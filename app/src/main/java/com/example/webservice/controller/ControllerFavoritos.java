package com.example.webservice.controller;

import android.app.AlertDialog;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.webservice.R;
import com.example.webservice.model.dao.EstadoDAO;
import com.example.webservice.model.vo.EstadoVO;
import com.example.webservice.view.FavoritosView;
import com.j256.ormlite.dao.Dao;

import java.util.ArrayList;

import static com.example.webservice.util.Constantes.NAO_FAVORITO;
import static com.example.webservice.util.Constantes.TIPO_TOSTRING;

public class ControllerFavoritos {

    private FavoritosView activity;
    private EstadoDAO dao;
    private ArrayList<EstadoVO> list;
    private ArrayAdapter<EstadoVO> adapter;
    private EstadoVO estado;

    public ControllerFavoritos(FavoritosView activity) {
        this.dao = new EstadoDAO(activity, EstadoVO.class);
        this.activity = activity;
        this.configListView();
    }

    private void configListView() {
        list = dao.consultarFavoritos();
        TIPO_TOSTRING = 1;
        adapter = new ArrayAdapter<>(
                activity,
                R.layout.layout_tv_white_text,
                dao.consultarFavoritos()
        );
        adapter.notifyDataSetChanged();
        activity.getLvResultado().setAdapter(adapter);

        this.configClickCurto();
    }

    private void configClickCurto() {
        try {
            activity.getLvResultado().setOnItemClickListener((AdapterView.OnItemClickListener) (adapterView, view, i, l) -> {
                estado = (EstadoVO) adapterView.getItemAtPosition(i);
                if (estado != null) {
                    AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                    alerta.setTitle("Remover dos Favoritos?");
                    TIPO_TOSTRING = 1;
                    alerta.setMessage(estado.toString());
                    alerta.setIcon(android.R.drawable.star_on);
                    alerta.setNegativeButton("Fechar", (dialogInterface, i1) -> estado = null);
                    alerta.setPositiveButton("Ok", (dialogInterface, i1) -> {
                        estado.setFavorito(NAO_FAVORITO);
                        Dao.CreateOrUpdateStatus status = dao.atualizar(estado);
                        if (status.isUpdated()) {
                            refreshData(1);
                            Toast.makeText(activity, "Item Removido de Favoritos!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(activity, "Erro ao Tentar Remover de Favoritos!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    alerta.show();
                } else {
                    Toast.makeText(activity, "Erro ao selecionar o Item", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void refreshData(int tipo) {
        if (tipo == 0) {
            adapter.notifyDataSetChanged();
        }
        if (tipo == 1) {
            list.clear();
            adapter.clear();
            list = dao.consultarFavoritos();
            adapter.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }
}
