package dk.creditoro.epg_poller.models;

/**
* TVTidProduction
*/
public class TVTidProduction {
	long start;
	long stop;
	String[] categories;
	String id;
	String title;
	boolean availableAsVod;
	boolean rerun;
	boolean premiere;
	boolean live;

	public TVTidProduction(long start, long stop, String[] categories, 
			String id, String title, boolean availableAsVod, boolean rerun, 
			boolean premiere, boolean live){

		this.start = start;
		this.stop = stop;
		this.categories = categories;
		this.id = id;
		this.title = title;
		this.availableAsVod = availableAsVod;
		this.rerun = rerun;
		this.premiere = premiere;
		this.live = live;
	}

	/**
	* @return the title
	*/
	public String getTitle() {
		return title;
	}

	/**
	* @return the id
	*/
	public String getId() {
		return id;
	}
	
}
