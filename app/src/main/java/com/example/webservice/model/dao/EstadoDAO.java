package com.example.webservice.model.dao;

import android.content.Context;

import com.example.webservice.model.banco.DaoHelper;
import com.example.webservice.model.vo.EstadoVO;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

public class EstadoDAO extends DaoHelper<EstadoVO> {

    public EstadoDAO(Context c, Class className) {
        super(c, className);
    }

    public Dao.CreateOrUpdateStatus atualizar(EstadoVO estado) {
        try {
            EstadoVO oldEstado = getDao().queryBuilder().where().eq("uf", estado.getUf()).queryForFirst();
            if (oldEstado != null) {
                estado.setId(oldEstado.getId());
            }
            return getDao().createOrUpdate(estado);
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n"
                    + e.getCause() + "\n"
                    + e.getNextException() + "\n"
                    + e.getClass().getSimpleName()
            );
        }
        return null;
    }

    public EstadoVO consultarUf(String uf) {
        try {
            return getDao().queryBuilder().where().eq("uf", uf).and().eq("favorito", 1).queryForFirst();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n"
                    + e.getCause() + "\n"
                    + e.getNextException() + "\n"
                    + e.getClass().getSimpleName()
            );
        }
        return null;
    }

    public ArrayList<EstadoVO> consultarFavoritos() {
        try {
            return (ArrayList<EstadoVO>) getDao().queryBuilder().where().eq("favorito", 1).query();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n"
                    + e.getCause() + "\n"
                    + e.getNextException() + "\n"
                    + e.getClass().getSimpleName()
            );
        }
        return null;
    }
}
