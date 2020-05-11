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

	@Override
	public String toString(){
		// return String.format("\n | Title: %35s | ProducerIdentifier: %50s,\n | ChannelIdentifieres: %35s, | Description: %50s |", 
		// 		title, producerId, channelId, description);
		return String.format("\n| %15s - %35s |\n| %15s - %35s |\n| %15s - %35s |\n| %15s - %35s |",
				"Title", title, 
				"ProducerIdentifier", producerId, 
				"ChannelIdentifieres", channelId,
				"Description" ,description);
	}
		
}
