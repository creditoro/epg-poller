package dk.creditoro.epg_poller.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
* TVTidProductionTest
*/
class TVTidProductionTest {
	private TVTidProduction tvTidProduction;

	public TVTidProductionTest(){
		tvTidProduction = new TVTidProduction(100L, 100000L, new String[] {"Action", "Comedy"}, "1234", 
				"The best show", false, false, false, false);
	}

	@Test
	void getTitle(){
		assertEquals("The best show", tvTidProduction.getTitle(), "Maybe the constructer changed?");
	}

	@Test
	void getId(){
		assertEquals("1234", tvTidProduction.getId(), "Maybe the constructer changed?");
	}
	
}
