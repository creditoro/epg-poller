package dk.creditoro.epg_poller.networking.models;

import com.google.gson.annotations.*;

/**
* TVTidPrograms
*/
public class TVTidPrograms {

    @SerializedName("program")
    @Expose
	TVTidProgram program;	
	
	public TVTidPrograms(TVTidProgram program){
		this.program = program;
	}

	/**
	* @return the program
	*/
	public TVTidProgram getProgram() {
		return program;
	}

	/**
	* @param program the program to set
	*/
	public void setProgram(TVTidProgram program) {
		this.program = program;
	}
}
