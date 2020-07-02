package com.example.webservice.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SummaryDTO {

    @SerializedName("Date")
    @Expose
    private String Date;
    @SerializedName("Countries")
    @Expose
    private List<CountriesDTO> Countries;
    @SerializedName("Global")
    @Expose
    private GlobalDTO Global;

    public SummaryDTO() {
    }

    public SummaryDTO(String date, List<CountriesDTO> countries, GlobalDTO global) {
        this.Date = date;
        this.Countries = countries;
        this.Global = global;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public List<CountriesDTO> getCountries() {
        return Countries;
    }

    public void setCountries(List<CountriesDTO> Countries) {
        this.Countries = Countries;
    }

    public GlobalDTO getGlobalDTO() {
        return new GlobalDTO(Global.getTotalRecovered(), Global.getNewRecovered(), Global.getTotalDeaths(), Global.getNewDeaths(), Global.getTotalConfirmed(), Global.getNewConfirmed());
    }

    public void setGlobal(GlobalDTO Global) {
        this.Global = Global;
    }
}
