
package dk.creditoro.epg_poller.models.program;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Locations {

    @SerializedName("schedules")
    @Expose
    private List<Schedule> schedules = null;
    @SerializedName("vods")
    @Expose
    private List<Object> vods = null;

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public List<Object> getVods() {
        return vods;
    }

    public void setVods(List<Object> vods) {
        this.vods = vods;
    }

}
