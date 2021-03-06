
package dk.creditoro.epg_poller.models.program;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Imgs {

    @SerializedName("images")
    @Expose
    private List<Image> images = null;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

}
