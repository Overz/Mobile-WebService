package com.example.webservice.controller;

import android.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.example.webservice.R;
import com.example.webservice.model.dao.EstadoDAO;
import com.example.webservice.model.dto.EstadoDTO;
import com.example.webservice.model.vo.EstadoVO;
import com.example.webservice.util.AdapterEstados;
import com.example.webservice.view.ConsultasView;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.example.webservice.util.Constantes.ATUALIZAR;
import static com.example.webservice.util.Constantes.CONSULTAR;
import static com.example.webservice.util.Constantes.ESTADOS_FAVORITOS;
import static com.example.webservice.util.Constantes.FAVORITO;
import static com.example.webservice.util.Constantes.SUCCESS;

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
     * OBS: Não tive nome para o método
     * <p>
     * <p>
     * Consulta:
     * consultar no banco, se já existir, mostrar o resultado
     * se estiver null, cadastrar no banco e Exibe na tela.
     * <p>
     * Atualizar:
     * consultar o UF, e sobreescrever os resultados do UF no banco e Exibe na tela.
     *
     * @param tipo int
     */
    public void fazAlgoAction(int tipo) {
        try {
            this.estado = null;
            String uf = (String) activity.getSpnEstados().getSelectedItem();
            if (!uf.equals(ESCOLHA)) {

                if (tipo == CONSULTAR) {
                    this.estado = dao.consultarUf(uf); // Consulta o Objeto no Banco
                    if (estado != null) {
                        Toast.makeText(activity, "Aguarde...", Toast.LENGTH_SHORT).show();
                        Toast.makeText(activity, "Item Já Registrado", Toast.LENGTH_SHORT).show();
                        // Chama o ListView Caso já exista no Banco
                        this.configListView(estado);
                    } else {
                        this.requestApi(uf, tipo); // Se não existir, consulta na API
                    }
                } else if (tipo == ATUALIZAR) {

                    this.estado = null;
                    Toast.makeText(activity, "UF: " + uf + "\nAtualizando Dados pela API", Toast.LENGTH_SHORT).show();
                    this.requestApi(uf, tipo); // Consulta a API para preencher o Objeto

                } else {
                    Toast.makeText(activity, "Ops, Algúm Erro Ocorreu!", Toast.LENGTH_SHORT).show();
                }
            } else {
                estado = null;
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
        ArrayList<EstadoVO> array = new ArrayList<>();
        array.add(estado);
        adapterEstados = new AdapterEstados(activity, array);
        activity.getLvResultado().setAdapter(adapterEstados);
        this.addClickCurto(estado.getUf());
        this.addClickLongo(estado.getUf());
        this.configurarBotao(1);
        System.gc();
    }

    /**
     * Possibilita Favoritar algum Item, dando update no Objeto no banco,
     * e consultando todos os "favoritos", em um Static Array
     *
     * @param uf String
     */
    private void addClickCurto(String uf) {
        activity.getLvResultado().setOnItemClickListener((adapterView, view, i, l) -> {
            this.estado = adapterEstados.getItem(i);
            if (estado != null) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                alerta.setTitle("Favoritar?");
                alerta.setMessage(estado.toString());
                alerta.setIcon(android.R.drawable.star_on);
                alerta.setNegativeButton("Fechar", (dialogInterface, i1) -> estado = null);
                alerta.setPositiveButton("Favoritar", (dialogInterface, i1) -> this.favoritar(uf));
                alerta.show();
            } else {
                Toast.makeText(activity, "Erro ao Tentar Favoritar!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Possibilita desfavoritar algum Item, dando update no Objeto no banco,
     * e consultando todos os "favoritos", em um Static Array
     *
     * @param uf String
     */
    private void addClickLongo(String uf) {
    }

    /**
     * Insere uma Flag 'Favorito' ao Objeto, atualiza no Banco, e Atribui em um Array Constante
     *
     * @param uf String
     */
    private void favoritar(String uf) {
        estado = dao.consultarUf(uf);
        estado.setFavorito(FAVORITO);
        int i = dao.atualizarFavorito(estado);
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
     * Envia uma requisição a API, retornando um JSON.
     * Tratando o JSON para a classe DTO e verifica se uma consulta/cadastro, ou uma atualização dos Dados no Banco.
     *
     * @param uf String
     */
    private void requestApi(String uf, int tipo) {
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
                    String res = new String(responseBody);
                    Gson g = new Gson();
                    EstadoDTO dto = g.fromJson(res, EstadoDTO.class);
                    Log.i("TO_STRING", dto.toString());
                    estado = dto.getEstadoVO();

                    if (tipo == CONSULTAR) {
                        cadastrarAction();
                    } else {
                        Toast.makeText(activity, "Atualizando Dados...", Toast.LENGTH_SHORT).show();
                        EstadoVO oldEstado = dao.consultarUf(uf);
                        atualizarAction(oldEstado.getId());
                    }

                    configListView(estado);

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
     * Cadastra, mostra uma Mensagem de Sucesso
     * Se nao Cadastrar, de Erro.
     */
    private void cadastrarAction() {
        try {
            int i = dao.cadastrar(estado);
            if (i == SUCCESS) {
                Toast.makeText(activity, "Item Registrado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "Item Falhou ao Registrar", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(activity, "Item Já Registrado/ou Falhou", Toast.LENGTH_SHORT).show();
            System.out.println(e.getMessage());
            System.out.println(e.getCause() + "\n");
            e.printStackTrace();
        }
    }

    /**
     * Atualiza os dados no Banco com o da API
     * Se atualizar, mostra uma mensagem de sucesso,
     * se não, de erro
     *
     * @param id Integer
     */
    private void atualizarAction(Integer id) {
        try {
            estado.setId(id);
            int i = dao.atualizar(estado);
            if (i == SUCCESS) {
                Thread.sleep(1000);
                Toast.makeText(activity, "Dados Atualizados!\n UF: " + estado.getUf() + " - Estado: " + estado.getEstado(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "Erro ao Atualizar/Nada Ao Alterar", Toast.LENGTH_SHORT).show();
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
