
package dk.creditoro.epg_poller.models.program;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TVTidProgram {

	private String channelId;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("seriesId")
    @Expose
    private String seriesId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("orgTitle")
    @Expose
    private String orgTitle;
    @SerializedName("prodYear")
    @Expose
    private int prodYear;
    @SerializedName("prodCountry")
    @Expose
    private String prodCountry;
    @SerializedName("teaser")
    @Expose
    private String teaser;
    @SerializedName("series")
    @Expose
    private Series series;
    @SerializedName("imgs")
    @Expose
    private Imgs imgs;
    @SerializedName("locations")
    @Expose
    private Locations locations;
    @SerializedName("ttvTexted")
    @Expose
    private boolean ttvTexted;
    @SerializedName("references")
    @Expose
    private List<Reference> references = null;

	/**
	* @return the channelId
	*/
	public String getChannelId() {
		return channelId;
	}
	/**
	* @param channelId the channelId to set
	*/
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOrgTitle() {
        return orgTitle;
    }

    public void setOrgTitle(String orgTitle) {
        this.orgTitle = orgTitle;
    }

    public int getProdYear() {
        return prodYear;
    }

    public void setProdYear(int prodYear) {
        this.prodYear = prodYear;
    }

    public String getProdCountry() {
        return prodCountry;
    }

    public void setProdCountry(String prodCountry) {
        this.prodCountry = prodCountry;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public Imgs getImgs() {
        return imgs;
    }

    public void setImgs(Imgs imgs) {
        this.imgs = imgs;
    }

    public Locations getLocations() {
        return locations;
    }

    public void setLocations(Locations locations) {
        this.locations = locations;
    }

    public boolean isTtvTexted() {
        return ttvTexted;
    }

    public void setTtvTexted(boolean ttvTexted) {
        this.ttvTexted = ttvTexted;
    }

    public List<Reference> getReferences() {
		return references;
	}

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

}
