package com.example.webservice.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webservice.R;
import com.example.webservice.model.vo.EstadoVO;

import java.util.List;

public class AdapterEstados extends BaseAdapter {

    private List<EstadoVO> estadosList;
    private LayoutInflater inflater;
    private Context context;

    public AdapterEstados(Context context, List<EstadoVO> list) {
        this.estadosList = list;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.notifyDataSetChanged();
    }

    public void clearList() {
        estadosList.clear();
        notifyDataSetChanged();
    }

    public void add(EstadoVO e) {
        estadosList.add(e);
        notifyDataSetChanged();
    }

    public void remover(EstadoVO e) {
        estadosList.remove(e);
        notifyDataSetChanged();
    }

    public void remover(int i) {
        if (estadosList.size() > 0) {
            estadosList.remove(i);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return estadosList.size();
    }

    @Override
    public EstadoVO getItem(int i) {
        return estadosList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            // Pegar o estado a cada chamada do método para preencher o adapter
            view = inflater.inflate(R.layout.lv_personal_2, null);

            for (EstadoVO estado : estadosList) {
                this.lvPersonal_2(view, estado);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause() + "\n");
            e.printStackTrace();
        }
        return view;
    }

    /**
     * Preenche os campos da tela com os parametros passados
     *
     * @param view View
     * @param e    EstadoVO
     */
    private void lvPersonal_2(View view, EstadoVO e) {
        try {
            TextView tvCasos = view.findViewById(R.id.tvTotalCasosPersonal);
            TextView tvSuspeitos = view.findViewById(R.id.tvTotalSuspeitosPersonal);
            TextView tvConfirmados = view.findViewById(R.id.tvTotalConfirmadosPersonal);
            TextView tvMortes = view.findViewById(R.id.tvTotalMortesPersonal);
            TextView tvRecuperados = view.findViewById(R.id.tvTotalRecuperadosPersonal);

            tvSuspeitos.setText(String.valueOf(e.getSuspeitos()));
            tvConfirmados.setText(String.valueOf(e.getCasosConfirmados()));
            tvMortes.setText(String.valueOf(e.getMortes()));

            this.mudarStringLayout(tvCasos, tvSuspeitos, tvConfirmados, tvMortes, tvRecuperados);
        } catch (Exception ex) {
            Toast.makeText(context, "Erro ao Preencher os Campos", Toast.LENGTH_SHORT).show();
            System.out.println(ex.getMessage());
            System.out.println(ex.getCause() + "\n");
            ex.printStackTrace();
        }
    }

    /**
     * Compara Strings da tela para mudar se for true
     *
     * @param tvCasos       TextView
     * @param tvSuspeitos   TextView
     * @param tvConfirmados TextView
     * @param tvMortes      TextView
     * @param tvRecuperados TextView
     */
    private void mudarStringLayout(TextView tvCasos, TextView tvSuspeitos, TextView tvConfirmados,
                                   TextView tvMortes, TextView tvRecuperados) {
        String comparator = context.getResources().getString(R.string.wait);
        if (tvCasos.getText().equals(comparator) || tvCasos.getText().equals("")) {
            tvCasos.setText(R.string.semInfo);
        }
        if (tvSuspeitos.getText().equals(comparator) || tvSuspeitos.getText().equals("")) {
            tvSuspeitos.setText(R.string.semInfo);
        }
        if (tvConfirmados.getText().equals(comparator) || tvConfirmados.getText().equals("")) {
            tvConfirmados.setText(R.string.semInfo);
        }
        if (tvMortes.getText().equals(comparator) || tvMortes.getText().equals("")) {
            tvMortes.setText(R.string.semInfo);
        }
        if (tvRecuperados.getText().equals(comparator) || tvRecuperados.getText().equals("")) {
            tvRecuperados.setText(R.string.semInfo);
        }
    }
}
