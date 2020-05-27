package dk.creditoro.epg_poller.models.program;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.*;

/**
* TVTidProgramTest
*/
class TVTidProgramTest {
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
		assertDoesNotThrow(() -> tvTidProgram.getImgs().setImages(new ArrayList<Image>()) );
		assertDoesNotThrow(() -> tvTidProgram.getImgs().getImages().add(new Image()) );
		assertDoesNotThrow(() -> tvTidProgram.setUrl("www.g.dk"));
		assertDoesNotThrow(() -> tvTidProgram.setDesc("description"));
		assertDoesNotThrow(() -> tvTidProgram.setSeries(new Series()));
		assertDoesNotThrow(() -> tvTidProgram.setTeaser("this is a teaser"));
		assertDoesNotThrow(() -> tvTidProgram.setProdYear(1995));
		assertDoesNotThrow(() -> tvTidProgram.setSeriesId("1010SeriesId"));
		assertDoesNotThrow(() -> tvTidProgram.setChannelId("101010"));
		assertDoesNotThrow(() -> tvTidProgram.setTtvTexted(true));
		assertDoesNotThrow(() -> tvTidProgram.setLocations(new Locations()));
		assertDoesNotThrow(() -> tvTidProgram.getLocations().setSchedules(new ArrayList<Schedule>()) );
		assertDoesNotThrow(() -> tvTidProgram.getLocations().setVods(new ArrayList<Object>()) );
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

	@Test
	void getSetImages(){
		assertDoesNotThrow(() -> tvTidProgram.getImgs().getImages().get(0).setUrl("www.k.dk"));
		assertDoesNotThrow(() -> tvTidProgram.getImgs().getImages().get(0).setWidth(1000));
		assertDoesNotThrow(() -> tvTidProgram.getImgs().getImages().get(0).setHeight(1500));
		assertDoesNotThrow(() -> tvTidProgram.getImgs().getImages().get(0).setScaleUrl("www.k.scale.dk"));
		assertEquals("www.k.dk", tvTidProgram.getImgs().getImages().get(0).getUrl());
		assertEquals(1000, tvTidProgram.getImgs().getImages().get(0).getWidth());
		assertEquals(1500, tvTidProgram.getImgs().getImages().get(0).getHeight());
		assertEquals("www.k.scale.dk", tvTidProgram.getImgs().getImages().get(0).getScaleUrl());
		assertEquals(1, tvTidProgram.getImgs().getImages().size());
	}

	@Test
	void getSetLocations(){
		assertNotNull(tvTidProgram.getLocations().getSchedules());
		tvTidProgram.getLocations().getSchedules().add(new Schedule());
		tvTidProgram.getLocations().getSchedules().get(0).setStop(10);
		tvTidProgram.getLocations().getSchedules().get(0).setStart(5);
		tvTidProgram.getLocations().getSchedules().get(0).setLive(true);
		tvTidProgram.getLocations().getSchedules().get(0).setRerun(false);
		tvTidProgram.getLocations().getSchedules().get(0).setPremiere(true);
		tvTidProgram.getLocations().getSchedules().get(0).setTitle("title");
		tvTidProgram.getLocations().getSchedules().get(0).setChannelId("10-10-10");
		tvTidProgram.getLocations().getSchedules().get(0).setCategories(new ArrayList<String>());
		tvTidProgram.getLocations().getSchedules().get(0).getCategories().add("Action");
		tvTidProgram.getLocations().getSchedules().get(0).getCategories().add("Romance");

		assertEquals(1, tvTidProgram.getLocations().getSchedules().size() );
		assertEquals(5, tvTidProgram.getLocations().getSchedules().get(0).getStart() );
		assertEquals(10, tvTidProgram.getLocations().getSchedules().get(0).getStop() );
		assertEquals(true, tvTidProgram.getLocations().getSchedules().get(0).isLive() );
		assertEquals(false, tvTidProgram.getLocations().getSchedules().get(0).isRerun() );
		assertEquals(true, tvTidProgram.getLocations().getSchedules().get(0).isPremiere() );
		assertEquals("title", tvTidProgram.getLocations().getSchedules().get(0).getTitle() );
		assertEquals("10-10-10", tvTidProgram.getLocations().getSchedules().get(0).getChannelId() );
		assertNotNull(tvTidProgram.getLocations().getSchedules().get(0).getCategories() );
		assertEquals("Action", tvTidProgram.getLocations().getSchedules().get(0).getCategories().get(0) );
		assertEquals("Romance", tvTidProgram.getLocations().getSchedules().get(0).getCategories().get(1) );
		assertTrue(tvTidProgram.getLocations().getVods().add(new Object()) );
		assertNotNull(tvTidProgram.getLocations().getVods().get(0));
	}

	@Test
	void getSetSeries(){
		assertEquals(0, tvTidProgram.getSeries().getSeason());
		assertEquals(0, tvTidProgram.getSeries().getEpisode());
		assertDoesNotThrow(() -> tvTidProgram.getSeries().setSeason(10) );
		assertDoesNotThrow(() -> tvTidProgram.getSeries().setEpisode(5) );
		assertEquals(10, tvTidProgram.getSeries().getSeason());
		assertEquals(5, tvTidProgram.getSeries().getEpisode());
	}
}
