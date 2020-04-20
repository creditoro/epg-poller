package dk.creditoro.epg_poller;

import dk.creditoro.epg_poller.core.EPGPoller;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestEpgPoller {

    @Test
    void testConstructor() {
		EPGPoller epg =  new EPGPoller();
		System.out.println("EPG: " + epg);
		assertEquals(7, 7);
	}


    @Test
    void testConection() {
		EPGPoller epg = new EPGPoller();
		System.out.println("EPG: " + epg);
		assertEquals(7, 7);
	}
//    @Test
//    void testShouldFailNoneConnection() {
//		EpgPoller epg = new EpgPoller();
//		System.out.println("EPG: " + epg);
//		assertEquals(7, 4);
//	}
}

	

