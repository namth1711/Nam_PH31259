package com.namth.nam_ph31259;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThemSua extends AppCompatActivity {
    public static final String KEY_TEN = "ten";

    public static final String KEY_HANG = "hang";

    public static final String KEY_NAM_SX = "namsx";

    public static final String KEY_GIA = "gia";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sua);
        EditText edthang = findViewById(R.id.edthang);
        EditText edtten = findViewById(R.id.edtten);
        EditText edtnamsx =findViewById(R.id.edtnamsx);
        EditText edtgia = findViewById(R.id.edtgia);
        Button btnsumbmit = findViewById(R.id.btnsumbmit);
        btnsumbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hang = edthang.getText().toString();
                String ten = edtten.getText().toString();
                String namsxStr = edtnamsx.getText().toString();
                String giaStr = edtgia.getText().toString();

                if (ten.isEmpty()) {
                    Toast.makeText(ThemSua.this, "Tên không để trống", Toast.LENGTH_SHORT).show();
                }else if (hang.isEmpty()) {
                    Toast.makeText(ThemSua.this, "Hãng không để trống", Toast.LENGTH_SHORT).show();
                }else if (namsxStr.isEmpty()) {
                    Toast.makeText(ThemSua.this, "Năm sản xuất không để trống", Toast.LENGTH_SHORT).show();
                }else if ( giaStr.isEmpty()) {
                    Toast.makeText(ThemSua.this, "Giá không để trống", Toast.LENGTH_SHORT).show();
                } else {

                    int namsx;
                    try {
                        namsx = Integer.parseInt(namsxStr);
                    } catch (NumberFormatException e) {
                        Toast.makeText(ThemSua.this, "Năm sản xuất không hợp lệ", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int gia;
                    try {
                        gia = Integer.parseInt(giaStr);
                    } catch (NumberFormatException e) {
                        Toast.makeText(ThemSua.this, "Giá không hợp lệ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (namsx<=1980 || namsx>=2023){
                        Toast.makeText(ThemSua.this, "Năm phải lớn hơn 1980 và nhỏ hơn 2023", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent i = new Intent();
                        i.putExtra(KEY_TEN, ten);
                        i.putExtra(KEY_HANG, hang);
                        i.putExtra(KEY_NAM_SX, namsxStr);
                        i.putExtra(KEY_GIA, giaStr);
                        setResult(RESULT_OK, i);
                        finish();
                    }
                }
            }
        });
    }
}