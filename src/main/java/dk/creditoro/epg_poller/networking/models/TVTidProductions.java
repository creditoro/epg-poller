package dk.creditoro.epg_poller.networking.models;

import com.google.gson.annotations.SerializedName;

/**
* TVTidProductions
*/
public class TVTidProductions {
	private int id;
    @SerializedName("programs")
	private TVTidProduction[] productions;

    public TVTidProductions(int id, TVTidProduction[] productions) {
        this.id = id;
		this.productions = productions;
    }

	/**
	* @return the id
	*/
	public int getId() {
		return id;
	}

	/**
	* @return the productions
	*/
	public TVTidProduction[] getProductions() {
		return productions;
	}
	
}
