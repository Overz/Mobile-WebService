package com.example.webservice.model.vo;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.jetbrains.annotations.NotNull;

@DatabaseTable(tableName = "estado")
public class EstadoVO {

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true, dataType = DataType.INTEGER_OBJ)
    private Integer id;
    @DatabaseField(columnName = "uId", dataType = DataType.INTEGER_OBJ)
    private Integer uId;
    @DatabaseField(columnName = "uf", unique = true, canBeNull = false, dataType = DataType.STRING, width = 2)
    private String uf;
    @DatabaseField(columnName = "nomeProduto", canBeNull = false, dataType = DataType.STRING)
    private String estado;
    @DatabaseField(columnName = "casosConfirmados", canBeNull = false, dataType = DataType.INTEGER_OBJ)
    private Integer casosConfirmados;
    @DatabaseField(columnName = "mortes", canBeNull = false, dataType = DataType.INTEGER_OBJ)
    private Integer mortes;
    @DatabaseField(columnName = "suspeitos", canBeNull = false, dataType = DataType.INTEGER_OBJ)
    private Integer suspeitos;
    @DatabaseField(columnName = "ignorados", canBeNull = false, dataType = DataType.INTEGER_OBJ)
    private Integer ignorados;
    @DatabaseField(columnName = "dataString", canBeNull = false, dataType = DataType.STRING)
    private String dataString;
    @DatabaseField(columnName = "favorito", dataType = DataType.INTEGER_OBJ)
    private Integer favorito;

    public EstadoVO() {
    }

    public EstadoVO(Integer uId, String uf, String estado, Integer casosConfirmados,
                    Integer mortes, Integer suspeitos, Integer ignorados, String dataString) {
        this.uId = uId;
        this.uf = uf;
        this.estado = estado;
        this.casosConfirmados = casosConfirmados;
        this.mortes = mortes;
        this.suspeitos = suspeitos;
        this.ignorados = ignorados;
        this.dataString = dataString;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCasosConfirmados() {
        return casosConfirmados;
    }

    public void setCasosConfirmados(Integer casosConfirmados) {
        this.casosConfirmados = casosConfirmados;
    }

    public Integer getMortes() {
        return mortes;
    }

    public void setMortes(Integer mortes) {
        this.mortes = mortes;
    }

    public Integer getSuspeitos() {
        return suspeitos;
    }

    public void setSuspeitos(Integer suspeitos) {
        this.suspeitos = suspeitos;
    }

    public Integer getIgnorados() {
        return ignorados;
    }

    public void setIgnorados(Integer ignorados) {
        this.ignorados = ignorados;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    public Integer getFavorito() {
        return favorito;
    }

    public void setFavorito(Integer favorito) {
        this.favorito = favorito;
    }

    @NotNull
    @Override
    public String toString() throws NullPointerException {
        try {
            return "UF: " + uf + " -  ESTADO: " + estado + "\n CASOS: " + casosConfirmados +
                    " - MORTES: " + mortes + "\n SUSPEITOS: " + suspeitos + "\n DATA: " + dataString;
        } catch (Exception e) {
            throw new NullPointerException("Erro no TO_STRING de EstadoVO: " + e.getMessage());
        }
    }
}
