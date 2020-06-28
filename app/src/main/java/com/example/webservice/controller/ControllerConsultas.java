package com.example.webservice.controller;

import android.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.example.webservice.model.dao.EstadoDAO;
import com.example.webservice.model.dto.EstadoDTO;
import com.example.webservice.model.vo.EstadoVO;
import com.example.webservice.util.AdapterEstados;
import com.example.webservice.view.ConsultasView;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

import static com.example.webservice.util.Constantes.ESTADOS_FAVORITOS;
import static com.example.webservice.util.Constantes.FAVORITO;
import static com.example.webservice.util.Constantes.SUCCESS;

public class ControllerConsultas {

    private ConsultasView activity;
    private AdapterEstados adapterEstados;
    private EstadoVO estado;
    private EstadoDAO dao;
    private static final String ESCOLHA = "Escolha o Estado";

    public ControllerConsultas(ConsultasView activity) {
        this.activity = activity;
    }

    /**
     * Configura o ListView na Exibição dos Resultados após consultar a API
     * & Adiciona o ListView Personalizado a tela com os valores do JSON
     *
     * @param estado EstadoVO
     */
    private void configListView(EstadoVO estado) {
        ArrayList<EstadoVO> array = new ArrayList<>();
        array.add(estado);
        adapterEstados = new AdapterEstados(activity, array, activity.getLayout());
        activity.getLvResultado().setAdapter(adapterEstados);
        this.addClickCurto();
        this.configurarBotao(1);
    }

    /**
     * Possibilita Favoritar algum Item;
     */
    private void addClickCurto() {
        activity.getLvResultado().setOnItemClickListener((adapterView, view, i, l) -> {
            this.estado = adapterEstados.getItem(i);
            if (estado != null) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                alerta.setTitle("Favoritar?");
                alerta.setMessage(estado.toString());
                alerta.setIcon(android.R.drawable.star_on);
                alerta.setNegativeButton("Fechar", (dialogInterface, i1) -> estado = null);
                alerta.setPositiveButton("Favoritar", (dialogInterface, i1) -> this.favoritar());
                alerta.show();
            } else {
                Toast.makeText(activity, "Erro ao Tentar Favoritar!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Insere uma Flag 'Favorito' ao Objeto, atualiza no Banco, e Atribui em um Array Constante
     */
    private void favoritar() {
        estado.setFavorito(FAVORITO);

        int i = dao.favoritar(estado);
        if (i == SUCCESS) {
            Toast.makeText(activity, "Favoritado!", Toast.LENGTH_SHORT).show();

            ArrayList<EstadoVO> daoReturn = dao.consultarFavoritos();
            if (daoReturn != null) {
                ESTADOS_FAVORITOS = daoReturn;
            }

        } else {
            Toast.makeText(activity, "Erro ao Tentar Favoritar", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Envia uma requisição para a API e Retorna um Json
     *
     * @param uf String
     */
    public void requestApi(String uf) {
        try {
            if (!uf.equals(ESCOLHA)) {
                AsyncHttpClient client = new AsyncHttpClient();
                String url = "https://covid19-brazil-api.now.sh/api/report/v1/brazil/uf/" + uf;
                client.get(url, new AsyncHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        Toast.makeText(activity, "Aguarde...", Toast.LENGTH_SHORT).show();
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
            } else {
                Toast.makeText(activity, "Escolha um Estado!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.i("<<COVID-API>>", "Erro ao Consumir API");
            Log.i("<<EXCEPTION>>", Objects.requireNonNull(e.getMessage()));
            Toast.makeText(activity, "Erro ao Consumir a API!", Toast.LENGTH_SHORT).show();
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

    /**
     * Trata o Objeto JSON com GSON, preenchendo EstadosVO já tratado e atualizando os valores da Tela
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
        this.cadastrar();
    }

    /**
     * Tenta cadastrar no Banco o Objeto consultado da API
     * Se cadastrar, mostra uma Mensagem de Sucesso
     * Se nao Cadastrar, de Erro.
     */
    private void cadastrar() {
        try {
            int i = dao.cadastrar(estado);
            if (i == SUCCESS) {
                Toast.makeText(activity, "Item Registrado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "Item Já Registrado/Falhou", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause() + "\n");
            e.printStackTrace();
        }
    }

    /**
     * Limpa o Adapter e a List na classe Adapter
     */
    public void limparAction() {
        int size = adapterEstados.getCount();
        for (int i = 0; i < size; i++) {
            adapterEstados.remover(i);
        }
        adapterEstados.clearList();
    }

}
