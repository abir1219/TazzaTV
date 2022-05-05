package com.textifly.tazzatv.Model;

public class CityModel {
    String cityName;
    int cityImage;

    public CityModel(String cityName, int cityImage) {
        this.cityName = cityName;
        this.cityImage = cityImage;
    }

    public String getCityName() {
        return cityName;
    }

    public int getCityImage() {
        return cityImage;
    }
}
