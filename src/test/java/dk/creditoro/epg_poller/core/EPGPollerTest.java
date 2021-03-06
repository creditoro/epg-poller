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

	@Test 
	void runningEPGPoller(){
		assertDoesNotThrow(() -> epgPoller.start(), "EPGPoller can not run, check the assertMessage");
	}

	@Test 
	void runningEPGPollerProduction(){
		assertDoesNotThrow(() -> epgPoller.startPostProductions(), "EPGPoller can not run, check the assertMessage");
	}
}
