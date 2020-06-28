package com.example.webservice.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.webservice.R;
import com.example.webservice.model.vo.PaisVO;

import java.util.List;

public class AdapterPaises extends BaseAdapter {

    private List<PaisVO> paisesList;
    private LayoutInflater inflater;

    public AdapterPaises(Context context, List<PaisVO> paisesList) {
        this.paisesList = paisesList;
        this.inflater = LayoutInflater.from(context);
    }

    public void add(PaisVO p) {
        paisesList.add(p);
        notifyDataSetChanged();
    }

    public void remover(PaisVO p) {
        paisesList.remove(p);
        notifyDataSetChanged();
    }

    public void remover(int i) {
        paisesList.remove(i);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return paisesList.size();
    }

    @Override
    public Object getItem(int i) {
        return paisesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            // Pegar o pais a cada chamada do método para preencher o adapter
            PaisVO pais = paisesList.get(i);
            view = inflater.inflate(R.layout.lv_personal_2, null);

//            TextView header = view.findViewById(R.id.lvHeader);
//            TextView tvCasos = view.findViewById(R.id.tvItem1);
//            TextView tvConfirm = view.findViewById(R.id.tvItem2);
//            TextView tvRecup = view.findViewById(R.id.tvItem3);
//            TextView tvMortes = view.findViewById(R.id.tvItem4);
//
//            header.setText(pais.getPais());
//            tvCasos.setText(pais.getCasos());
//            tvConfirm.setText(pais.getConfirmados());
//            tvRecup.setText(pais.getRecuperados());
//            tvMortes.setText(pais.getMortes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}
