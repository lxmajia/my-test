package cn.xwlin.rcgame.entity;

public class ClubInfo {
    private Integer id;

    private String name;

    private String showName;

    private String logo;

    private String locCity;

    private String stadiumNameName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName == null ? null : showName.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getLocCity() {
        return locCity;
    }

    public void setLocCity(String locCity) {
        this.locCity = locCity == null ? null : locCity.trim();
    }

    public String getStadiumNameName() {
        return stadiumNameName;
    }

    public void setStadiumNameName(String stadiumNameName) {
        this.stadiumNameName = stadiumNameName == null ? null : stadiumNameName.trim();
    }
}