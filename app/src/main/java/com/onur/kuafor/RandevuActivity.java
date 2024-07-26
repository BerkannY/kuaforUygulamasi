package com.onur.kuafor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.onur.kuafor.databinding.ActivityRandevuBinding;
import com.onur.kuafor.fragments.AnasayfaFragment;
import com.onur.kuafor.fragments.ProfilFragment;

import java.util.ArrayList;
import java.util.Date;

public class RandevuActivity extends AppCompatActivity {
    public ActivityRandevuBinding binding;
    SQLiteDatabase veritabani;
    int dayy,monthh,yearr;
    String sehir,cinsiyet,telgel,islem1="Hayır",islem2="Hayır",islem3="Hayır",islem4="Hayır",islem5="Hayır",islem6="Hayır",islem7="Hayır",islem8="Hayır",islem9="Hayır";
    int zaman=0;
    ArrayList<String> kuaforlist =new ArrayList<>();
    boolean islemdurumu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityRandevuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String selectedday= String.valueOf(dayOfMonth);
                month ++;
                String selectedmonth=  (String.valueOf(month));
                String selectedyear=  (String.valueOf(year)) ;

                dayy = Integer.parseInt(selectedday);
                yearr = Integer.parseInt(selectedyear);
                monthh = Integer.parseInt(selectedmonth);
            }

        });

        binding.saat9.setBackgroundResource(R.color.white);
        binding.saat10.setBackgroundResource(R.color.white);
        binding.saat11.setBackgroundResource(R.color.white);
        binding.saat14.setBackgroundResource(R.color.white);
        binding.saat15.setBackgroundResource(R.color.white);
        binding.saat16.setBackgroundResource(R.color.white);

        SharedPreferences isimgetir = RandevuActivity.this.getSharedPreferences("dosyam", Context.MODE_PRIVATE);
        telgel = isimgetir.getString("kullanicitel","tel");
        String sifregel = isimgetir.getString("kullanicisifre","parola");

        try {
            veritabani = RandevuActivity.this.openOrCreateDatabase("KullancilarDb",MODE_PRIVATE,null);
            veritabani.execSQL("CREATE TABLE IF NOT EXISTS randevu (ID INTEGER PRIMARY KEY AUTOINCREMENT,TELEFON VARCAHAR(10),KUAFOR VARCHAR(30),SAAT VARCHAR(2),TARIH INTEGER(2),AY INTEGER(2),YIL INTEGER(4),ISLEM1 VARCHAR,ISLEM2 VARCHAR,ISLEM3 VARCHAR,ISLEM4 VARCHAR,ISLEM5 VARCHAR,ISLEM6 VARCHAR,ISLEM7 VARCHAR,ISLEM8 VARCHAR,ISLEM9 VARCHAR) ");
            Cursor cursor = veritabani.rawQuery("SELECT * FROM tablom WHERE TELEFON= '"+telgel+"' AND SIFRE = '"+sifregel+"' ", null);
            int sehirX = cursor.getColumnIndex("SEHIR");
            int cinsiyetX = cursor.getColumnIndex("CINSIYET");
            while (cursor.moveToNext()) {
                sehir = cursor.getString(sehirX);
                cinsiyet = cursor.getString(cinsiyetX);
                binding.sehirtxt.setText(sehir);
                if (cinsiyet.equals("Erkek")) {
                    kuaforlist.add("Bir Kuaför seçin");
                    kuaforlist.add("Ahmet kuaför");
                    kuaforlist.add("Karizma kuaför");
                    kuaforlist.add("Altın Makas kuaförü");
                } else {
                    kuaforlist.add("Bir Kuaför seçin");
                    kuaforlist.add("Ayşe kuaför");
                    kuaforlist.add("Balım kuaför");
                    kuaforlist.add("Canan kuaförü");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(RandevuActivity.this, android.R.layout.simple_spinner_item, kuaforlist);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.spinnerkuafor.setAdapter(adapter);
                cursor.close();
            }
            }catch (Exception e) {
                Toast.makeText(RandevuActivity.this, "sql sorgusunda bir hata ile karşılaşıldı...80", Toast.LENGTH_SHORT).show();
            }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void geridonfonk(View view){
        Intent anasayfagecis = new Intent(RandevuActivity.this, MenuActivity.class);
        startActivity(anasayfagecis);

    }
    public void devamfonk(View view){
        String kuafor = binding.spinnerkuafor.getSelectedItem().toString();
        if (kuafor.equals("Bir Kuaför seçin")){
            toast("Lütfen Kuaför seçimini yapın...");
        }else{
            binding.ilklayout.setVisibility(View.GONE);
            binding.ikincilayout.setVisibility(View.VISIBLE);
        }


    }
    @SuppressLint("ResourceAsColor")
    public void saat9fonk (View view){
        zaman = 9;
        binding.radevualbtn.setClickable(true);
        binding.saat9.setBackgroundResource(R.color.black);
        binding.saat10.setBackgroundResource(R.color.white);
        binding.saat11.setBackgroundResource(R.color.white);
        binding.saat14.setBackgroundResource(R.color.white);
        binding.saat15.setBackgroundResource(R.color.white);
        binding.saat16.setBackgroundResource(R.color.white);

    }
    @SuppressLint("ResourceAsColor")
    public void saat10fonk (View view){
        zaman = 10;
        binding.radevualbtn.setClickable(true);
        binding.saat10.setBackgroundResource(R.color.black);
        binding.saat9.setBackgroundResource(R.color.white);
        binding.saat11.setBackgroundResource(R.color.white);
        binding.saat14.setBackgroundResource(R.color.white);
        binding.saat15.setBackgroundResource(R.color.white);
        binding.saat16.setBackgroundResource(R.color.white);

    }
    @SuppressLint("ResourceAsColor")
    public void saat11fonk (View view){
        zaman = 11;
        binding.radevualbtn.setClickable(true);
        binding.saat11.setBackgroundResource(R.color.black);
        binding.saat9.setBackgroundResource(R.color.white);
        binding.saat10.setBackgroundResource(R.color.white);
        binding.saat14.setBackgroundResource(R.color.white);
        binding.saat15.setBackgroundResource(R.color.white);
        binding.saat16.setBackgroundResource(R.color.white);
    }
    @SuppressLint("ResourceAsColor")
    public void saat14fonk (View view){
        zaman = 14;
        binding.radevualbtn.setClickable(true);
        binding.saat14.setBackgroundResource(R.color.black);
        binding.saat9.setBackgroundResource(R.color.white);
        binding.saat10.setBackgroundResource(R.color.white);
        binding.saat11.setBackgroundResource(R.color.white);
        binding.saat15.setBackgroundResource(R.color.white);
        binding.saat16.setBackgroundResource(R.color.white);

    }
    @SuppressLint("ResourceAsColor")
    public void saat15fonk (View view){
        zaman = 15;
        binding.radevualbtn.setClickable(true);
        binding.saat15.setBackgroundResource(R.color.black);
        binding.saat9.setBackgroundResource(R.color.white);
        binding.saat10.setBackgroundResource(R.color.white);
        binding.saat11.setBackgroundResource(R.color.white);
        binding.saat14.setBackgroundResource(R.color.white);
        binding.saat16.setBackgroundResource(R.color.white);

    }
    @SuppressLint("ResourceAsColor")
    public void saat16fonk (View view){
        zaman = 16;
        binding.radevualbtn.setClickable(true);
        binding.saat16.setBackgroundResource(R.color.black);
        binding.saat9.setBackgroundResource(R.color.white);
        binding.saat10.setBackgroundResource(R.color.white);
        binding.saat11.setBackgroundResource(R.color.white);
        binding.saat14.setBackgroundResource(R.color.white);
        binding.saat15.setBackgroundResource(R.color.white);
    }
    public void checkboxfonk(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if (view.getId() == R.id.checkBoxSacKesim) {
            if(checked)
                islem1 = "Evet";
            islemdurumu = true;
        }else if (view.getId() == R.id.checkBoxFon) {
            if(checked)
                islem2 = "Evet";
            islemdurumu = true;
        } else if (view.getId() == R.id.checkBoxBoyama) {
            if(checked)
                islem3 = "Evet";
            islemdurumu = true;
        } else if (view.getId() == R.id.checkBoxPerma) {
            if(checked)
                islem4 = "Evet";
            islemdurumu = true;
        } else if (view.getId() == R.id.checkBoxRofle) {
            if(checked)
                islem5 = "Evet";
            islemdurumu = true;
        } else if (view.getId() == R.id.checkBoxManikur) {
            if(checked)
                islem6 = "Evet";
            islemdurumu = true;
        } else if (view.getId() == R.id.checkBoxPedikur) {
            if(checked)
                islem7 = "Evet";
            islemdurumu = true;
        } else if (view.getId() == R.id.checkBoxAgda) {
            if(checked)
                islem8 = "Evet";
            islemdurumu = true;
        } else if (view.getId() == R.id.checkBoxYuz) {
            if(checked)
                islem9 = "Evet";
            islemdurumu = true;
        }
        if (islem1.equals("Hayır") &&islem2.equals("Hayır")&&islem3.equals("Hayır")&&islem4.equals("Hayır")&&islem5.equals("Hayır")&&islem6.equals("Hayır")&&islem7.equals("Hayır")&&islem8.equals("Hayır") && islem9.equals("Hayır")){
            toast("Lütfen bir işlem seçimi yapın...");
            islemdurumu = false;
        }
    }


    public void randevualfonk (View view){
        String kuafor = binding.spinnerkuafor.getSelectedItem().toString();

        try {

            veritabani = RandevuActivity.this.openOrCreateDatabase("KullancilarDb",MODE_PRIVATE,null);
            veritabani.execSQL("INSERT INTO randevu (TELEFON,KUAFOR,SAAT,TARIH,AY,YIL,ISLEM1,ISLEM2,ISLEM3,ISLEM4,ISLEM5,ISLEM6,ISLEM7,ISLEM8,ISLEM9)VALUES ('"+telgel+"','"+kuafor+"','"+zaman+"','"+dayy+"','"+monthh+"','"+yearr+"','"+islem1+"','"+islem2+"','"+islem3+"','"+islem4+"','"+islem5+"','"+islem6+"','"+islem7+"','"+islem8+"','"+islem9+"')");
            toast("Randevu Başarıyla Oluşturuldu...");
            Intent anasyafagec = new Intent(RandevuActivity.this,MenuActivity.class);
            startActivity(anasyafagec);
            finish();
        }catch (Exception e){
            toast("Sql sorgusunda bir hata ile karşılaşıldı...85");
        }

    }
    public void toast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}