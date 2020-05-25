package dk.creditoro.epg_poller.networking;

import java.util.logging.Logger;

/**
 * LoadConfig
 */
public class LoadConfig {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static LoadConfig loadconfig = null;
    private String USER;
    private String PASSWORD;
    private String WEBURLTV2;
    private String APIURL;

    private LoadConfig(){
            System.out.println( System.getenv("API_URL") + "%s" );
            System.out.println( System.getenv("API_USER") );
            System.out.println( System.getenv("API_PASSWORD") );
            System.out.println( System.getenv("API_WEBURLTV2") );
        if(System.getenv("API_URL") == null || 
                System.getenv("API_WEBURLTV2") == null ||
                System.getenv("API_USER") == null ||
                System.getenv("API_PASSWORD") == null ){
            LOGGER.warning("You have too setup system variabels, now exiting");
            System.exit(0);
        } else {
            APIURL = System.getenv("API_URL") + "%s";
            USER = System.getenv("API_USER");
            PASSWORD = System.getenv("API_PASSWORD");
            WEBURLTV2 = System.getenv("API_WEBURLTV2");
            LOGGER.info("LoadConfig initilised");
            LOGGER.info(APIURL + "\n");
            LOGGER.info(WEBURLTV2 + "\n");
            LOGGER.info(USER + "\n");
            LOGGER.info(PASSWORD + "\n");
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
        return APIURL;
    }

    /**
     * @return the weburltv2
     */
    public String getWeburltv2() {
        return WEBURLTV2;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return USER;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return PASSWORD;

    }
}
