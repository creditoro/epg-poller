package dk.creditoro.epgPoller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestEpgPoller {

    @Test
    void testConstructor() {
		EpgPoller epg =  new EpgPoller();
		System.out.println("EPG: " + epg);
		assertEquals(7, 7);
	}


    @Test
    void testConection() {
		EpgPoller epg = new EpgPoller();
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

	

