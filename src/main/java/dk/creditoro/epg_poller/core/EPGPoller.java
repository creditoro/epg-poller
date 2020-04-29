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
		var productions = httpManager.getTvTidProductions(Arrays.asList(1,2), LocalDate.now()); 
		var creditoroProductions = transform(productions);
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
     * Transform the list of channels from TVTid to a list of Creditoro compatible channels.
     *
     * @param channels the channels
     */
    private List<CreditoroProduction> transform(TVTidProductions[] channelproductions) {
        var creditoroProductions = new ArrayList<CreditoroProduction>();
		
		for (var channel : channelproductions){
			var productions = channel.getProductions();
			for (var production : productions){ 
				creditoroProductions.add(new CreditoroProduction(production.getTitle(),
							"EPG_POLLER", "413a074e-e186-46c4-a581-b80e6efc5608"));
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
            httpManager.postProductions(production);
        }
    }
}

