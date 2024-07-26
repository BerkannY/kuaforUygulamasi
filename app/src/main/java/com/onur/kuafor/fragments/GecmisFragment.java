package com.onur.kuafor.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.onur.kuafor.R;
import com.onur.kuafor.databinding.FragmentGecmisBinding;
import com.onur.kuafor.databinding.FragmentProfilBinding;

import java.util.ArrayList;
import java.util.Date;


public class GecmisFragment extends Fragment {
    public FragmentGecmisBinding binding;
    SQLiteDatabase veritabani;
    String hepsi, telgel, sifregel;
    ArrayList<String> saatler = new ArrayList<>();
    ArrayList<String> gunler = new ArrayList<>();
    ArrayList<String> aylar = new ArrayList<>();
    ArrayList<String> yillar = new ArrayList<>();
    ArrayList<String> kuaforler = new ArrayList<>();
    ArrayList<String> hepsiler = new ArrayList<>();
    ArrayList<String> islem1 = new ArrayList<>();
    ArrayList<String> islem2 = new ArrayList<>();
    ArrayList<String> islem3 = new ArrayList<>();
    ArrayList<String> islem4 = new ArrayList<>();
    ArrayList<String> islem5 = new ArrayList<>();
    ArrayList<String> islem6 = new ArrayList<>();
    ArrayList<String> islem7 = new ArrayList<>();
    ArrayList<String> islem8 = new ArrayList<>();
    ArrayList<String> islem9 = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGecmisBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Date d = new Date();
        CharSequence day  = DateFormat.format("d", d.getTime());
        CharSequence mounth  = DateFormat.format("M", d.getTime());
        CharSequence year  = DateFormat.format("yyyy", d.getTime());
        int sqlyear = Integer.parseInt((String) year)-1;
        int sqlmounth = Integer.parseInt((String) mounth)-1;
        int sqlday = Integer.parseInt((String) day)-1;


        SharedPreferences isimgetir = GecmisFragment.this.getActivity().getSharedPreferences("dosyam", Context.MODE_PRIVATE);
        telgel = isimgetir.getString("kullanicitel","tel");
        sifregel = isimgetir.getString("kullanicisifre","parola");

        try {
            veritabani = GecmisFragment.this.getActivity().openOrCreateDatabase("KullancilarDb", MODE_PRIVATE, null);
            Cursor cursor = veritabani.rawQuery("SELECT * FROM randevu WHERE TELEFON='"+telgel+"' ",null);
            int kuaforX = cursor.getColumnIndex("KUAFOR");
            int saatX = cursor.getColumnIndex("SAAT");
            int tarihX = cursor.getColumnIndex("TARIH");
            int ayX = cursor.getColumnIndex("AY");
            int yilX = cursor.getColumnIndex("YIL");
            int secim1X = cursor.getColumnIndex("ISLEM1");
            int secim2X = cursor.getColumnIndex("ISLEM2");
            int secim3X = cursor.getColumnIndex("ISLEM3");
            int secim4X = cursor.getColumnIndex("ISLEM4");
            int secim5X = cursor.getColumnIndex("ISLEM5");
            int secim6X = cursor.getColumnIndex("ISLEM6");
            int secim7X = cursor.getColumnIndex("ISLEM7");
            int secim8X = cursor.getColumnIndex("ISLEM8");
            int secim9X = cursor.getColumnIndex("ISLEM9");
            while (cursor.moveToNext()){
                kuaforler.add(cursor.getString(kuaforX));
                saatler.add(cursor.getString(saatX));
                gunler.add(cursor.getString(tarihX));
                aylar.add(cursor.getString(ayX));
                yillar.add(cursor.getString(yilX));
                islem1.add(cursor.getString(secim1X));
                islem2.add(cursor.getString(secim2X));
                islem3.add(cursor.getString(secim3X));
                islem4.add(cursor.getString(secim4X));
                islem5.add(cursor.getString(secim5X));
                islem6.add(cursor.getString(secim6X));
                islem7.add(cursor.getString(secim7X));
                islem8.add(cursor.getString(secim8X));
                islem9.add(cursor.getString(secim9X));

//                if (islem1.equals("Evet")){
//                    islemm1 = "Saç Kesimi";
//                } if (islem2.equals("Evet")) {
//                    islemm2 = "Fön";
//                } if (islem3.equals("Evet")) {
//                    islemm3 = "Saç Boyama";
//                } if (islem4.equals("Evet")) {
//                    islemm4 = "Perma";
//                } if (islem5.equals("Evet")) {
//                    islemm5 = "Röfle";
//                } if (islem6.equals("Evet")) {
//                    islemm6 = "Manikür";
//                } if (islem7.equals("Evet")) {
//                    islemm7 = "Pedikür";
//                } if (islem8.equals("Evet")) {
//                    islemm8 = "Ağda";
//                } if (islem9.equals("Evet")) {
//                    islemm9 = "Yüz Alma";
//                }
                hepsi = ("Tarih:   "+cursor.getString(tarihX)+"/"+cursor.getString(ayX)+"/"+cursor.getString(yilX)+"\nSaat: "+cursor.getString(saatX)+":00\n"+cursor.getString(kuaforX));
                hepsiler.add(hepsi);
            }
            cursor.close();
            ArrayAdapter arrayAdapter = new ArrayAdapter(GecmisFragment.this.getActivity(), android.R.layout.simple_list_item_1,(hepsiler));
            binding.gecmisrandevulayout.setAdapter(arrayAdapter);
        }catch (Exception e){
            toast("sql sorgusunda bir hata ile karşılaşıldı...90");
        }








        return view;
    }

    public void toast(String message){
        Toast.makeText(GecmisFragment.this.getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}