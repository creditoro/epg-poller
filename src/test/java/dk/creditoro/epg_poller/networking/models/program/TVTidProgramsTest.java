package dk.creditoro.epg_poller.networking.models.program;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
* TVTidProgramsTest
*/
public class TVTidProgramsTest {
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
