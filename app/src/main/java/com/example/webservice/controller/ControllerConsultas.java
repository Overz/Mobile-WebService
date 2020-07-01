package com.example.webservice.controller;

import android.app.AlertDialog;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.webservice.R;
import com.example.webservice.model.dao.EstadoDAO;
import com.example.webservice.model.dto.EstadoDTO;
import com.example.webservice.model.vo.EstadoVO;
import com.example.webservice.util.AdapterEstados;
import com.example.webservice.view.ConsultasView;
import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.example.webservice.util.Constantes.DESATIVADO;
import static com.example.webservice.util.Constantes.FAVORITO;
import static com.example.webservice.util.Constantes.NAO_FAVORITO;
import static com.example.webservice.util.Constantes.TIPO_TOSTRING;

public class ControllerConsultas {

    private static final String ESCOLHA = "Escolha o Estado";
    private ConsultasView activity;
    private AdapterEstados adapterEstados;
    private EstadoVO estado;
    private EstadoDAO dao;

    public ControllerConsultas(ConsultasView activity) {
        this.activity = activity;
        dao = new EstadoDAO(activity, EstadoVO.class);
    }

    /**
     * Consulta:
     * consultar no banco, se já existir e se for favorito, mostrar o resultado.
     * Se estiver null, cadastrar no banco e Exibe na tela.
     */
    public void consultarAction() {
        try {
            this.estado = null;
            String uf = (String) activity.getSpnEstados().getSelectedItem();

            if (!uf.equals(ESCOLHA)) {
                EstadoVO estado = dao.consultarUf(uf);

                if (estado != null) {
                    this.configListView(estado);
                } else {
                    this.requestApi(uf); // Consulta a API para preencher o Objeto
                }
            } else {
                Toast.makeText(activity, "Escolha um Estado!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause() + "\n");
            e.printStackTrace();
        }
    }

    /**
     * Configura o ListView na Exibição dos Resultados após consultar a API
     * & Adiciona o ListView Personalizado a tela com os valores do JSON
     *
     * @param estado EstadoVO
     */
    private void configListView(EstadoVO estado) {
        Toast.makeText(activity, "Consultado!", Toast.LENGTH_LONG).show();
        ArrayList<EstadoVO> array = new ArrayList<>();
        array.add(estado);
        adapterEstados = new AdapterEstados(activity, array, DESATIVADO);
        activity.getLvResultado().setAdapter(adapterEstados);
        this.addClickCurto();
        this.addClickLongo();
        this.configurarBotao(1);
        TIPO_TOSTRING = 0;
        System.gc();
    }

    /**
     * Possibilita Favoritar algum Item, dando update no Objeto no banco.
     */
    private void addClickCurto() {
        activity.getLvResultado().setOnItemClickListener((adapterView, view, i, l) -> {
            this.estado = adapterEstados.getItem(i);
            if (estado != null) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                alerta.setTitle("Deseja Favoritar esse Item?");
                TIPO_TOSTRING = 1;
                alerta.setMessage(estado.toString());
                alerta.setIcon(android.R.drawable.star_on);
                alerta.setNegativeButton("Fechar", (dialogInterface, i1) -> this.estado = null);
                alerta.setPositiveButton("Ok", (dialogInterface, i1) -> {
                    estado.setFavorito(FAVORITO);
                    this.updateEstado(estado);
                });
                alerta.show();
            } else {
                Toast.makeText(activity, "Erro ao Tentar Favoritar!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Possibilita desfavoritar algum Item, dando update no Objeto no banco.
     */
    private void addClickLongo() {
        activity.getLvResultado().setOnItemLongClickListener((AdapterView.OnItemLongClickListener) (adapterView, view, i, l) -> {
            estado = adapterEstados.getItem(i);
            if (estado != null) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                alerta.setTitle("Remover dos Favoritos?");
                TIPO_TOSTRING = 1;
                alerta.setMessage(estado.toString());
                alerta.setIcon(android.R.drawable.star_on);
                alerta.setNegativeButton("Fechar", (dialogInterface, i1) -> estado = null);
                alerta.setPositiveButton("Ok", (dialogInterface, i1) -> {
                    estado.setFavorito(NAO_FAVORITO);
                    updateEstado(estado);
                });
                alerta.show();
            } else {
                Toast.makeText(activity, "Erro ao Tentar Favoritar!", Toast.LENGTH_SHORT).show();
            }
            return true;
        });
    }

    /**
     * Envia uma requisição a API, retornando um JSON.
     * Tratando o JSON para a classe DTO e verifica se uma consulta, ou uma atualização dos Dados no Banco.
     *
     * @param uf String
     */
    private void requestApi(String uf) {
        try {
            AsyncHttpClient client = new AsyncHttpClient();
            String url = "https://covid19-brazil-api.now.sh/api/report/v1/brazil/uf/" + uf;
            client.get(url, new AsyncHttpResponseHandler() {
                @Override
                public void onStart() {
                    super.onStart();
                    Toast.makeText(activity, activity.getString(R.string.wait), Toast.LENGTH_SHORT).show();
                    ControllerConsultas.this.configurarBotao(0);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    ControllerConsultas.this.tratarJson(statusCode, headers, responseBody);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast.makeText(activity, "Falha na Requisição", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            Toast.makeText(activity, "Erro ao Consumir a API!", Toast.LENGTH_SHORT).show();
            Log.i("<<COVID-API>>", "Erro ao Consumir API");
            System.out.println(e.getMessage() + "\n"
                    + e.getCause() + "\n"
                    + e.getClass().getSimpleName()
            );
        }
    }

    /**
     * Método Auxiliar para a Requisição da API
     * Trata o Objeto e configura o ListView
     *
     * @param statusCode   int
     * @param headers      Header[]
     * @param responseBody byte[]
     */
    private void tratarJson(int statusCode, Header[] headers, byte[] responseBody) {
        String res = new String(responseBody);
        Gson g = new Gson();
        EstadoDTO dto = g.fromJson(res, EstadoDTO.class);
        Log.i("TO_STRING", dto.toString());
        estado = dto.getEstadoVO();
        this.configListView(estado);
    }

    /**
     * Cadastra/Atualiza mostrando uma Mensagem de Sucesso.
     * Se nao, de Erro.
     *
     * @param estado EstadoVO
     */
    private void updateEstado(EstadoVO estado) {
        try {
            Dao.CreateOrUpdateStatus status = dao.atualizar(estado);
            if (status.isCreated()) {
                Toast.makeText(activity, "Item Cadastrado!", Toast.LENGTH_SHORT).show();
            } else if (status.isUpdated()) {
                Toast.makeText(activity, "Item Atualizado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "Erro ao Atualizar o Item!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(activity, "Erro ao atualizar o estado!!!", Toast.LENGTH_SHORT).show();
            System.out.println(e.getMessage());
            System.out.println(e.getCause() + "\n");
            e.printStackTrace();
        }
    }

    /**
     * Limpa o Adapter e o List na classe Adapter
     */
    public void limparAction() {
        try {
            if (adapterEstados != null) {
                int size = adapterEstados.getCount();
                for (int i = 0; i < size; i++) {
                    adapterEstados.remover(i);
                }
                adapterEstados.clearList();
            } else {
                Toast.makeText(activity, "Limpando...", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause() + "\n");
            e.printStackTrace();
        }
    }

    /**
     * Ativa e Desativa os botões enquanto consultando a API
     *
     * @param i int
     */
    private void configurarBotao(int i) {
        if (i == 0) {
            activity.getBtnConsultar().setEnabled(false);
            activity.getBtnLimpar().setEnabled(false);
        } else {
            activity.getBtnConsultar().setEnabled(true);
            activity.getBtnLimpar().setEnabled(true);
        }
    }

}
