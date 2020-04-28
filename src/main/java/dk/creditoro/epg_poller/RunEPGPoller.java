package dk.creditoro.epg_poller;

import dk.creditoro.epg_poller.core.EPGPoller;

public class RunEPGPoller {
    public static void main(String[] args) {
        // new EPGPoller().start();
		new EPGPoller().startPostProductions();
    }
}
