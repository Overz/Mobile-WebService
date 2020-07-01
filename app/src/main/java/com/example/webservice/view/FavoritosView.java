package com.example.webservice.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.webservice.R;
import com.example.webservice.model.dao.EstadoDAO;
import com.example.webservice.model.vo.EstadoVO;
import com.j256.ormlite.dao.Dao;

import java.util.List;

import static com.example.webservice.util.Constantes.NAO_FAVORITO;
import static com.example.webservice.util.Constantes.TIPO_TOSTRING;

public class FavoritosView extends AppCompatActivity {

    private ArrayAdapter<EstadoVO> adapter;
    private List<EstadoVO> list;
    private ListView lvResultado;
    private EstadoVO estado;
    private EstadoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos_view);
        this.init();
        this.confitListView();
    }

    private void init() {
        this.estado = new EstadoVO();
        this.dao = new EstadoDAO(this, EstadoVO.class);
        this.lvResultado = findViewById(R.id.lvResultadoFavoritos);
    }

    private void confitListView() {
        try {
            TIPO_TOSTRING = 1;
            list = dao.consultarFavoritos();
            adapter = new ArrayAdapter<>(
                    this,
                    R.layout.layout_tv_white_text,
                    list
            );
            adapter.notifyDataSetChanged();
            lvResultado.setAdapter(adapter);

            this.configClickCurto();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void configClickCurto() {
        try {
            lvResultado.setOnItemClickListener((AdapterView.OnItemClickListener) (adapterView, view, i, l) -> {
                estado = (EstadoVO) adapterView.getItemAtPosition(i);
                if (estado != null) {
                    AlertDialog.Builder alerta = new AlertDialog.Builder(this);
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
                            Toast.makeText(this, "Item Removido de Favoritos!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Erro ao Tentar Remover de Favoritos!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    alerta.show();
                } else {
                    Toast.makeText(this, "Erro ao selecionar o Item", Toast.LENGTH_SHORT).show();
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
            adapter.clear();
            list = dao.consultarFavoritos();
            adapter.notifyDataSetChanged();
        }
    }

}