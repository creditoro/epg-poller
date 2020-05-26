package dk.creditoro.epg_poller.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
* CreditoroProductionTest
*/
class CreditoroProductionTest {
	CreditoroProduction creditoroProductions;	
	CreditoroProduction creditoroProductions1;	
	
	public CreditoroProductionTest(){
		creditoroProductions = new CreditoroProduction("test", "producerId", "channelId", "description");
		creditoroProductions1 = new CreditoroProduction("10-10-10", "test", "Producer", "channel", "thisIsADescription");
	}

	@Test
	void getTitle(){
		assertEquals("test", creditoroProductions.title, "could not get title");
	}

	@Test
	void getProducerId(){
		assertEquals("producerId", creditoroProductions.producerId, 
				"could not get producerId");
	}

	@Test
	void getChannelId(){
		assertEquals("channelId", creditoroProductions.channelId, 
				"could not get channelId");
	}

	@Test
	void getIdentifier(){
		assertEquals("10-10-10", creditoroProductions1.identifier, "could not get identifier");
	}

	@Test
	void getProducer(){
		assertEquals("Producer", creditoroProductions1.producer, 
				"could not get Object Producer");
	}

	@Test
	void getChannel(){
		assertEquals("channel", creditoroProductions1.channel, 
				"could not get Object channel");
	}

	@Test
	void getDescription(){
		assertEquals("description", creditoroProductions.description,
				"could not get the description");
		assertEquals("thisIsADescription", creditoroProductions1.description,
				"could not get the description");
	}

	@Test
	void getToString(){
		assertEquals(String.format("%n| %15s - %35s |%n| %15s - %35s |%n| %15s - %35s |%n| %15s - %35s |",
				"Title", "test", 
				"ProducerIdentifier", "producerId", 
				"ChannelIdentifieres", "channelId",
				"Description" ,"description"), creditoroProductions.toString(), 
				"ToString Method changed, and I think this is the wrong way to test it?");
	}
}
