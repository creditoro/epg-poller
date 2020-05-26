package dk.creditoro.epg_poller.core;

import dk.creditoro.epg_poller.networking.HttpManager;
import dk.creditoro.epg_poller.models.CreditoroChannel;
import dk.creditoro.epg_poller.models.CreditoroProduction;
import dk.creditoro.epg_poller.models.TVTidChannel;
import dk.creditoro.epg_poller.models.TVTidProductions;
import dk.creditoro.epg_poller.models.program.TVTidProgram;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


/**
 * The type Epg poller.
 */
public class EPGPoller {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final HttpManager httpManager;
    private String user = LoadConfig.getLoadconfig().getUser();
    private String password = LoadConfig.getLoadconfig().getPassword();

    /**
     * Instantiates a new Epg poller.
     */
    public EPGPoller() {
        httpManager = new HttpManager();
    }

    /**
     * Start the EPG Poller.
     */
    public void start() {
        httpManager.login(user, password);
        var tvTidChannels = extract();
        var creditoroChannels = transform(tvTidChannels);
        load(creditoroChannels);
        LOGGER.info(":-)");
    }

	public void startPostProductions(){
        httpManager.login(user, password);
        var tvTidChannels = extract();
		var productionsWithDesc = extractProductions(tvTidChannels);
		var creditoroProductions = transform(productionsWithDesc, "6ed67a28-f288-492b-8947-d9d0e9539608");
		loadProductions(creditoroProductions);
        LOGGER.info(":-)");
	}


    /**
     * Extract tv tid channel [ ].
     *
     * @return the tv tid channel [ ]
     */
    private TVTidChannel[] extract() {
        return httpManager.getTvTidChannels().getChannels();
    }


	/**
	 * Extract tv tid program [ ].
	 *
	 * @return the tv tid production [ ]
	 */
	private List<TVTidProgram> extractProductions(TVTidChannel[] tvTidChannels){

		// Makes a channel Id list. It is used for tvitid to get each production description.
		List<Integer> tvTidChannelsIds = new ArrayList<>();
		// Mapping ChannelIdentifieres
		Map<Integer, String> channelIdToTitle = new HashMap<>();
		for (TVTidChannel tvTidChannel : tvTidChannels) {
			tvTidChannelsIds.add(tvTidChannel.getId());
			channelIdToTitle.put(tvTidChannel.getId(), tvTidChannel.getTitle());
		}

		// Gets the productions without description
		var productions = httpManager.getTvTidProductions(tvTidChannelsIds, LocalDate.now()); 

		// Gets the production with description
		List<TVTidProgram> productionsList = new ArrayList<>();

		// Get a Map with Channel Name to Channel Identifier
		var channelIdentifierMap = getChannelIdentifierMap("");

		// The tvTidProductions hold the productions[]
		for (TVTidProductions tvTidProductions : productions) {
			//The second one where we go throug each of production[]
			for (var tvTidProduction : tvTidProductions.getProductions()) {
				// Gets the identifier from the creditoro api
				var channelIdentifier = channelIdentifierMap.get(channelIdToTitle.get(tvTidProductions.getId()));
				// Gets the productions from the tvtid.dk
				var productionWithDescription = httpManager.getTvTidProductionsWithDesc(tvTidProduction, tvTidProductions.getId());		
				// Sets Identifier on a channel, for later posting it to creditoro api
				productionWithDescription.setChannelId(channelIdentifier);
				// Add to the list we return later
				productionsList.add(productionWithDescription);
			}
		}
		return productionsList;
	}

	/**
	 * Extract Identifier from creditoro api.
	 *
	 * @return creditoro channel identifier
	 */
	private Map<String,String> getChannelIdentifierMap(String channelName){
		var channelResponse = httpManager.getChannels(channelName);

		Map<String,String> channelIdentifierMap = new HashMap<>();
		for (CreditoroChannel creditoroChannel : channelResponse) {
			channelIdentifierMap.put(creditoroChannel.getName(), creditoroChannel.getIdentifier());
		}
		return channelIdentifierMap;
	}


    /**
     * Transform the list of channels from TVTid to a list of Creditoro compatible channels.
     *
     * @param channels the channels
     */
    private List<CreditoroChannel> transform(TVTidChannel[] channels) {
        var creditoroChannels = new ArrayList<CreditoroChannel>();
        for (var channel : channels) {
            creditoroChannels.add(new CreditoroChannel(channel.getTitle(), channel.getLogo()));
        }
        return creditoroChannels;
    }


    /**
     * Transform the list of productions from TVTid to a list of Creditoro compatible productions.
     *
     * @param productions the productions
     */
	private List<CreditoroProduction> transform(List<TVTidProgram> tvTidProgramList, String producerIdentifier) {
		var creditoroProductions = new ArrayList<CreditoroProduction>();
		for (var program : tvTidProgramList){
			creditoroProductions.add(new CreditoroProduction(program.getTitle(), producerIdentifier, 
						program.getChannelId(), program.getDesc()));
		}
		return creditoroProductions;
	}


    /**
     * Load.
     *
     * @param channels the channels
     */
    private void load(Iterable<CreditoroChannel> channels) {
        for (var channel : channels) {
            httpManager.postChannel(channel);
        }
    }

    /**
     * Load.
     *
     * @param productions the productions
     */
	private void loadProductions(Iterable<CreditoroProduction> productions) {
		for (var production : productions) {
			httpManager.postProductions(production);
		}
	}
}

