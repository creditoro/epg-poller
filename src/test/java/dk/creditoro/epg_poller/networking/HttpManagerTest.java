package dk.creditoro.epg_poller.networking;

import org.junit.jupiter.api.Test;

import dk.creditoro.epg_poller.networking.models.CreditoroChannel;
import dk.creditoro.epg_poller.networking.models.CreditoroProduction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpManagerTest {
    private HttpManager httpManager;
	private static final String USER = "string@string.dk";
	private static final String PASSWORD = "string";

    public HttpManagerTest() {
        httpManager = new HttpManager();
    }

    @Test
    void getTvTidChannels() {
        var channels = httpManager.getTvTidChannels();
        assertNotNull(channels);
        var tvTidChannels = channels.getChannels();
        assertNotNull(tvTidChannels);
        assertTrue(tvTidChannels.length > 0);
    }

    @Test
    void postChannel() {
		httpManager.login(USER, PASSWORD);
		//Create the channel that we are testing
		var channel = new CreditoroChannel("testChannel", "testURL");
		// Makes sure to delte the channel before we test if it can post.
		var getRespone = httpManager.getChannels(channel.getName());
		if (getRespone.length != 0){
			httpManager.deleteChannel( getRespone[0].getIdentifier() );
		}
		// Post the first channel
		var responeChannel = httpManager.postChannel(channel);
		assertTrue(channel.getName().equalsIgnoreCase(responeChannel.getName()), "Makes sure it it gets the same channels as it posted");
		// post the samme channel again
		var dublicatedResponeChannel = httpManager.postChannel(channel);
		assertFalse(channel.equals(dublicatedResponeChannel), "Makes sure it can't post dublicate");
		// Delte the channel 
		getRespone = httpManager.getChannels(channel.getName());
		var httpRespone = httpManager.deleteChannel( getRespone[0].getIdentifier() );
		assertEquals(204, httpRespone, "Checks if we can delte channel");
	}

	@Test
	void postAndDeleteProduction(){
		var title = "this is a awesome title";
		httpManager.login(USER, PASSWORD);
		var productionD = httpManager.getProductions(title);
		if(productionD.length != 0){
			httpManager.deleteProduction(productionD[0].getIdentifier());
		}
		var channel = httpManager.getChannels("TV2");
		var producerId = httpManager.getIdentifier();
		var channelId = channel[0].getIdentifier();
		var production = new CreditoroProduction(title, 
				producerId, channelId, "Get this description");
		var productionFromAPI = httpManager.postProductions(production);
		assertNotNull(productionFromAPI, "Maybe it could not post to the api?");
		assertEquals(title, productionFromAPI.getTitle());
		assertEquals(204, httpManager.deleteProduction(productionFromAPI.getIdentifier()),
				"Maybe it could not get created or else it could not get delted");
	}

    @Test
    void login() {
        var loggedind = httpManager.login(USER, PASSWORD);
		assertTrue(loggedind);
    }

    @Test
    void failLogin() {
        var loggedind = httpManager.login(USER, "1234");
		assertFalse(loggedind);
    }
}

