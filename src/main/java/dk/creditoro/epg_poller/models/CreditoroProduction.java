package dk.creditoro.epg_poller.models;

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
	String description;
	String identifier;
	Object producer;
	Object channel;

	public CreditoroProduction(String title, String producerId, String channelId, String description){
		this.title = title;
		this.producerId = producerId;
		this.channelId = channelId;
		this.description = description;
	}

	public CreditoroProduction(String identifier, String title, Object producer, Object channel, String description){
		this.identifier = identifier;
		this.title = title;
		this.producer = producer;
		this.channel = channel;
		this.description = description;
	}

	/**
	* @return the title
	*/
	public String getTitle() {
		return title;
	}

	/**
	* @return the identifier
	*/
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public String toString(){
		return String.format("%n| %15s - %35s |%n| %15s - %35s |%n| %15s - %35s |%n| %15s - %35s |",
				"Title", title, 
				"ProducerIdentifier", producerId, 
				"ChannelIdentifieres", channelId,
				"Description" ,description);
	}
		
}
