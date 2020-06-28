package com.example.webservice.model.vo;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDateTime;

@DatabaseTable(tableName = "Pais")
public class PaisVO {

    @DatabaseField(canBeNull = false, generatedId = true, allowGeneratedIdInsert = true)
    private int id;
    @DatabaseField(columnName = "nomeProduto", canBeNull = false, dataType = DataType.STRING, width = 50)
    private String pais;
    @DatabaseField(columnName = "casos", canBeNull = false, dataType = DataType.INTEGER_OBJ)
    private Integer casos;
    @DatabaseField(columnName = "confirmados", canBeNull = false, dataType = DataType.INTEGER_OBJ)
    private Integer confirmados;
    @DatabaseField(columnName = "mortes", canBeNull = false, dataType = DataType.INTEGER_OBJ)
    private Integer mortes;
    @DatabaseField(columnName = "recuperados", canBeNull = false, dataType = DataType.INTEGER_OBJ)
    private Integer recuperados;
    private LocalDateTime data;

    public PaisVO() {
    }

    public PaisVO(String pais, Integer casos, Integer confirmados, Integer mortes, Integer recuperados, LocalDateTime data) {
        this.pais = pais;
        this.casos = casos;
        this.confirmados = confirmados;
        this.mortes = mortes;
        this.recuperados = recuperados;
        this.data = data;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getCasos() {
        return casos;
    }

    public void setCasos(Integer casos) {
        this.casos = casos;
    }

    public Integer getConfirmados() {
        return confirmados;
    }

    public void setConfirmados(Integer confirmados) {
        this.confirmados = confirmados;
    }

    public Integer getMortes() {
        return mortes;
    }

    public void setMortes(Integer mortes) {
        this.mortes = mortes;
    }

    public Integer getRecuperados() {
        return recuperados;
    }

    public void setRecuperados(Integer recuperados) {
        this.recuperados = recuperados;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
