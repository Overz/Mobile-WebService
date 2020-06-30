package com.example.webservice.model.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CountriesDTO implements Serializable {

    @SerializedName("Date")
    private String Date;
    @SerializedName("TotalRecovered")
    private int TotalRecovered;
    @SerializedName("NewRecovered")
    private int NewRecovered;
    @SerializedName("TotalDeaths")
    private int TotalDeaths;
    @SerializedName("NewDeaths")
    private int NewDeaths;
    @SerializedName("TotalConfirmed")
    private int TotalConfirmed;
    @SerializedName("NewConfirmed")
    private int NewConfirmed;
    @SerializedName("Slug")
    private String Slug;
    @SerializedName("CountryCode")
    private String CountryCode;
    @SerializedName("Country")
    private String Country;


    public CountriesDTO() {
    }

    public CountriesDTO(String date, int totalRecovered, int newRecovered, int totalDeaths,
                        int newDeaths, int totalConfirmed, int newConfirmed, String slug, String countryCode, String country) {
        Date = date;
        TotalRecovered = totalRecovered;
        NewRecovered = newRecovered;
        TotalDeaths = totalDeaths;
        NewDeaths = newDeaths;
        TotalConfirmed = totalConfirmed;
        NewConfirmed = newConfirmed;
        Slug = slug;
        CountryCode = countryCode;
        Country = country;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public int getTotalRecovered() {
        return TotalRecovered;
    }

    public void setTotalRecovered(int TotalRecovered) {
        this.TotalRecovered = TotalRecovered;
    }

    public int getNewRecovered() {
        return NewRecovered;
    }

    public void setNewRecovered(int NewRecovered) {
        this.NewRecovered = NewRecovered;
    }

    public int getTotalDeaths() {
        return TotalDeaths;
    }

    public void setTotalDeaths(int TotalDeaths) {
        this.TotalDeaths = TotalDeaths;
    }

    public int getNewDeaths() {
        return NewDeaths;
    }

    public void setNewDeaths(int NewDeaths) {
        this.NewDeaths = NewDeaths;
    }

    public int getTotalConfirmed() {
        return TotalConfirmed;
    }

    public void setTotalConfirmed(int TotalConfirmed) {
        this.TotalConfirmed = TotalConfirmed;
    }

    public int getNewConfirmed() {
        return NewConfirmed;
    }

    public void setNewConfirmed(int NewConfirmed) {
        this.NewConfirmed = NewConfirmed;
    }

    public String getSlug() {
        return Slug;
    }

    public void setSlug(String Slug) {
        this.Slug = Slug;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String CountryCode) {
        this.CountryCode = CountryCode;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }
}
