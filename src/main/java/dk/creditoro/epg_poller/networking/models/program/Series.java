
package dk.creditoro.epg_poller.networking.models.program;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Series {

    @SerializedName("episode")
    @Expose
    private int episode;
    @SerializedName("season")
    @Expose
    private int season;

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

}
