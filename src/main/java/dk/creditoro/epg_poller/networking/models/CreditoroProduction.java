package dk.creditoro.epg_poller.networking.models;

import com.google.gson.annotations.SerializedName;

/**
* CreditoroProductions
*/
public class CreditoroProduction {
	private String title;
	private String producer_id;
	private String channel_id;
	private String identifier;
	private Object producer;
	private Object channel;

	public CreditoroProduction(String title, String producer_id, String channel_id){
		this.title = title;
		this.producer_id = producer_id;
		this.channel_id = channel_id;
	}

	public CreditoroProduction(String identifier, String title, Object producer, Object channel){
		this.identifier = identifier;
		this.title = title;
		this.producer = producer;
		this.channel = channel;
	}
		
}
