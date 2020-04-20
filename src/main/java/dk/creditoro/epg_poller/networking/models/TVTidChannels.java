package dk.creditoro.epg_poller.networking.models;

public class TVTidChannels {
    private final TVTidChannel[] channels;

    public TVTidChannels(TVTidChannel[] channels) {
        this.channels = channels;
    }

    public TVTidChannel[] getChannels() {
        return channels;
    }
}
