package dk.creditoro.epg_poller.networking.models;

import com.google.gson.annotations.SerializedName;

/**
* CreditoroProductions
*/
public class CreditoroProduction {
	String title;
    @SerializedName("producer_id")
	String producerId;
    @SerializedName("channel_id")
	String channelId;
	String identifier;
	Object producer;
	Object channel;

	public CreditoroProduction(String title, String producerId, String channelId){
		this.title = title;
		this.producerId = producerId;
		this.channelId = channelId;
	}

	public CreditoroProduction(String identifier, String title, Object producer, Object channel){
		this.identifier = identifier;
		this.title = title;
		this.producer = producer;
		this.channel = channel;
	}
		
}
