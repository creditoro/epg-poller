package dk.creditoro.epg_poller.networking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;


/**
* CreditoroChannelTest
*/
public class CreditoroChannelTest {
	CreditoroChannel creditoroChannel = new CreditoroChannel("TV2", 
			"https://epg-images.tv2.dk/channellogos/logo/3.png", 
			"04bdd23d-75d5-4381-af97-628b7e531e0d");
	
	@Test
	void getName(){
		assertEquals("TV2", creditoroChannel.getName(), "Could not get the name?");
	}

	@Test
	void getIconUrl(){
		assertEquals("https://epg-images.tv2.dk/channellogos/logo/3.png", creditoroChannel.getIconUrl(), "Could not get the URL?");
	}
		
	@Test
	void getIdentifier(){
		assertEquals("04bdd23d-75d5-4381-af97-628b7e531e0d", creditoroChannel.getIdentifier(), "Could not get the Identifier?");
	}

	@Test
	void makeACreditoroChannel(){
		CreditoroChannel creditoroChannel = new CreditoroChannel("TV2", "https://epg-images.tv2.dk/channellogos/logo/3.png");
		assertNull(creditoroChannel.getIdentifier(), "the Identifier should be null? if it is not where does it get set?");
	}

	
}
