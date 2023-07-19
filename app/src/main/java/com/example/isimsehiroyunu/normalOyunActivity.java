package com.example.isimsehiroyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class normalOyunActivity extends AppCompatActivity {
    private TextView txtIlBilgi, txtViewIl, txtPuan;
    private EditText editTextTahmin;
    private String[] iller = {"Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Aksaray", "Amasya",
            "Ankara", "Antalya", "Ardahan", "Artvin", "Aydın", "Balıkesir", "Bartın", "Batman", "Bayburt",
            "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli",
            "Diyarbakır", "Düzce", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep",
            "Giresun", "Gümüşhane", "Hakkâri", "Hatay", "Iğdır", "Isparta", "İstanbul", "İzmir", "Kahramanmaraş",
            "Karabük", "Karaman", "Kars", "Kastamonu", "Kayseri", "Kırıkkale", "Kırklareli", "Kırşehir", "Kilis",
            "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Mardin", "Mersin", "Muğla", "Muş", "Nevşehir", "Niğde",
            "Ordu", "Osmaniye", "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Şanlıurfa", "Şırnak", "Tekirdağ",
            "Tokat", "Trabzon", "Tunceli", "Uşak", "Van", "Yalova", "Yozgat", "Zonguldak"
    };

    private Random randomIl, randomHarf;
    private int randomIlNumber, randomHarfNumber, baslangicHarfSayisi;
    private String gelenIl, ilBoyut = "", gelenTahmin;
    private int toplamPuan = 0, dogruPuan = 10, azalacakPuan = 3, harfPuan = 2, bosPuan = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_oyun);

        txtIlBilgi = findViewById(R.id.bilgitxt);
        txtViewIl = findViewById(R.id.ilgview);
        editTextTahmin = findViewById(R.id.tahminedittext);
        txtPuan = (TextView) findViewById(R.id.txtrekor);

        randomHarf = new Random();
        setRandomValues();
    }

    public void tahminEt(View v) {
        gelenTahmin = editTextTahmin.getText().toString();

        if (!TextUtils.isEmpty(gelenTahmin)) {
            if (gelenTahmin.equalsIgnoreCase(gelenIl)) {
                toplamPuan += dogruPuan;

                txtPuan.setText(String.valueOf(toplamPuan));
                Toast.makeText(normalOyunActivity.this,"+10 Perfect ",Toast.LENGTH_SHORT).show();
                setRandomValues();
                editTextTahmin.setText("");


            } else {
                txtIlBilgi.setText("Yanlış tahmin");
                toplamPuan -= azalacakPuan;
                Toast.makeText(normalOyunActivity.this,"3 Puanınız Silindi ",Toast.LENGTH_SHORT).show();
                txtPuan.setText(String.valueOf(toplamPuan));
            }
        } else {
            txtIlBilgi.setText("Boş değer olamaz");
            toplamPuan -= bosPuan;
            txtPuan.setText(String.valueOf(toplamPuan));
        }
    }

    private void setRandomValues() {
        ilBoyut = "";
        randomIl = new Random();
        randomIlNumber = randomIl.nextInt(iller.length);
        gelenIl = iller[randomIlNumber];

        txtIlBilgi.setText(gelenIl.length() + " " + "harfli ilimiz");

        if (gelenIl.length() >= 5 && gelenIl.length() < 7) {
            baslangicHarfSayisi = 1;
        } else if (gelenIl.length() >= 7 && gelenIl.length() < 10) {
            baslangicHarfSayisi = 2;
        } else {
            baslangicHarfSayisi = 3;
        }

        for (int i = 0; i < gelenIl.length(); i++) {
            if (i < gelenIl.length() - 1) {
                ilBoyut += "- ";
            } else {
                ilBoyut += "-";
            }
        }
        txtViewIl.setText(ilBoyut);

        for (int c = 0; c < baslangicHarfSayisi; c++) {
            harfAl(txtIlBilgi);
        }

    }

    public void harfAl(View v) {

        ArrayList<Character> ilHarfleri = new ArrayList<>();
        for (char c : gelenIl.toCharArray()) {
            ilHarfleri.add(c);
        }


        if (ilHarfleri.size() > 0) {
            randomHarfNumber = randomHarf.nextInt(ilHarfleri.size());
            String[] txtHarfler = txtViewIl.getText().toString().split(" ");
            char[] gelenIlHarfler = gelenIl.toCharArray();

            for (int i = 0; i < gelenIl.length(); i++) {
                if (txtHarfler[i].equals("-") && (gelenIlHarfler[i] == ilHarfleri.get(randomHarfNumber))) {
                    txtHarfler[i] = String.valueOf(ilHarfleri.get(randomHarfNumber));
                }
            }

            StringBuilder newIlBoyut = new StringBuilder();
            for (String harf : txtHarfler) {
                newIlBoyut.append(harf).append(" ");
            }
            txtViewIl.setText(newIlBoyut.toString());
            ilHarfleri.remove(randomHarfNumber);
        }
    }
}
