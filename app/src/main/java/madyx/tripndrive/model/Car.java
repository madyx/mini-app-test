package madyx.tripndrive.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Car implements Serializable {

    @JsonProperty("kmIncluded")
    private Integer kmIncluded;

    @JsonProperty("pid")
    private Integer pid;

    @JsonProperty("modelBrand")
    private String modelBrand;

    @JsonProperty("modelName")
    private String modelName;

    @JsonProperty("modelCategory")
    private String modelCategory;

    @JsonProperty("carYear")
    private Integer carYear;

    @JsonProperty("doorNumber")
    private Integer doorNumber;

    @JsonProperty("placeNumber")
    private Integer placeNumber;

    @JsonProperty("transmission")
    private String transmission;

    @JsonProperty("gaz")
    private String gaz;

    @JsonProperty("airconditionning")
    private Boolean airconditionning;

    @JsonProperty("sunroof")
    private Boolean sunroof;

    @JsonProperty("jackPlug")
    private Boolean jackPlug;

    @JsonProperty("gps")
    private Boolean gps;

    @JsonProperty("babyChair")
    private Integer babyChair;

    @JsonProperty("boosterSeat")
    private Integer boosterSeat;

    @JsonProperty("childSeat")
    private Integer childSeat;

    @JsonProperty("images")
    private List<CarImage> images = new ArrayList<CarImage>();

    @JsonProperty("start")
    private String start;

    @JsonProperty("end")
    private String end;

    @JsonProperty("days")
    private Integer days;

    @JsonProperty("price")
    private Integer price;

    @JsonProperty("extraKmPrice")
    private Integer extraKmPrice;

    @JsonProperty("kilometerLabel")
    private String kilometerLabel;

    @JsonProperty("gazLabel")
    private String gazLabel;

    @JsonProperty("transmissionLabel")
    private String transmissionLabel;

    @JsonProperty("modelCategoryLabel")
    private String modelCategoryLabel;

    @JsonProperty("dailyPrice")
    private Integer dailyPrice;


    public Integer getKmIncluded() {
        return kmIncluded;
    }

    public void setKmIncluded(Integer kmIncluded) {
        this.kmIncluded = kmIncluded;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getModelBrand() {
        return modelBrand;
    }

    public void setModelBrand(String modelBrand) {
        this.modelBrand = modelBrand;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelCategory() {
        return modelCategory;
    }

    public void setModelCategory(String modelCategory) {
        this.modelCategory = modelCategory;
    }

    public Integer getCarYear() {
        return carYear;
    }

    public void setCarYear(Integer carYear) {
        this.carYear = carYear;
    }

    public Integer getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(Integer doorNumber) {
        this.doorNumber = doorNumber;
    }

    public Integer getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(Integer placeNumber) {
        this.placeNumber = placeNumber;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getGaz() {
        return gaz;
    }

    public void setGaz(String gaz) {
        this.gaz = gaz;
    }

    public Boolean getAirconditionning() {
        return airconditionning;
    }

    public void setAirconditionning(Boolean airconditionning) {
        this.airconditionning = airconditionning;
    }

    public Boolean getSunroof() {
        return sunroof;
    }

    public void setSunroof(Boolean sunroof) {
        this.sunroof = sunroof;
    }

    public Boolean getJackPlug() {
        return jackPlug;
    }

    public void setJackPlug(Boolean jackPlug) {
        this.jackPlug = jackPlug;
    }

    public Boolean getGps() {
        return gps;
    }

    public void setGps(Boolean gps) {
        this.gps = gps;
    }

    public Integer getBabyChair() {
        return babyChair;
    }

    public void setBabyChair(Integer babyChair) {
        this.babyChair = babyChair;
    }

    public Integer getBoosterSeat() {
        return boosterSeat;
    }

    public void setBoosterSeat(Integer boosterSeat) {
        this.boosterSeat = boosterSeat;
    }

    public Integer getChildSeat() {
        return childSeat;
    }

    public void setChildSeat(Integer childSeat) {
        this.childSeat = childSeat;
    }

    public List<CarImage> getImages() {
        return images;
    }

    public void setImages(List<CarImage> images) {
        this.images = images;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getExtraKmPrice() {
        return extraKmPrice;
    }

    public void setExtraKmPrice(Integer extraKmPrice) {
        this.extraKmPrice = extraKmPrice;
    }

    public String getKilometerLabel() {
        return kilometerLabel;
    }

    public void setKilometerLabel(String kilometerLabel) {
        this.kilometerLabel = kilometerLabel;
    }

    public String getGazLabel() {
        return gazLabel;
    }

    public void setGazLabel(String gazLabel) {
        this.gazLabel = gazLabel;
    }

    public String getTransmissionLabel() {
        return transmissionLabel;
    }

    public void setTransmissionLabel(String transmissionLabel) {
        this.transmissionLabel = transmissionLabel;
    }

    public String getModelCategoryLabel() {
        return modelCategoryLabel;
    }

    public void setModelCategoryLabel(String modelCategoryLabel) {
        this.modelCategoryLabel = modelCategoryLabel;
    }

    public Integer getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(Integer dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

}
