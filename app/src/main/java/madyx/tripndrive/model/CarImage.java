package madyx.tripndrive.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CarImage implements Serializable {

    @JsonProperty("smallUrl")
    private String smallUrl;

    @JsonProperty("mediumUrl")
    private String mediumUrl;

    @JsonProperty("largeUrl")
    private String largeUrl;

    @JsonProperty("key")
    private String key;

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public String getMediumUrl() {
        return mediumUrl;
    }

    public void setMediumUrl(String mediumUrl) {
        this.mediumUrl = mediumUrl;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
