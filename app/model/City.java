package model;

public class City {
    private Integer id;
    private String name;
    private Double latitude;
    private Double longitude;

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
        this.name = name;
    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String toString() {
        return "id:" + id + ", name:" + name + ", latitude:" + latitude + ", longitude:" + longitude;
    }
}

