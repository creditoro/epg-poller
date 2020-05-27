package dk.creditoro.epg_poller.core;

import java.util.logging.Logger;

/**
 * LoadConfig
 */
public class LoadConfig {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static LoadConfig loadconfig = null;
    private String apiUrl = System.getenv("API_URL") + "%s";
    private String user = System.getenv("API_USER");
    private String password = System.getenv("API_PASSWORD");
    private String webUrlTV2 = System.getenv("API_WEBURLTV2");

    private LoadConfig(){
        if( apiUrl == null || user == null || password == null || webUrlTV2 == null){
            LOGGER.warning("You have too setup system variabels, now exiting");
            System.exit(0);
        } else {
            LOGGER.info("LoadConfig initilised");
        }
    }

    /**
     * @return the loadconfig
     */
    public static LoadConfig getLoadconfig() {
        if(loadconfig == null){
            loadconfig = new LoadConfig();
        } 
        return loadconfig;
    }

    /**
     * @return the APIURL
     */
    public String getAPIURL() {
        return apiUrl;
    }

    /**
     * @return the weburltv2
     */
    public String getWeburltv2() {
        return webUrlTV2;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;

    }
}
