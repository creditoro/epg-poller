package dk.creditoro.epg_poller.models.program;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
* TVTidProgramsTest
*/
class TVTidProgramsTest {
	TVTidPrograms tvTidPrograms;
	TVTidPrograms tvTidProgramsWithNull;

	public TVTidProgramsTest(){
		tvTidPrograms = new TVTidPrograms();
		tvTidProgramsWithNull = new TVTidPrograms();
	}
	
	@Test @BeforeEach
	void addTVTidProgram(){
		assertDoesNotThrow(()-> tvTidPrograms.setProgram(new TVTidProgram()) );
	}

	@Test
	void getTVTidProgram(){
		assertNotNull(tvTidPrograms.getProgram(),
				"This should not be null, check TVTidPrograms for more");
		assertNull(tvTidProgramsWithNull.getProgram(),
				"Makes sure it does not create a object by itself");
	}
}
