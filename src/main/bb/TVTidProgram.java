package dk.creditoro.epg_poller.networking.models;

import java.util.List;

import com.google.gson.annotations.*;

/**
* TvTidProgram
*/
public class TVTidProgram {
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
    private Images imgs;
    @SerializedName("locations")
    @Expose
    private Locations locations;
    @SerializedName("ttvTexted")
    @Expose
    private boolean ttvTexted;
    @SerializedName("references")
    @Expose
    private List<Reference> references = null;
	
	public TVTidProgram(String id, String url, String title, List<String> categories, String desc, String orgTitle, int prodYear, 
			String prodCountry, String teaser, Images imgs, boolean ttvTexted, List<Reference> references){
		this.id = id;
		this.url = url;
		this.title = title;
		this.categories = categories;
		this.desc = desc;
		this.orgTitle = orgTitle;
		this.prodYear = prodYear;
		this.prodCountry = prodCountry;
		this.teaser = teaser;
		// this.audio = audio;
		// this.video = video;
		this.imgs = imgs;
		// this.credits = credits;
		this.ttvTexted = ttvTexted;
		this.references = references;
	}

	/**
	* @return the desc
	*/
	public String getDesc() {
		return desc;
	}

	/**
	* @return the id
	*/
	public String getId() {
		return id;
	}
	
	/**
	* @return the orgTitle
	*/
	public String getOrgTitle() {
		return orgTitle;
	}
	/**
	* @return the title
	*/
	public String getTitle() {
		return title;
	}
}

/**
*  Video properties
*/
class Video {
	String aspect;

	Video(String aspect){
		this.aspect = aspect;
	}
}

/**
*  Image Data from tvtid
*/
class Image{
	String url;
	String scaleUrl;
	String width;
	String height;
	Image(String url, String scaleUrl, String width, String height){
		this.url = url;
		this.scaleUrl = scaleUrl;
		this.width = width;
		this.height = height;
	}
}

/**
*  List of Images
*/
class Images {
	Image[] images;

	Images(Image[] images){
		this.images = images;
	}
}

/**
* Credits
*/
class Credits {
	Director director;

	Credits(Director director){
		this.director = director;
	}
}

/**
* List of Director names
*/
class Director {
	String[] name;

	Director(String[] name){
		this.name = name;
	}
}

/**
*  Where is it broadcastet
*/
class Locations {
	Schedules schedules;	
	String[] vods;
	Locations(Schedules schedules, String[] vods){
		this.schedules = schedules;
		this.vods = vods;
	}
}

/**
*  Hold info about program, and what channel it is on
*/
class Schedules {
	String channelId;
	String title;
	String[] categories;
	long stop;
	long start;
	boolean rerun;
	boolean premiere;
	boolean live;

	Schedules(String channelId, String title, String[] categories, long stop, long start, boolean reprun, boolean premiere, boolean live){
		this.channelId = channelId;
		this.title = title;
		this.categories = categories;
		this.stop = stop;
	}
}

/**
*  Somekey type stuf I don't know what is
*/
class Reference {
	String authority;
	String type;
	String key;

	Reference(String authority, String type, String key){
		this.authority = authority;
		this.type = type;
		this.key = key;
	}
}

class Series {
    int episode;
    int season;
	Series(int episode, int season){
		this.episode = episode;
		this.season = season;
	}

}
