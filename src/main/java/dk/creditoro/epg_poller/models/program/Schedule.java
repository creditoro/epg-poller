
package dk.creditoro.epg_poller.models.program;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Schedule {

    @SerializedName("channelId")
    @Expose
    private String channelId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;
    @SerializedName("stop")
    @Expose
    private int stop;
    @SerializedName("start")
    @Expose
    private int start;
    @SerializedName("rerun")
    @Expose
    private boolean rerun;
    @SerializedName("premiere")
    @Expose
    private boolean premiere;
    @SerializedName("live")
    @Expose
    private boolean live;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public int getStop() {
        return stop;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public boolean isRerun() {
        return rerun;
    }

    public void setRerun(boolean rerun) {
        this.rerun = rerun;
    }

    public boolean isPremiere() {
        return premiere;
    }

    public void setPremiere(boolean premiere) {
        this.premiere = premiere;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

}
