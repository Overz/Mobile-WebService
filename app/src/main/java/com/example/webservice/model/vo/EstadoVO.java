package com.example.webservice.model.vo;

import com.example.webservice.util.Constantes;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@DatabaseTable(tableName = "Estado")
public class EstadoVO {

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true, dataType = DataType.INTEGER_OBJ)
    private Integer id;
    @DatabaseField(columnName = "uId", canBeNull = false, dataType = DataType.INTEGER_OBJ)
    private Integer uId;
    @DatabaseField(columnName = "uf", canBeNull = false, dataType = DataType.STRING, width = 2)
    private String uf;
    @DatabaseField(columnName = "nomeProduto", canBeNull = false, dataType = DataType.STRING, width = 50)
    private String estado;
    @DatabaseField(columnName = "casosConfirmados", canBeNull = false, dataType = DataType.INTEGER_OBJ)
    private Integer casosConfirmados;
    @DatabaseField(columnName = "mortes", canBeNull = false, dataType = DataType.INTEGER_OBJ)
    private Integer mortes;
    @DatabaseField(columnName = "suspeitos", canBeNull = false, dataType = DataType.INTEGER_OBJ)
    private Integer suspeitos;
    @DatabaseField(columnName = "ignorados", canBeNull = false, dataType = DataType.INTEGER_OBJ)
    private Integer ignorados;
    @DatabaseField(columnName = "data", canBeNull = false, dataType = DataType.DATE_STRING, width = 50)
    private String data;
    @DatabaseField(columnName = "favorito", canBeNull = false, dataType = DataType.INTEGER_OBJ)
    private Integer favorito;

    public EstadoVO() {
    }

    public EstadoVO(Integer uId, String uf, String estado, Integer casosConfirmados,
                    Integer mortes, Integer suspeitos, Integer ignorados, String data) {
        this.uId = uId;
        this.uf = uf;
        this.estado = estado;
        this.casosConfirmados = casosConfirmados;
        this.mortes = mortes;
        this.suspeitos = suspeitos;
        this.ignorados = ignorados;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
            LocalDateTime data = LocalDateTime.parse(this.data.replace("Z", ""));
            return "UF: " + uf + " -  ESTADO: " + estado + "\n CASOS: " + casosConfirmados +
                    " - MORTES: " + mortes + "\n SUSPEITOS: " + suspeitos + "\n DATA: " + data.format(Constantes.DTF);
        } catch (Exception e) {
            throw new NullPointerException("Erro no TO_STRING de EstadoVO");
        }
    }
}
