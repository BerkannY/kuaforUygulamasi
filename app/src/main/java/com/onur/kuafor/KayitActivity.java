package com.onur.kuafor;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.onur.kuafor.databinding.ActivityKayitBinding;

public class KayitActivity extends AppCompatActivity {
    public ActivityKayitBinding binding;

    String selectedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKayitBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String[] sehirler = {"Lütfen bir şehir seçin","Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Aksaray", "Amasya", "Ankara", "Antalya", "Ardahan", "Artvin", "Aydın", "Balıkesir", "Bartın", "Batman", "Bayburt", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Düzce", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Iğdır", "Isparta", "İstanbul", "İzmir", "Kahramanmaraş", "Karabük", "Karaman", "Kars", "Kastamonu", "Kayseri", "Kırıkkale", "Kırklareli", "Kırşehir", "Kilis", "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Mardin", "Mersin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Osmaniye", "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Şanlıurfa", "Şırnak", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Uşak", "Van", "Yalova", "Yozgat", "Zonguldak"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sehirler);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.sehirSpinner.setAdapter(adapter);




        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void kayitfonk(View view){
        String isim = binding.isimedit.getText().toString();
        String soyisim = binding.soyisimedit.getText().toString();
        String telefon = binding.telefonedit.getText().toString();
        String sifre = binding.sifreedit.getText().toString();
        String sifre2 = binding.sifre2edit.getText().toString();
        int selectedId = binding.radiogrupid.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        selectedText = selectedRadioButton.getText().toString();

        String sehir = binding.sehirSpinner.getSelectedItem().toString();
        int sahirsayi = (int) binding.sehirSpinner.getSelectedItemId();
        int cinssayi = binding.radiogrupid.getCheckedRadioButtonId();
        System.out.println("seçilen sehir: "+sehir);
        System.out.println("seçilen cinsiyet yeni: "+selectedText);
        if (selectedText.isEmpty() ||cinssayi==-1 ||sifre2.isEmpty() || isim.isEmpty() || soyisim.isEmpty() || sifre.isEmpty() || telefon.isEmpty() || sahirsayi==0) {
            Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show();
        } else if (telefon.length()!=10) {
            toast("Lütfen Telefon numarasını tamamlayın...");
        } else if (binding.isimedit.length()<3 ||binding.soyisimedit.length()<3) {
            toast("Lütfen isim, soyismi kontrol edin...");
        } else if (sifre.length()<8) {
            toast("Lütfen en az 8 haneli bir parola oluşturun...");
        } else if (!sifre.equals(sifre2)) {
            toast("Şifreler eşleşmiyor...");
        } else{
            try {
                SQLiteDatabase veritabani = this.openOrCreateDatabase("KullancilarDb",MODE_PRIVATE,null);
                veritabani.execSQL("INSERT INTO tablom (ISIM,SOYISIM,TELEFON,SEHIR,SIFRE,CINSIYET)VALUES ('"+isim+"','"+soyisim+"','"+telefon+"','"+sehir+"','"+sifre+"','"+selectedText+"')");
                toast("Kayıt başarıyla tamamlandı...");

            }catch (Exception e){
                toast("Sql sorgusunda bir hata ile karşılaşıldı...5");
            }
            binding.soyisimedit.setText("");
            binding.isimedit.setText("");
            binding.telefonedit.setText("");
            binding.sifreedit.setText("");
            binding.sifre2edit.setText("");
            binding.sehirSpinner.setSelection(0);
        }

    }


    public void geridonfonk(View view){
        Intent anasayfagecis = new Intent(KayitActivity.this, MainActivity.class);
        startActivity(anasayfagecis);

    }

    public void toast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}