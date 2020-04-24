package dk.creditoro.epg_poller.networking;

import dk.creditoro.epg_poller.networking.models.CreditoroChannel;
import dk.creditoro.epg_poller.networking.models.TVTidChannels;
import kong.unirest.Unirest;
import kong.unirest.UnirestConfigException;

import java.util.Map;
import java.util.logging.Logger;

public class HttpManager {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static final String PATH = "/%s/{%s}";
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
        LOGGER.info("Getting TV tid channels.");
        var response = Unirest
                .get("https://tvtid-api.api.tv2.dk/api/tvtid/v1/schedules/channels");
        var obj = response.asObject(TVTidChannels.class);
        return obj.getBody();
    }

    public CreditoroChannel postChannel(CreditoroChannel channel) {
        LOGGER.info("Posting to Creditoro API.");
        var response = Unirest
                .post("https://api.creditoro.nymann.dev/channels/")
                .body(channel)
                .header("Authorization", token);
		var JSONResponse = response.asObject(CreditoroChannel.class);
        LOGGER.info(String.format("The Channel that got posted is: %s, and got http status: %s", channel.getName(), JSONResponse.getStatus()));
        var channelResponse = JSONResponse.getBody();
		LOGGER.info(String.format("Channel is: %s, String URL is: %s, Identifier is: %s", 
					channelResponse.getName(), 
					channelResponse.getIconUrl(),
					channelResponse.getIdentifier()));
		return channelResponse;
    }

    public int deleteChannel(String identifier) {
        LOGGER.info("Deleting identifier: " + identifier + " on Creditoro API.");
        var response = Unirest
                .delete("https://api.creditoro.nymann.dev/channels/" + identifier)
                .header("Authorization", token);
		var JsonResponse = response.asJson();
        LOGGER.info(String.format("The identifier: %s, got http status: %s delted?", identifier, JsonResponse.getStatus()));
		return JsonResponse.getStatus();
    }

    public CreditoroChannel[] getChannels(String route, String query) {
        LOGGER.info("Getting the Channel " + query + " from Creditoro API.");
        var response = Unirest
                .get(String.format(route))
				.queryString("q", query)
                .header("Authorization", token);
        LOGGER.info(String.format("The Channel that is search for is: %s, and got http status: %s", query, response.asJson().getStatus()));
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
