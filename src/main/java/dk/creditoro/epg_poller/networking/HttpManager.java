package dk.creditoro.epg_poller.networking;

import dk.creditoro.epg_poller.networking.models.CreditoroChannel;
import dk.creditoro.epg_poller.networking.models.TVTidChannels;
import kong.unirest.Unirest;
import kong.unirest.UnirestConfigException;

import java.util.Map;
import java.util.logging.Logger;

public class HttpManager {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
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
        LOGGER.info(response.asJson().getStatusText());
        return response.asObject(CreditoroChannel.class)
                .getBody();
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
