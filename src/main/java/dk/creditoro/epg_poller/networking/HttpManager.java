package dk.creditoro.epg_poller.networking;

import dk.creditoro.epg_poller.networking.models.*;
import dk.creditoro.epg_poller.networking.models.program.*;
// import dk.creditoro.epg_poller.networking.models.program.TVTidProgram;
import kong.unirest.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpManager {
	private static final String WEBURLTV2 = "https://tvtid-api.api.tv2.dk/api/tvtid/";
	private static final String TVTIDCHANNELS = "%s%s/schedules/channels";
	private static final String TVTIDPRODUCTIONS = "%s%s/epg/dayviews/%s";
	private static final String TVTIDPROGRAMS = "%s%s/schedules/channels/%s/programs/%s";
	private static final String RESTVERSIONV1 = "v1";
	private static final String APIURL = "https://api.creditoro.nymann.dev%s";
	private static final String CHANNELS = "/channels/";
	private static final String PRODUCTION = "/productions/";
	private static final String USERS = "/users/";
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static final String AUTHORIZATION = "Authorization";
    private String token;

    public HttpManager() {
        try {
            Unirest.config()
                    .setDefaultHeader("Accept", "application/json")
                    .setDefaultHeader("Content-Type", "application/json")
                    .followRedirects(true)
                    .enableCookieManagement(false);
        } catch (UnirestConfigException e) {
            LOGGER.info("Unirest is already configured, skipping.");
        }
    }

    public TVTidChannels getTvTidChannels() {
        LOGGER.info("Getting TVTid channels.");
        var response = Unirest
                .get(String.format(TVTIDCHANNELS, WEBURLTV2, RESTVERSIONV1));
        var obj = response.asObject(TVTidChannels.class);
        return obj.getBody();
    }

    public TVTidProductions[] getTvTidProductions(List<Integer> channelIdList, LocalDate date) {
        LOGGER.info("Getting TVTid productions.");
        var responseAsTvTidProd = Unirest
                .get(String.format(TVTIDPRODUCTIONS, WEBURLTV2, RESTVERSIONV1, date))
				.queryString("ch", channelIdList).asObject(TVTidProductions[].class);
		LOGGER.log(Level.INFO, "Status code: {0}", responseAsTvTidProd.getStatus());
		return responseAsTvTidProd.getBody();
    }

	public TVTidProgram getTvTidProductionsWithDesc(TVTidProduction tvTidProduction, int ChannelId){
        LOGGER.info("Getting TVTid productions with description.");
		var responseAsTvTidProg = Unirest.get(String.format(TVTIDPROGRAMS, WEBURLTV2, RESTVERSIONV1, ChannelId, tvTidProduction.getId())).
			asObject(TVTidPrograms.class);
		LOGGER.log(Level.INFO, "Status code: {0}", responseAsTvTidProg.getStatus());	
		return responseAsTvTidProg.getBody().getProgram();
	}		

    public CreditoroChannel postChannel(CreditoroChannel channel) {
        LOGGER.info("Posting to Creditoro API.");
        var response = Unirest
                .post(String.format(APIURL, CHANNELS))
                .body(channel)
                .header(AUTHORIZATION, token);
		var jsonResponse = response.asObject(CreditoroChannel.class);
		LOGGER.info(jsonResponse.getStatusText());
		return jsonResponse.getBody();
    }

    public CreditoroProduction postProductions(CreditoroProduction channel) {
        LOGGER.log(Level.INFO, "Posting Production {0} to Creditoro API.", channel.getTitle());
		int status = 200;
		while(true){
        var creditoroProduction = Unirest
                .post(String.format(APIURL, PRODUCTION))
                .body(channel)
                .header(AUTHORIZATION, token)
				.asObject(CreditoroProduction.class);
			status = creditoroProduction.getStatus();
			if ( 200 <= status & status <= 299){
				LOGGER.log(Level.INFO, "Http status: {0}", status);
				return creditoroProduction.getBody();
			} else if (status == 429){
				try {
					LOGGER.log(Level.INFO, "Http Status: {0} and waiting", status);
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					LOGGER.info("Thread got woken UP?");
				}
			} else {
				LOGGER.log(Level.WARNING, "Returning null, Http Status {0}", status);
				return null;
			}
		} 
    }

    public int deleteChannel(String identifier) {
		LOGGER.log(Level.INFO, "Deleting identifier: {0}", identifier);
        var response = Unirest
                .delete(String.format(APIURL, CHANNELS)+ identifier)
                .header(AUTHORIZATION, token);
		var jsonResponse = response.asJson().getStatus();
		LOGGER.log(Level.INFO, "The identifier maybe deleted, look at the respone: {0}", jsonResponse);
		return jsonResponse;
    }

	public CreditoroChannel[] getChannels(String query) {
		LOGGER.log(Level.INFO, "Getting the channel: {0} from creditoro API", query);
		int status = 200;
		while(true){
			var creditoroChannels = Unirest
				.get(String.format(APIURL, CHANNELS))
				.queryString("q", query)
				.header(AUTHORIZATION, token)
				.asObject(CreditoroChannel[].class);
			status = creditoroChannels.getStatus();
			if ( 200 <= status & status <= 299){
				LOGGER.log(Level.INFO, "Http status: {0}", status);
				return creditoroChannels.getBody();
			} else if (status == 429){
				try {
					LOGGER.log(Level.INFO, "Http Status: {0} and waiting", status);
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					LOGGER.info("Thread got woken UP?");
				}
			} else {
				LOGGER.log(Level.WARNING, "Returning null, Http Status {0}", status);
				return null;
			}
		}
	} 

    public boolean login(String email, String password) {
        var response = Unirest
                .post(String.format(APIURL, USERS)+ "login")
                .body(Map.of("email", email, "password", password)).asJson();
        token = response.getHeaders().getFirst("token");
        if (token == null) {
            return false;
        }
        return true;
    }

}
