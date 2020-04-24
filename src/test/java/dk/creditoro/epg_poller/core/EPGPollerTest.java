package dk.creditoro.epg_poller.core;

import org.junit.jupiter.api.Test;

/**
* EPGPollerTest
*/
public class EPGPollerTest {

	EPGPoller epgPoller = new EPGPoller();	

	@Test
	void startTest(){
		epgPoller.start();

	}
}
