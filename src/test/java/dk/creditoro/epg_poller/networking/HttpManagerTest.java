package dk.creditoro.epg_poller.networking;

import org.junit.jupiter.api.Test;

import dk.creditoro.epg_poller.core.LoadConfig;
import dk.creditoro.epg_poller.models.CreditoroChannel;
import dk.creditoro.epg_poller.models.CreditoroProduction;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;

class HttpManagerTest {
    private HttpManager httpManager;
    private static String USER = LoadConfig.getLoadconfig().getUser();
    private static String PASSWORD = LoadConfig.getLoadconfig().getPassword();

    public HttpManagerTest() {
        USER = LoadConfig.getLoadconfig().getUser();
        PASSWORD = LoadConfig.getLoadconfig().getPassword();
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
        assertEquals(channel.getName(), responeChannel.getName(), "Makes sure it it gets the same channels as it posted");
        // post the samme channel again
        var dublicatedResponeChannel = httpManager.postChannel(channel);
        assertNotEquals(channel, dublicatedResponeChannel, "Makes sure it can't post dublicate");
        // Delte the channel 
        getRespone = httpManager.getChannels(channel.getName());
        var httpRespone = httpManager.deleteChannel( getRespone[0].getIdentifier() );
        assertEquals(204, httpRespone, "Checks if we can delte channel");
    }
    @Test
    void getChannels(){
        assertEquals(0, httpManager.getChannels("blblaldasldsaldslds").length,
                "Because this is a unknow Channel this should be a length of 0");
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
        assertNull(httpManager.postProductions(production), 
                "It should not be able to post the same production twice?");
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
        var httpMangerLocal = new HttpManager();
        var loggedind = httpMangerLocal.login(USER, "1234");
        assertFalse(loggedind);
        assertNull(httpMangerLocal.getIdentifier());
    }

    @Test
    void getTvTidProductionsWithDesc(){
        httpManager.login(USER, PASSWORD);
        var productionFromTvTid = httpManager.getTvTidProductions(
                Arrays.asList(1), LocalDate.now());
        assertNotEquals(0, productionFromTvTid.length,
                "the lenght should be longer than zero");

        var productionWithDescription = httpManager.getTvTidProductionsWithDesc(
                productionFromTvTid[0].getProductions()[0],
                productionFromTvTid[0].getId());

        assertNotNull(productionWithDescription, "this should always have a object");
    }
}

