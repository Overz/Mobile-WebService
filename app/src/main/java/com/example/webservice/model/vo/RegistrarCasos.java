package com.example.webservice.model.vo;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.jetbrains.annotations.NotNull;

@DatabaseTable(tableName = "casosInseridos")
public class RegistrarCasos {

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true, dataType = DataType.INTEGER_OBJ)
    private Integer id;
    @DatabaseField(columnName = "casos", dataType = DataType.INTEGER_OBJ)
    private Integer casos;
    @DatabaseField(columnName = "casosSuspeitos", dataType = DataType.INTEGER_OBJ)
    private Integer casosSuspeitos;
    @DatabaseField(columnName = "casosConfirmados", dataType = DataType.INTEGER_OBJ)
    private Integer casosConfirmados;
    @DatabaseField(columnName = "mortes", dataType = DataType.INTEGER_OBJ)
    private Integer mortes;
    @DatabaseField(columnName = "recuperados", dataType = DataType.INTEGER_OBJ)
    private Integer recuperados;

    public RegistrarCasos() {
    }

    public RegistrarCasos(Integer id, Integer casos, Integer casosSuspeitos, Integer casosConfirmados, Integer mortes, Integer recuperados) {
        this.id = id;
        this.casos = casos;
        this.casosSuspeitos = casosSuspeitos;
        this.casosConfirmados = casosConfirmados;
        this.mortes = mortes;
        this.recuperados = recuperados;
    }

    public RegistrarCasos(Integer casos, Integer casosSuspeitos, Integer casosConfirmados, Integer mortes, Integer recuperados) {
        this.casos = casos;
        this.casosSuspeitos = casosSuspeitos;
        this.casosConfirmados = casosConfirmados;
        this.mortes = mortes;
        this.recuperados = recuperados;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCasos() {
        return casos;
    }

    public void setCasos(Integer casos) {
        this.casos = casos;
    }

    public Integer getCasosSuspeitos() {
        return casosSuspeitos;
    }

    public void setCasosSuspeitos(Integer casosSuspeitos) {
        this.casosSuspeitos = casosSuspeitos;
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

    public Integer getRecuperados() {
        return recuperados;
    }

    public void setRecuperados(Integer recuperados) {
        this.recuperados = recuperados;
    }

    @NotNull
    @Override
    public String toString() {
        return "Casos: " + casos + " - Suspeitos: " + casosSuspeitos + " - Confirmados: " + casosConfirmados + "\n - Mortes: " + mortes + " Recuperados: " + recuperados;
    }
}
