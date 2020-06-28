package com.example.webservice.model.dao;

import android.content.Context;

import com.example.webservice.model.banco.DaoHelper;
import com.example.webservice.model.vo.EstadoVO;

import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.webservice.util.Constantes.FAILD;
import static com.example.webservice.util.Constantes.SUCCESS;

public class EstadoDAO extends DaoHelper<EstadoVO> {
    public EstadoDAO(Context c, Class className) {
        super(c, className);
    }

    public int favoritar(EstadoVO estado) {
        try {
            return getDao().update(estado);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return FAILD;
    }


    public int desfavoritar() {

        return 0;
    }

    public int cadastrar(EstadoVO estado) {
        try {
            Object o = getDao().createIfNotExists(estado);
            if (o != null) {
                return SUCCESS;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return FAILD;
    }

    public ArrayList<EstadoVO> consultarFavoritos() {
        try {
            return (ArrayList<EstadoVO>) getDao().queryBuilder().where().eq("favorito", "1").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
