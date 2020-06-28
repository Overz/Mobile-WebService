package com.example.webservice.model.dto;

import com.example.webservice.model.vo.EstadoVO;

import java.io.Serializable;

public class EstadoDTO implements Serializable {

    private int uid;
    private String uf;
    private String state;
    private int cases;
    private int deaths;
    private int suspects;
    private int refuses;
    private String datetime;

    public EstadoDTO() {
    }

    public EstadoDTO(int uid, String uf, String state, int cases, int deaths, int suspects, int refuses, String datetime) {
        this.uid = uid;
        this.uf = uf;
        this.state = state;
        this.cases = cases;
        this.deaths = deaths;
        this.suspects = suspects;
        this.refuses = refuses;
        this.datetime = datetime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getSuspects() {
        return suspects;
    }

    public void setSuspects(int suspects) {
        this.suspects = suspects;
    }

    public int getRefuses() {
        return refuses;
    }

    public void setRefuses(int refuses) {
        this.refuses = refuses;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public EstadoVO getEstadoVO() {
        return new EstadoVO(uid, uf, state, cases, deaths, suspects, refuses, datetime);
    }

    @Override
    public String toString() {
        return "EstadoDTO{" +
                "uid=" + uid +
                ", uf='" + uf + '\'' +
                ", state='" + state + '\'' +
                ", cases=" + cases +
                ", deaths=" + deaths +
                ", suspects=" + suspects +
                ", refuses=" + refuses +
                ", datetime='" + datetime + '\'' +
                '}';
    }
}
