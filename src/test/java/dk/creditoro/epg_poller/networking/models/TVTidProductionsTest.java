package dk.creditoro.epg_poller.networking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
* TVTidProductionsTest
*/
public class TVTidProductionsTest {
		TVTidProductions tvTidProductions;
	
	public TVTidProductionsTest(){
		tvTidProductions = new TVTidProductions(1, new TVTidProduction[] {
			new TVTidProduction(100L, 112L, new String[] {"Action", "Comedy"}, "1234", 
					"The Best show", false, false, false, false)});
	}
	
	@Test
	void getId(){
		assertEquals(1, tvTidProductions.getId(), 
				"Getting the Id of TVTidProductions");
	}

	@Test
	void getProduction(){
		for (var production : tvTidProductions.getProductions()) {
			assertEquals("1234", production.getId(), 
					"Getting the production id");	
			assertEquals("The Best show", production.getTitle(), 
					"Getting the production name");	
		}
	}
}
