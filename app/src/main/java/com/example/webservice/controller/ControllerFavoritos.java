package com.example.webservice.controller;

import android.widget.ArrayAdapter;

import com.example.webservice.model.dao.EstadoDAO;
import com.example.webservice.model.vo.EstadoVO;
import com.example.webservice.view.FavoritosView;

public class ControllerFavoritos {

    private FavoritosView activity;
    private EstadoDAO dao;

    public ControllerFavoritos(FavoritosView activity) {
        this.activity = activity;
        this.dao = new EstadoDAO(activity, EstadoVO.class);
        this.configListView();
    }

    private void configListView() {
        ArrayAdapter<EstadoVO> adapter = new ArrayAdapter<>(
                activity,
                android.R.layout.simple_list_item_1,
                dao.consultarFavoritos()
        );
        adapter.notifyDataSetChanged();
        activity.getLvFavoritos().setAdapter(adapter);
    }

    private void addClickCurto() {
    }

    private void addClickLongo() {
    }

}
