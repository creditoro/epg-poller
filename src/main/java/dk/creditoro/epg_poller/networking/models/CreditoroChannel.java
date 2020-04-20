package dk.creditoro.epg_poller.networking.models;

import com.google.gson.annotations.SerializedName;

public class CreditoroChannel {
    private final String name;

    @SerializedName("icon_url")
    private final String iconUrl;

    public CreditoroChannel(String name, String iconUrl) {
        this.name = name;
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
