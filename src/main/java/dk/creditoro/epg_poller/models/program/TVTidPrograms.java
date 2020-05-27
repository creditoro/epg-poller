
package dk.creditoro.epg_poller.models.program;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TVTidPrograms {

    @SerializedName("program")
    @Expose
    private TVTidProgram program;

    public TVTidProgram getProgram() {
        return program;
    }

    public void setProgram(TVTidProgram program) {
        this.program = program;
    }

}
