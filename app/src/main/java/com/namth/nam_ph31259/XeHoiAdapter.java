package com.namth.nam_ph31259;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class XeHoiAdapter extends BaseAdapter {
    List<XeHoi> list =new ArrayList<>();
    Context c;

    public XeHoiAdapter(List<XeHoi> list, Context c) {
        this.list = list;
        this.c = c;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inf = ((Activity)c).getLayoutInflater();
        view = inf.inflate(R.layout.item_xehoi,null);
        TextView txtnamsx = view.findViewById(R.id.txtnamsx);
        TextView txtten = view.findViewById(R.id.txtten);
        TextView txthang = view.findViewById(R.id.txthang);
        TextView txtgia = view.findViewById(R.id.txtgia);
        Button btnsua = view.findViewById(R.id.btn_sua);
        Button btnxoa = view.findViewById(R.id.btn_xoa);
        txtnamsx.setText(String.valueOf(list.get(i).getNamsx()));
        txtten.setText(list.get(i).getTen());
        txthang.setText(list.get(i).getHang());
        txtgia.setText(String.valueOf(list.get(i).getGia()));
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MH2)c).setSua(i);
            }
        });
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MH2)c).xoa(i);
            }
        });

        return view;
    }
}
