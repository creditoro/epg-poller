package dk.creditoro.epg_poller.networking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpManagerTest {
    private HttpManager httpManager;

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
    }

    @Test
    void login() {
    }
}