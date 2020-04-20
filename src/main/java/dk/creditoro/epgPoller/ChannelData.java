package dk.creditoro.epgPoller;


/**
* ChannelData
*/
public class ChannelData {
		private String name;
		private int id;
		private String icon;
		private String logo;
		private String svgLogo;

	public ChannelData(String name, int id, String icon, String logo, String svgLogo){
		this.name = name;
		this.id = id;
		this.icon = icon;
		this.logo = logo;
		this.svgLogo = svgLogo;
	}

	public String getName() {
		return name;
	}
	public int getId(){
		return id;
	}
	public String getIcon() {
		return icon;
	}
	public String getLogo() {
		return logo;
	}
	public String getSvgLogo() {
		return svgLogo;
	}
}
