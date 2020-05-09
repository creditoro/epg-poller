package dk.creditoro.epg_poller.networking.models;

/**
* TvTidProgram
*/
public class TVTidProgram {
	String id;
	String url;
	String[] categories;
	String desc;
	String orgTitle;
	String prodYear;
	String prodCountry;
	String teaser;
	String audio;
	Video video;
	Images imgs;
	Credits credits;
	boolean ttvTexted;
	References references;
	
	public TVTidProgram(String id, String url, String[] categories, String desc, String orgTitle, String prodYear, 
			String prodCountry, String teaser, String audio, Video video, Images imgs, Credits credits ,boolean ttvTexted, References references){
		this.id = id;
		this.url = url;
		this.categories = categories;
		this.desc = desc;
		this.orgTitle = orgTitle;
		this.prodYear = prodYear;
		this.prodCountry = prodCountry;
		this.teaser = teaser;
		this.audio = audio;
		this.video = video;
		this.imgs = imgs;
		this.credits = credits;
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
class References {
	String authority;
	String type;
	String key;

	References(String authority, String type, String key){
		this.authority = authority;
		this.type = type;
		this.key = key;
	}
}
