package dk.creditoro.epg_poller.networking.models.program;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.*;

/**
* TVTidProgramTest
*/
public class TVTidProgramTest {
	TVTidProgram tvTidProgram;
	
	TVTidProgramTest(){
		tvTidProgram = new TVTidProgram();
	}

	@Test @BeforeEach
	void setTVTidProgram(){
		assertDoesNotThrow(() -> tvTidProgram.setTitle("tv2"));
		assertDoesNotThrow(() -> tvTidProgram.setOrgTitle("tv2"));
		assertDoesNotThrow(() -> tvTidProgram.setId("101010"));
		assertDoesNotThrow(() -> tvTidProgram.setImgs(new Imgs()));
		assertDoesNotThrow(() -> tvTidProgram.setUrl("www.g.dk"));
		assertDoesNotThrow(() -> tvTidProgram.setDesc("description"));
		assertDoesNotThrow(() -> tvTidProgram.setSeries(new Series()));
		assertDoesNotThrow(() -> tvTidProgram.setTeaser("this is a teaser"));
		assertDoesNotThrow(() -> tvTidProgram.setProdYear(1995));
		assertDoesNotThrow(() -> tvTidProgram.setSeriesId("1010SeriesId"));
		assertDoesNotThrow(() -> tvTidProgram.setChannelId("101010"));
		assertDoesNotThrow(() -> tvTidProgram.setTtvTexted(true));
		assertDoesNotThrow(() -> tvTidProgram.setLocations(new Locations()));
		assertDoesNotThrow(() -> tvTidProgram.setProdCountry("dk"));
		assertDoesNotThrow(() -> tvTidProgram.setCategories(new ArrayList<String>()));
		assertDoesNotThrow(() -> tvTidProgram.setReferences(new ArrayList<Reference>()));
	}
	
	@Test
	void getTVTidProgram(){
		var message = "tvTidProgram changed?";
		assertEquals("tv2", tvTidProgram.getTitle(), message);
		assertEquals("tv2", tvTidProgram.getOrgTitle(), message);
		assertEquals("101010", tvTidProgram.getId(), message);
		assertNotNull(tvTidProgram.getImgs(), message);
		assertEquals("www.g.dk", tvTidProgram.getUrl(), message);
		assertEquals("description", tvTidProgram.getDesc(), message);
		assertNotNull(tvTidProgram.getSeries(), message);
		assertEquals("this is a teaser", tvTidProgram.getTeaser(), message);
		assertEquals(1995, tvTidProgram.getProdYear(), message);
		assertEquals("1010SeriesId", tvTidProgram.getSeriesId(), message);
		assertEquals("101010", tvTidProgram.getChannelId(), message);
		assertEquals(true, tvTidProgram.isTtvTexted(), message);
		assertNotNull(tvTidProgram.getLocations(), message);
		assertEquals("dk", tvTidProgram.getProdCountry(), message);
		assertNotNull(tvTidProgram.getCategories(), message);
		assertNotNull(tvTidProgram.getReferences(), message);
	}

	@Test
	void setGetCategories(){
		tvTidProgram.getCategories().add("Hello world");
		assertEquals("Hello world", tvTidProgram.getCategories().get(0),
				"References list does not work?");
		assertEquals(1, tvTidProgram.getCategories().size(), "List should not be bigger than 1");
	}

	@Test
	void setGetReferences(){
		assertDoesNotThrow(()->
				tvTidProgram.getReferences().add(new Reference())
			);
		var referencesList = tvTidProgram.getReferences();
		var references = referencesList.get(0);
		references.setKey("thisKey");
		references.setType("thisType");
		references.setAuthority("thisAuthority");
		assertEquals(references, tvTidProgram.getReferences().get(0), 
				"These should be the same Object");
		assertEquals("thisKey", tvTidProgram.getReferences().get(0).getKey(),
				"Look at references.java");
		assertEquals("thisType", tvTidProgram.getReferences().get(0).getType(),
				"Look at references.java");
		assertEquals("thisAuthority", tvTidProgram.getReferences().get(0).getAuthority(),
				"Look at references.java");
	}
}
