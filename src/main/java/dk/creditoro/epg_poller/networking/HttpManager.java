package dk.creditoro.epg_poller.networking;

import dk.creditoro.epg_poller.networking.models.CreditoroChannel;
import dk.creditoro.epg_poller.networking.models.CreditoroProduction;
import dk.creditoro.epg_poller.networking.models.TVTidChannels;
import dk.creditoro.epg_poller.networking.models.TVTidProductions;
import kong.unirest.Unirest;
import kong.unirest.UnirestConfigException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpManager {
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
                .get("https://tvtid-api.api.tv2.dk/api/tvtid/v1/schedules/channels");
        var obj = response.asObject(TVTidChannels.class);
        return obj.getBody();
    }

    public TVTidProductions[] getTvTidProductions(List<Integer> channelIdList, LocalDate date) {
        LOGGER.info("Getting TVTid productions.");
        var response = Unirest
                .get(String.format("https://tvtid-api.api.tv2.dk/api/tvtid/v1/epg/dayviews/%s", date))
				.queryString("ch", channelIdList);
		LOGGER.log(Level.INFO, "Status code: {0}", response.asJson().getStatus());
		return response.asObject(TVTidProductions[].class).getBody();
    }

    public CreditoroChannel postChannel(CreditoroChannel channel) {
        LOGGER.info("Posting to Creditoro API.");
        var response = Unirest
                .post("https://api.creditoro.nymann.dev/channels/")
                .body(channel)
                .header(AUTHORIZATION, token);
		var jsonResponse = response.asObject(CreditoroChannel.class);
		LOGGER.info(jsonResponse.getStatusText());
		return jsonResponse.getBody();
    }

    public CreditoroProduction postProductions(CreditoroProduction channel) {
        LOGGER.info("Posting Production to Creditoro API.");
        var response = Unirest
                .post("https://api.creditoro.nymann.dev/channels/")
                .body(channel)
                .header(AUTHORIZATION, token);
		var jsonResponse = response.asObject(CreditoroProduction.class);
		LOGGER.info(jsonResponse.getStatusText());
		return jsonResponse.getBody();
    }

    public int deleteChannel(String identifier) {
		LOGGER.log(Level.INFO, "Deleting identifier: {0}", identifier);
        var response = Unirest
                .delete("https://api.creditoro.nymann.dev/channels/" + identifier)
                .header(AUTHORIZATION, token);
		var jsonResponse = response.asJson().getStatus();
		LOGGER.log(Level.INFO, "The identifier maybe deleted, look at the respone: {0}", jsonResponse);
		return jsonResponse;
    }

    public CreditoroChannel[] getChannels(String route, String query) {
		LOGGER.log(Level.INFO, "Getting the channel: {0}", query);
        var response = Unirest
                .get(String.format(route))
				.queryString("q", query)
                .header(AUTHORIZATION, token);
		LOGGER.log(Level.INFO, "Got the channels with: {0}", query);
		return response.asObject(CreditoroChannel[].class).getBody();
    }

    public boolean login(String email, String password) {
        var response = Unirest
                .post("https://api.creditoro.nymann.dev/users/login")
                .body(Map.of("email", email, "password", password)).asJson();
        var getBody = response.getBody();
        if (getBody == null) {
            return false;
        }
        var getObject = getBody.getObject();
        token = getObject.getString("token");
        return true;
    }

}
