package dk.creditoro.epg_poller.networking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
* TVTidChannelsTest
*/
public class TVTidChannelsTest {
	private TVTidChannels tvTidChannels;
	private TVTidChannel[] channelArray;

	TVTidChannelsTest(){
	channelArray = new TVTidChannel[]{
		new TVTidChannel( 3, "TV 2 DANMARK", "https://epg-images.tv2.dk/channellogos/icon/3.png", "https://epg-images.tv2.dk/channellogos/logo/3.png", "https://epg-images.tv2.dk/channellogos/svg/3.svg", "da", 2),
		new TVTidChannel( 3, "TV 2 DANMARK", "https://epg-images.tv2.dk/channellogos/icon/3.png", "https://epg-images.tv2.dk/channellogos/logo/3.png", "https://epg-images.tv2.dk/channellogos/svg/3.svg", "da", 2),
		};
	tvTidChannels = new TVTidChannels(channelArray);
	}

	@Test
	void getChannel(){
		for (TVTidChannel tvTidChannel : tvTidChannels.getChannels()) {
			assertEquals("TV 2 DANMARK", tvTidChannel.getTitle(), "Problem with getting channel");	
		}
	}
	
}
