package dk.creditoro.epg_poller.networking;

import dk.creditoro.epg_poller.networking.models.*;
import kong.unirest.HttpResponse;
import kong.unirest.RequestBodyEntity;
import kong.unirest.Unirest;
import kong.unirest.UnirestConfigException;

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

	public TVTidProductions[] getTvTidProductions(TVTidProductions[] productions){
		List<TVTidProgram> programs = new ArrayList<>();
		for (TVTidProductions tvTidProductions : productions) {
			for (var tvTidProduction : tvTidProductions.getProductions()) {
				var responseAsTvTidProg = Unirest
					.get(String.format(TVTIDPROGRAMS, WEBURLTV2, RESTVERSIONV1, tvTidProductions.getId(), tvTidProduction.getId())).asObject(TVTidProgram.class);
				LOGGER.log(Level.INFO, "Status code: {0}", responseAsTvTidProg.getStatus());
				programs.add(responseAsTvTidProg.getBody());
			}
		}
		System.out.println("All the programs we got");
		for (var program : programs) {
			System.out.println("program name: " + program.getOrgTitle() + " Id: " + program.getId() + " desc: " + program.getDesc());	
		}

		return null;
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

    public HttpResponse<CreditoroProduction> postProductions(CreditoroProduction channel) {
        LOGGER.info("Posting Production to Creditoro API.");
        var response = Unirest
                .post(String.format(APIURL, PRODUCTION))
                .body(channel)
                .header(AUTHORIZATION, token);
		var creditoroProduction = response.asObject(CreditoroProduction.class);
		LOGGER.log(Level.INFO, "Got HttpResponse {0}", creditoroProduction.getStatus() );
		return creditoroProduction;
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
		LOGGER.log(Level.INFO, "Getting the channel: {0}", query);
        var response = Unirest
                .get(String.format(APIURL, CHANNELS))
				.queryString("q", query)
                .header(AUTHORIZATION, token);
		var creditoroChannels = response.asObject(CreditoroChannel[].class);
		LOGGER.log(Level.INFO, "Got the channels with: {0}", creditoroChannels.getStatus());
		return creditoroChannels.getBody();
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
