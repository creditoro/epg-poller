package dk.creditoro.epg_poller.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
* TVTidChannelsTest
*/
class TVTidChannelsTest {
	private TVTidChannels tvTidChannels;
	private TVTidChannel[] channelArray;

	TVTidChannelsTest(){
	channelArray = new TVTidChannel[]{
		new TVTidChannel( 3, "TV 3 DANMARK", "https://epg-images.tv2.dk/channellogos/icon/3.png", "https://epg-images.tv2.dk/channellogos/logo/3.png", "https://epg-images.tv2.dk/channellogos/svg/3.svg", "da", 2),
		new TVTidChannel( 2, "TV 2 DANMARK", "https://epg-images.tv2.dk/channellogos/icon/3.png", "https://epg-images.tv2.dk/channellogos/logo/3.png", "https://epg-images.tv2.dk/channellogos/svg/3.svg", "da", 2),
		};
	tvTidChannels = new TVTidChannels(channelArray);
	}

	@Test
	void getChannel(){
		assertEquals("TV 3 DANMARK", tvTidChannels.getChannels()[0].getTitle(), "Problem with getting channel");	
		assertEquals("TV 2 DANMARK", tvTidChannels.getChannels()[1].getTitle(), "Problem with getting channel");	
	}

	@Test 
	void getChannelById(){
		assertEquals(3, tvTidChannels.getChannel(3).getId(), "could not get channels by id");
		assertEquals(2, tvTidChannels.getChannel(2).getId(), "Could not get channels by id");
		assertNull(tvTidChannels.getChannel(4), "sould not be albe to get anything");
	}
	
	@Test
	void getChannelByTitle(){
		var message = "getChannel by title/Name does not work.";
		assertEquals("TV 2 DANMARK", tvTidChannels.getChannel("TV 2 DANMARK").getTitle(), message);
		assertEquals("TV 3 DANMARK", tvTidChannels.getChannel("TV 3 DANMARK").getTitle(), message);
		assertNull(tvTidChannels.getChannel("TV 5000"), message);
	}

}
