package dk.creditoro.epg_poller.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
* EPGPollerTest
*/
class EPGPollerTest {
	private EPGPoller epgPoller;

	EPGPollerTest(){
		epgPoller = new EPGPoller();
	}

	@Test
	void assertEpgPoller(){
		assertNotNull(epgPoller, "Checks if it can make the epgPoller");
	}

}
