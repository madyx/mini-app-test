package madyx.tripndrive.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Site implements Serializable {

    @JsonProperty("code")
    private String code;

    @JsonProperty("label")
    private String label;

    @JsonProperty("type")
    private String type;

    @JsonProperty("lat")
    private Double lat;

    @JsonProperty("lng")
    private Double lng;

    @JsonProperty("htmlParkLandingUrl")
    private String htmlParkLandingUrl;

    @JsonProperty("parkMinDurationHours")
    private Integer parkMinDurationHours;

    @JsonProperty("parkMaxDurationDays")
    private Integer parkMaxDurationDays;

    @JsonProperty("rentDelayHours")
    private Integer rentDelayHours;

    @JsonProperty("parkDelayHours")
    private Integer parkDelayHours;

    @JsonProperty("agencyStart")
    private Object agencyStart;

    @JsonProperty("agencyEnd")
    private Object agencyEnd;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getHtmlParkLandingUrl() {
        return htmlParkLandingUrl;
    }

    @JsonProperty("htmlParkLandingUrl")
    public void setHtmlParkLandingUrl(String htmlParkLandingUrl) {
        this.htmlParkLandingUrl = htmlParkLandingUrl;
    }

    public Integer getParkMinDurationHours() {
        return parkMinDurationHours;
    }

    @JsonProperty("parkMinDurationHours")
    public void setParkMinDurationHours(Integer parkMinDurationHours) {
        this.parkMinDurationHours = parkMinDurationHours;
    }

    public Integer getParkMaxDurationDays() {
        return parkMaxDurationDays;
    }

    public void setParkMaxDurationDays(Integer parkMaxDurationDays) {
        this.parkMaxDurationDays = parkMaxDurationDays;
    }

    public Integer getRentDelayHours() {
        return rentDelayHours;
    }

    public void setRentDelayHours(Integer rentDelayHours) {
        this.rentDelayHours = rentDelayHours;
    }

    public Integer getParkDelayHours() {
        return parkDelayHours;
    }

    public void setParkDelayHours(Integer parkDelayHours) {
        this.parkDelayHours = parkDelayHours;
    }

    public Object getAgencyStart() {
        return agencyStart;
    }

    public void setAgencyStart(Object agencyStart) {
        this.agencyStart = agencyStart;
    }

    public Object getAgencyEnd() {
        return agencyEnd;
    }

    public void setAgencyEnd(Object agencyEnd) {
        this.agencyEnd = agencyEnd;
    }

}
