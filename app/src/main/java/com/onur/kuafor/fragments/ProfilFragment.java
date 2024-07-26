package com.onur.kuafor.fragments;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.onur.kuafor.MainActivity;
import com.onur.kuafor.R;
import com.onur.kuafor.databinding.ActivityMenuBinding;
import com.onur.kuafor.databinding.FragmentProfilBinding;

public class ProfilFragment extends Fragment {
    public FragmentProfilBinding binding;
    SQLiteDatabase veritabani;
    String ad,soyad,sehir,parola,cinsiyet,telefon,isimson,soyisimson,telefonson,cinsiyetson,sifreson,sehirson;
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfilBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        String[] sehirler = {"Lütfen bir şehir seçin","Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Aksaray", "Amasya", "Ankara", "Antalya", "Ardahan", "Artvin", "Aydın", "Balıkesir", "Bartın", "Batman", "Bayburt", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Düzce", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Iğdır", "Isparta", "İstanbul", "İzmir", "Kahramanmaraş", "Karabük", "Karaman", "Kars", "Kastamonu", "Kayseri", "Kırıkkale", "Kırklareli", "Kırşehir", "Kilis", "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Mardin", "Mersin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Osmaniye", "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Şanlıurfa", "Şırnak", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Uşak", "Van", "Yalova", "Yozgat", "Zonguldak"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ProfilFragment.this.getActivity(), android.R.layout.simple_spinner_item, sehirler);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.sehirSpinnerProfil.setAdapter(adapter);


        SharedPreferences isimgetir = ProfilFragment.this.getActivity().getSharedPreferences("dosyam", Context.MODE_PRIVATE);
        String telgel = isimgetir.getString("kullanicitel","tel");
        String sifregel = isimgetir.getString("kullanicisifre","parola");

        try {
            veritabani = ProfilFragment.this.getActivity().openOrCreateDatabase("KullancilarDb",MODE_PRIVATE,null);
            Cursor cursor = veritabani.rawQuery("SELECT * FROM tablom WHERE TELEFON= '"+telgel+"' AND SIFRE = '"+sifregel+"' ", null);
            int isimlerX= cursor.getColumnIndex("ISIM");
            int soyisimlerX = cursor.getColumnIndex("SOYISIM");
            int telefonX = cursor.getColumnIndex("TELEFON");
            int sehirX = cursor.getColumnIndex("SEHIR");
            int sifreX = cursor.getColumnIndex("SIFRE");
            int cinsiyetX = cursor.getColumnIndex("CINSIYET");
            while (cursor.moveToNext()) {
                ad= cursor.getString(isimlerX);
                soyad= cursor.getString(soyisimlerX);
                telefon = cursor.getString(telefonX);
                sehir = cursor.getString(sehirX);
                parola = cursor.getString(sifreX);
                cinsiyet = cursor.getString(cinsiyetX);
                binding.idisimedit.setText(ad);
                binding.idsoyisimedit.setText(soyad);
                binding.idtelefonedit.setText(telefon);
                binding.idsifreedit.setText(parola);
                binding.idsifre2edit.setText(parola);
                int position = adapter.getPosition(sehir);
                binding.sehirSpinnerProfil.setSelection(position);
                if (cinsiyet.equals("Erkek")){
                    binding.iderkekbtnn.setChecked(true);
                }else {
                    binding.idkadinbtnn.setChecked(false);
                }

            }
            cursor.close();

        }catch (Exception e) {
            Toast.makeText(ProfilFragment.this.getActivity(), "sql sorgusunda bir hata ile karşılaşıldı...10", Toast.LENGTH_SHORT).show();
        }
        binding.idBtnGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = binding.idradiogrupid.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = view.findViewById(selectedId);
                int sahirsayi = (int) binding.sehirSpinnerProfil.getSelectedItemId();
                if (selectedRadioButton.getText().toString().isEmpty() ||binding.idsifreedit.getText().toString().isEmpty() || binding.idisimedit.getText().toString().isEmpty() || binding.idsoyisimedit.getText().toString().isEmpty() || binding.idsifre2edit.getText().toString().isEmpty() || binding.idtelefonedit.getText().toString().isEmpty() || sahirsayi==0) {
                    Toast.makeText(ProfilFragment.this.getActivity(), "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show();
                } else if (telefon.length()!=10) {
                    toast("Lütfen Telefon numarasını tamamlayın...");
                } else if (binding.idisimedit.getText().toString().length()<3 ||binding.idsoyisimedit.getText().toString().length()<3) {
                    toast("Lütfen isim, soyismi kontrol edin...");
                } else if (binding.idsifreedit.getText().toString().length()<8) {
                    toast("Lütfen en az 8 haneli bir parola oluşturun...");
                } else if (!binding.idsifreedit.getText().toString().equals(binding.idsifre2edit.getText().toString())) {
                    toast("Şifreler eşleşmiyor...");
                } else{
                    try {
                        veritabani = ProfilFragment.this.getActivity().openOrCreateDatabase("KullancilarDb",MODE_PRIVATE,null);
                        isimson= binding.idisimedit.getText().toString();
                        soyisimson = binding.idsoyisimedit.getText().toString();
                        sehirson = binding.sehirSpinnerProfil.getSelectedItem().toString();
                        telefonson = binding.idtelefonedit.getText().toString();
                        sifreson = binding.idsifreedit.getText().toString();
                        cinsiyetson = selectedRadioButton.getText().toString();

                        System.out.println("isim: "+isimson+" soyisim: "+soyisimson+" telefon: "+telefonson+" cinsiyet: "+ cinsiyetson);
                        String updateQuery = "UPDATE tablom SET ISIM=?, SOYISIM=?, TELEFON=? ,SEHIR = ?,SIFRE = ?, CINSIYET=? WHERE TELEFON= ? AND SIFRE = ? ";

                        SQLiteStatement statement = veritabani.compileStatement(updateQuery);
                        statement.bindString(1, isimson);
                        statement.bindString(2, soyisimson);
                        statement.bindString(3, telefonson);
                        statement.bindString(4, sehirson);
                        statement.bindString(5, sifreson);
                        statement.bindString(6, cinsiyetson);
                        statement.bindString(7, telgel);
                        statement.bindString(8, sifregel);
                        statement.execute();
                        statement.close();

                        toast("Verileriniz güncellendi...");
                    }catch (Exception e){
                        toast("Sql günceleme sorgusunda hata oluştu...");
                    }
                }
            }
        });

        return view;
    }
    public void toast(String message){
        Toast.makeText(ProfilFragment.this.getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}