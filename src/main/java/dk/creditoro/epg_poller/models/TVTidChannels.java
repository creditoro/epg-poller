package dk.creditoro.epg_poller.models;

/**
* TVTidChannels
*/
public class TVTidChannels {
    private final TVTidChannel[] channels;

    public TVTidChannels(TVTidChannel[] channels) {
        this.channels = channels;
    }

    public TVTidChannel[] getChannels() {
        return channels;
    }

	public TVTidChannel getChannel(String title){
		for (TVTidChannel tvTidChannel : channels) {
			if(tvTidChannel.getTitle().contains(title)){
				return tvTidChannel;
			}
		}
		return null;
	}

	public TVTidChannel getChannel(int channelId){
		for (TVTidChannel tvTidChannel : channels) {
			if(tvTidChannel.getId() == channelId){
				return tvTidChannel;
			}
		}
		return null;
	}
}
