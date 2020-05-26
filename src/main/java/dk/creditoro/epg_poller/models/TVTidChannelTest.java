package dk.creditoro.epg_poller.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
* TVTidChannel
*/
public class TVTidChannelTest {
	private TVTidChannel tvTidChannel;
	public TVTidChannelTest(){
		tvTidChannel = new TVTidChannel( 3, "TV 2 DANMARK", "https://epg-images.tv2.dk/channellogos/icon/3.png", "https://epg-images.tv2.dk/channellogos/logo/3.png", "https://epg-images.tv2.dk/channellogos/svg/3.svg", "da", 2);
	}
	

	@Test
	void getTitle(){
		assertEquals("TV 2 DANMARK" , tvTidChannel.getTitle(), "Check the channel names get method");
	}

	@Test
	void getLogo(){
		assertEquals( "https://epg-images.tv2.dk/channellogos/logo/3.png" , tvTidChannel.getLogo(), "Check the channel logo get method");
	}

	@Test
	void getId(){
		assertEquals(3, tvTidChannel.getId(), "Check the channel id get method");
	}

}
