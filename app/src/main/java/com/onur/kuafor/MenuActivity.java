package com.onur.kuafor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.onur.kuafor.databinding.ActivityMenuBinding;
import com.onur.kuafor.fragments.AlisverisFragment;
import com.onur.kuafor.fragments.AnasayfaFragment;
import com.onur.kuafor.fragments.GecmisFragment;
import com.onur.kuafor.fragments.ProfilFragment;
import com.onur.kuafor.fragments.SepetFragment;

public class MenuActivity extends AppCompatActivity {
    public ActivityMenuBinding binding;
    AnasayfaFragment anasayfaFragment;
    AlisverisFragment alisverisFragment;
    GecmisFragment gecmisFragment;
    ProfilFragment profilFragment;
    SepetFragment sepetFragment;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        anasayfaFragment = new AnasayfaFragment();
        alisverisFragment = new AlisverisFragment();
        gecmisFragment = new GecmisFragment();
        profilFragment = new ProfilFragment();
        sepetFragment = new SepetFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, anasayfaFragment).commit();


        binding.bottomNavMenuActivity.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.idAnasayfa) {
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, anasayfaFragment).commit();
                return true;
            } else if (item.getItemId() == R.id.idAlisveris) {
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, alisverisFragment).commit();
                return true;
            } else if (item.getItemId() == R.id.idGecmis) {
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, gecmisFragment).commit();
                return true;
            } else if (item.getItemId() == R.id.idHesap) {
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, profilFragment).commit();
                return true;
            } else if (item.getItemId() == R.id.idSepet) {
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, sepetFragment).commit();
                return true;
            } else {
                return false;
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}