package com.onur.kuafor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.onur.kuafor.databinding.ActivityMainBinding;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase veritabani;
    public ActivityMainBinding binding;
    ArrayList<String> telefonlar= new ArrayList<>();
    boolean isimyanlis,parolayanlis;
    ArrayList<String> sifreler= new ArrayList<>();
    ArrayList<Integer> idler= new ArrayList<>();
    String telgel,sifregel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        try {
            veritabani = this.openOrCreateDatabase("KullancilarDb",MODE_PRIVATE,null);
            veritabani.execSQL("CREATE TABLE IF NOT EXISTS tablom (ID INTEGER PRIMARY KEY AUTOINCREMENT,ISIM VARCHAR(20),SOYISIM VARCHAR(20),TELEFON VARCHAR(10),SEHIR VARCHAR(30),SIFRE VARCHAR(20),CINSIYET VARCHAR(6)) ");

        }catch (Exception e){
            toast("Sql sorgusunda bir hata ile karşılaşıldı...1");}

        if (binding.switch1.isChecked()){
            SharedPreferences isimgetir = MainActivity.this.getSharedPreferences("dosyam", Context.MODE_PRIVATE);
            telgel = isimgetir.getString("kullanicitel","tel");
            sifregel = isimgetir.getString("kullanicisifre","parola");
            if(!telgel.equals("tel")){
                binding.telefonedit.setText(telgel);
                binding.sifre.setText(sifregel);}
        }



        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void girisfonk(View view){

        if (TextUtils.isEmpty(binding.telefonedit.getText().toString())){
            toast("Lütfen telefon numarası girin...");
        }
        else if (TextUtils.isEmpty(binding.sifre.getText().toString())){
            toast("Lütfen bir şifre girin...");
        }
        else {
            try {
                SQLiteDatabase veritabani = this.openOrCreateDatabase("KullancilarDb", MODE_PRIVATE, null);

                Cursor imlec = veritabani.rawQuery("SELECT * FROM tablom", null);
                int idlerX = imlec.getColumnIndex("ID");
                int telefonX = imlec.getColumnIndex("TELEFON");
                int sifrelerX = imlec.getColumnIndex("SIFRE");
                while (imlec.moveToNext()) {
                    idler.add(imlec.getInt(idlerX));
                    telefonlar.add(imlec.getString(telefonX));
                    sifreler.add(imlec.getString(sifrelerX));

                }
                imlec.close();
                for (int i = 0; i <= idler.size(); i++) {
                    if (binding.telefonedit.getText().toString().equalsIgnoreCase(telefonlar.get(i))) {
                        if (binding.sifre.getText().toString().equals(sifreler.get(i).toLowerCase())) {
                            int sonId = idler.get(i);
                            Intent menusayfasinagec = new Intent(MainActivity.this, MenuActivity.class);
                            menusayfasinagec.putExtra("id",sonId);
                            startActivity(menusayfasinagec);
                            finish();
                            if (binding.switch1.isChecked()){
                                SharedPreferences benihatirla = this.getSharedPreferences("dosyam",MODE_PRIVATE);
                                SharedPreferences.Editor editor =benihatirla.edit();
                                editor.putString("kullanicitel",telefonlar.get(i));
                                editor.putString("kullanicisifre",sifreler.get(i));
                                editor.apply();
                            }
                            return;
                        }else{
                            parolayanlis =true;


                        }
                    } else {
                        isimyanlis= true;


                    }

                }
                if (parolayanlis=true)
                    toast("Parola bilgisi yanlış...");
                else if (isimyanlis == true)
                    toast("kullanıcı telefon bilgisi doğrulanamadı...");



            }catch(Exception e){
                toast("Sql sorgusunda bir hata ile karşılaşıldı...3");
            }

        }
    }


    public void kayitfonk(View view ){
        Intent kayitgecis = new Intent(MainActivity.this, KayitActivity.class);
        startActivity(kayitgecis);
    }
    public void toast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}