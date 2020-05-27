package dk.creditoro.epg_poller.models;

import com.google.gson.annotations.SerializedName;

public class CreditoroChannel {
    private final String name;
    private final String identifier;

    @SerializedName("icon_url")
    private final String iconUrl;

    public CreditoroChannel(String name, String iconUrl) {
        this.name = name;
        this.iconUrl = iconUrl;
        this.identifier = null;
    }
	
    public CreditoroChannel(String name, String iconUrl, String identifier) {
        this.name = name;
        this.iconUrl = iconUrl;
        this.identifier = identifier;
    }

	/**
	* @return the name
	*/
	public String getName() {
		return name;
	}

	/**
	* @return the iconUrl
	*/
	public String getIconUrl() {
		return iconUrl;
	}

	/**
	* @return the identifier
	*/
	public String getIdentifier() {
		return identifier;
	}
}
