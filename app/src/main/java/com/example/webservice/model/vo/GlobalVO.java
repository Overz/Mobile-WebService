package com.example.webservice.model.vo;

import com.example.webservice.model.dto.GlobalDTO;

public class GlobalVO {

    private int novosConfirmados;
    private int totalConfimados;
    private int novosMortos;
    private int totalMortes;
    private int novosRecuperados;
    private int totalRecuperados;

    public GlobalVO(GlobalDTO dto) {
        this.novosConfirmados = dto.getNewConfirmed();
        this.totalConfimados = dto.getTotalConfirmed();
        this.novosMortos = dto.getNewDeaths();
        this.totalMortes = dto.getTotalDeaths();
        this.novosRecuperados = dto.getNewRecovered();
        this.totalRecuperados = dto.getTotalRecovered();
    }

    public GlobalVO(int totalRecuperados, int novosRecuperados, int totalDeaths, int novosMortos, int totalConfimados, int novosConfirmados) {
    }

    public int getNovosConfirmados() {
        return novosConfirmados;
    }

    public void setNovosConfirmados(int novosConfirmados) {
        this.novosConfirmados = novosConfirmados;
    }

    public int getTotalConfimados() {
        return totalConfimados;
    }

    public void setTotalConfimados(int totalConfimados) {
        this.totalConfimados = totalConfimados;
    }

    public int getNovosMortos() {
        return novosMortos;
    }

    public void setNovosMortos(int novosMortos) {
        this.novosMortos = novosMortos;
    }

    public int getTotalMortes() {
        return totalMortes;
    }

    public void setTotalMortes(int totalMortes) {
        this.totalMortes = totalMortes;
    }

    public int getNovosRecuperados() {
        return novosRecuperados;
    }

    public void setNovosRecuperados(int novosRecuperados) {
        this.novosRecuperados = novosRecuperados;
    }

    public int getTotalRecuperados() {
        return totalRecuperados;
    }

    public void setTotalRecuperados(int totalRecuperados) {
        this.totalRecuperados = totalRecuperados;
    }
}
