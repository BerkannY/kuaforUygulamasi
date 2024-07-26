package com.onur.kuafor.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.onur.kuafor.R;
import com.onur.kuafor.databinding.FragmentAlisverisBinding;
import com.onur.kuafor.databinding.FragmentProfilBinding;
import com.onur.kuafor.urunler.Urun1Activity;
import com.onur.kuafor.urunler.Urun2Activity;
import com.onur.kuafor.urunler.Urun3Activity;
import com.onur.kuafor.urunler.Urun4Activity;
import com.onur.kuafor.urunler.Urun5Activity;

public class AlisverisFragment extends Fragment {
    public FragmentAlisverisBinding binding;
    SQLiteDatabase veritabani;
    int adet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentAlisverisBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        try {
            veritabani = AlisverisFragment.this.getActivity().openOrCreateDatabase("KullancilarDb",MODE_PRIVATE,null);
            veritabani.execSQL("CREATE TABLE IF NOT EXISTS sepet (ID INTEGER PRIMARY KEY AUTOINCREMENT,TELEFON VARCHAR(10),URUNADI VARCHAR(20),FIYATI VARCHAR(10),ADET VARCHAR(30)) ");
        }catch (Exception e){
            toast("Sql sorgusunda bir hata ile karşılaşıldı...8");}

        SharedPreferences isimgetir = AlisverisFragment.this.getActivity().getSharedPreferences("dosyam", Context.MODE_PRIVATE);
        String telgel = isimgetir.getString("kullanicitel","tel");




        binding.layouturunana1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urun1sayfagec = new Intent(AlisverisFragment.this.getActivity(), Urun1Activity.class);
                startActivity(urun1sayfagec);
            }
        });
        binding.satinalbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urunadi = binding.urun1.getText().toString();
                String fiyati = binding.urunfiyat1.getText().toString();
                try {
                    veritabani = AlisverisFragment.this.getActivity().openOrCreateDatabase("KullancilarDb",MODE_PRIVATE,null);
                    veritabani.execSQL("INSERT INTO sepet (TELEFON,URUNADI,FIYATI)VALUES ('"+telgel+"','"+urunadi+"','"+fiyati+"')");
                    toast("Ürün başarıyla sepete eklendi...");
                }catch (Exception e){
                    toast("Sql sorgusunda bir hata ile karşılaşıldı...51");
                }
            }
        });
        binding.layouturunana2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urun2sayfagec = new Intent(AlisverisFragment.this.getActivity(), Urun2Activity.class);
                startActivity(urun2sayfagec);
            }
        });
        binding.satinalbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urunadi = binding.urun2.getText().toString();
                String fiyati = binding.urunfiyat2.getText().toString();
                try {
                    veritabani = AlisverisFragment.this.getActivity().openOrCreateDatabase("KullancilarDb",MODE_PRIVATE,null);
                    veritabani.execSQL("INSERT INTO sepet (TELEFON,URUNADI,FIYATI)VALUES ('"+telgel+"','"+urunadi+"','"+fiyati+"')");
                    toast("Ürün başarıyla sepete eklendi...");
                }catch (Exception e){
                    toast("Sql sorgusunda bir hata ile karşılaşıldı...52");
                }
            }
        });
        binding.layouturunana3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urun3sayfagec = new Intent(AlisverisFragment.this.getActivity(), Urun3Activity.class);
                startActivity(urun3sayfagec);
            }
        });
        binding.satinalbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urunadi = binding.urun3.getText().toString();
                String fiyati = binding.urunfiyat3.getText().toString();
                try {
                    veritabani = AlisverisFragment.this.getActivity().openOrCreateDatabase("KullancilarDb",MODE_PRIVATE,null);
                    veritabani.execSQL("INSERT INTO sepet (TELEFON,URUNADI,FIYATI)VALUES ('"+telgel+"','"+urunadi+"','"+fiyati+"')");
                    toast("Ürün başarıyla sepete eklendi...");
                }catch (Exception e){
                    toast("Sql sorgusunda bir hata ile karşılaşıldı...53");
                }
            }
        });
        binding.layouturunana4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urun4sayfagec = new Intent(AlisverisFragment.this.getActivity(), Urun4Activity.class);
                startActivity(urun4sayfagec);
            }
        });
        binding.satinalbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urunadi = binding.urun4.getText().toString();
                String fiyati = binding.urunfiyat4.getText().toString();
                try {
                    veritabani = AlisverisFragment.this.getActivity().openOrCreateDatabase("KullancilarDb",MODE_PRIVATE,null);
                    veritabani.execSQL("INSERT INTO sepet (TELEFON,URUNADI,FIYATI)VALUES ('"+telgel+"','"+urunadi+"','"+fiyati+"')");
                    toast("Ürün başarıyla sepete eklendi...");
                }catch (Exception e){
                    toast("Sql sorgusunda bir hata ile karşılaşıldı...54");
                }
            }
        });




        return view;
    }

    public void toast(String message){
        Toast.makeText(AlisverisFragment.this.getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}