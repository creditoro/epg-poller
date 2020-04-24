package dk.creditoro.epg_poller.networking.models;

public class TVTidChannel {
    int id;
    String title;
    String icon;
    String logo;
    String svgLogo;
    String lang;
    int sort;

    public TVTidChannel(int id, String title, String icon, String logo, String svgLogo, String lang, int sort) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.logo = logo;
        this.svgLogo = svgLogo;
        this.lang = lang;
        this.sort = sort;
    }


    public String getTitle() {
        return title;
    }

    public String getLogo() {
        return logo;
    }

}
