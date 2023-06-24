package com.namth.nam_ph31259;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MH2 extends AppCompatActivity {

    ListView lv_khach;
    List<XeHoi> list = new ArrayList<>();
    XeHoiAdapter adapter = new XeHoiAdapter(list,this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mh2);
        lv_khach = findViewById(R.id.lv_xehoi);
        Button btnthem = findViewById(R.id.btn_them);
        Toolbar toolbar = findViewById(R.id.toobar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        docfile();
        if (list.isEmpty()){
            list.add(new XeHoi("nin", "mai",2017,23332520));
            list.add(new XeHoi("sh", "kia",2020,28235320));
            list.add(new XeHoi("gabo", "moi",2012,51324323));
            list.add(new XeHoi("saki", "mot",2013,20323443));
            list.add(new XeHoi("lai", "king",2015,37525435));}
        fill();
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                them.launch(new Intent(MH2.this,ThemSua.class));
            }
        });
    }
    ActivityResultLauncher<Intent> them = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK){
                        Intent i = result.getData();
                        Bundle b = i.getExtras();
                        String hang = b.getString(ThemSua.KEY_HANG);
                        String ten = b.getString(ThemSua.KEY_TEN);
                        int namsx = Integer.parseInt(b.getString(ThemSua.KEY_NAM_SX));
                        int gia = Integer.parseInt(b.getString(ThemSua.KEY_GIA));
                        list.add(new XeHoi(ten,hang,namsx,gia));
                        fill();
                        ghifile();
                    }
                }
            }
    );
    int x =0;
    ActivityResultLauncher<Intent> sua = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK){
                        Intent i = result.getData();
                        Bundle b = i.getExtras();
                        String hang = b.getString(ThemSua.KEY_HANG);
                        String ten = b.getString(ThemSua.KEY_TEN);
                        int namsx = Integer.parseInt(b.getString(ThemSua.KEY_NAM_SX));
                        int gia = Integer.parseInt(b.getString(ThemSua.KEY_GIA));
                        list.set(x,new XeHoi(ten,hang,namsx,gia));
                        fill();
                        ghifile();
                    }
                }
            }
    );
    public void setSua(int x){
        this.x=x;
        sua.launch(new Intent(MH2.this,ThemSua.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu_home,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        } else if (item.getItemId() == R.id.menu_them) {
            them.launch(new Intent(MH2.this,ThemSua.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void xoa(int i){
        list.remove(i);
        ghifile();
        fill();
        Toast.makeText(MH2.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
    }
    public String filename = "xehoi.txt";
    public void ghifile(){
        try {
            FileOutputStream fos = openFileOutput(filename,MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void docfile() {
        try {
            FileInputStream fis = openFileInput(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (List<XeHoi>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fill(){
        adapter = new XeHoiAdapter(list,this);
        lv_khach.setAdapter(adapter);
    }
}