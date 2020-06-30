package com.example.webservice.model.dto;

import com.example.webservice.model.vo.GlobalVO;
import com.google.gson.annotations.SerializedName;

public class GlobalDTO {

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

    public GlobalDTO() {
    }

    public GlobalDTO(int totalRecovered, int newRecovered, int totalDeaths, int newDeaths, int totalConfirmed, int newConfirmed) {
        TotalRecovered = totalRecovered;
        NewRecovered = newRecovered;
        TotalDeaths = totalDeaths;
        NewDeaths = newDeaths;
        TotalConfirmed = totalConfirmed;
        NewConfirmed = newConfirmed;
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

    public GlobalVO getGlobalVO() {
        return new GlobalVO(TotalRecovered, NewRecovered, TotalDeaths, NewDeaths, TotalConfirmed, NewConfirmed);
    }
}
