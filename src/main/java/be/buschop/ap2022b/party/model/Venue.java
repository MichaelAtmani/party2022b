package be.buschop.ap2022b.party.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Venue {
    @Id
    private int id;
    private String venueName;
    private String linkMoreInfo;
    private Boolean hasFreeParking;
    private Boolean toddlerFriendly;
    private Boolean hasGoodFood;
    private Boolean teenagerFriendly;

    public Venue() {

    }

    public Venue(String venueName, String linkMoreInfo, Boolean hasFreeParking, Boolean toddlerFriendly, Boolean hasGoodFood, Boolean teenagerFriendly) {
        this.venueName = venueName;
        this.linkMoreInfo = linkMoreInfo;
        this.hasFreeParking = hasFreeParking;
        this.toddlerFriendly = toddlerFriendly;
        this.hasGoodFood = hasGoodFood;
        this.teenagerFriendly = teenagerFriendly;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getLinkMoreInfo() {
        return linkMoreInfo;
    }

    public void setLinkMoreInfo(String linkMoreInfo) {
        this.linkMoreInfo = linkMoreInfo;
    }

    public Boolean getHasFreeParking() {
        return hasFreeParking;
    }

    public void setHasFreeParking(Boolean hasFreeParking) {
        this.hasFreeParking = hasFreeParking;
    }

    public Boolean getToddlerFriendly() {
        return toddlerFriendly;
    }

    public void setToddlerFriendly(Boolean toddlerFriendly) {
        toddlerFriendly = toddlerFriendly;
    }

    public Boolean getHasGoodFood() {
        return hasGoodFood;
    }

    public void setHasGoodFood(Boolean hasGoodFood) {
        this.hasGoodFood = hasGoodFood;
    }

    public Boolean getTeenagerFriendly() {
        return teenagerFriendly;
    }

    public void setTeenagerFriendly(Boolean teenagerFriendly) {
        teenagerFriendly = teenagerFriendly;
    }
}
