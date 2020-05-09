package dk.creditoro.epg_poller.core;

import dk.creditoro.epg_poller.networking.HttpManager;
import dk.creditoro.epg_poller.networking.models.CreditoroChannel;
import dk.creditoro.epg_poller.networking.models.CreditoroProduction;
import dk.creditoro.epg_poller.networking.models.TVTidChannel;
import dk.creditoro.epg_poller.networking.models.TVTidProductions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The type Epg poller.
 */
public class EPGPoller {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final HttpManager httpManager;

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
        httpManager.login("string@string.dk", "string");
        var tvTidChannels = extract();
        var creditoroChannels = transform(tvTidChannels);
        load(creditoroChannels);
        LOGGER.info(":-)");
    }

	public void startPostProductions(){
        httpManager.login("string@string.dk", "string");
        var tvTidChannels = extract();
		List<Integer> tvTidChannelsIds = new ArrayList<>();
		for (TVTidChannel tvTidChannel : tvTidChannels) {
			tvTidChannelsIds.add(tvTidChannel.getId());
		}
		var productions = httpManager.getTvTidProductions(tvTidChannelsIds, LocalDate.now()); 
		var productionWithDescription = httpManager.getTvTidProductions(productions);
		var creditoroProductions = transform(productions, "6ed67a28-f288-492b-8947-d9d0e9539608");
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
	private List<CreditoroProduction> transform(TVTidProductions[] channelproductions, String producerIdentifier) {
		var creditoroProductions = new ArrayList<CreditoroProduction>();
		var creditoroChannels = Arrays.asList(httpManager.getChannels(""));
		var tvTidChannels = httpManager.getTvTidChannels();

		for (TVTidProductions channel : channelproductions){
			for (var production : channel.getProductions()){ 

				if (tvTidChannels == null){
					System.out.println("Channel is null");
				}
				System.out.println("Production: " + production.getTitle() + " Id:"+ channel.getId());
				var channelName = tvTidChannels.getChannel(channel.getId()).getTitle();
				System.out.println("put Channel: " + channelName);
				creditoroChannels.stream()
					.filter(cChannel->cChannel.getName()
							.contains(channelName)).
					forEach(
							s-> creditoroProductions.add(new CreditoroProduction(
									production.getTitle(), producerIdentifier, s.getIdentifier(), "TEST TEST EPG EPG SHIIIIT :D "))
							);



			}
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
			// If i could not post try again
			int isSuccess = 200;
			do {
				isSuccess = httpManager.postProductions(production).getStatus();
				LOGGER.log(Level.INFO, "Going to sleep, was a success? {0}", isSuccess);
				if(!(200 <= isSuccess) & ! ( isSuccess <= 299)){
					if( isSuccess == 429){
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							LOGGER.info("Thread got woken UP?");
						}
					} else {
						isSuccess = 200;
					}
				}
			} while (!(200 <= isSuccess) & ! ( isSuccess <= 299));
        }
    }
}

