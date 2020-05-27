
package dk.creditoro.epg_poller.models.program;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reference {

    @SerializedName("authority")
    @Expose
    private String authority;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("key")
    @Expose
    private String key;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
