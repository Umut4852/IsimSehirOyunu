package com.example.isimsehiroyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class sureliOyunActivity extends AppCompatActivity {
    private TextView Bilgi, il, puans, kalansure,txtrekor;
    private EditText tahmin;
    private Button alharf,ettahmin;
    private ImageButton restart;
    private String[] Illler = {"Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Aksaray", "Amasya",
            "Ankara", "Antalya", "Ardahan", "Artvin", "Aydın", "Balıkesir", "Bartın", "Batman", "Bayburt",
            "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli",
            "Diyarbakır", "Düzce", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep",
            "Giresun", "Gümüşhane", "Hakkâri", "Hatay", "Iğdır", "Isparta", "İstanbul", "İzmir", "Kahramanmaraş",
            "Karabük", "Karaman", "Kars", "Kastamonu", "Kayseri", "Kırıkkale", "Kırklareli", "Kırşehir", "Kilis",
            "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Mardin", "Mersin", "Muğla", "Muş", "Nevşehir", "Niğde",
            "Ordu", "Osmaniye", "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Şanlıurfa", "Şırnak", "Tekirdağ",
            "Tokat", "Trabzon", "Tunceli", "Uşak", "Van", "Yalova", "Yozgat", "Zonguldak"
    };

    private Random rndIl, rndharf;
    private int rndilnumber, rndharfnumber, baslangicharfsayisi;
    private int toplamsure=10000;
    private String gelenil, ilboyut = "", gelentahmin;
    private int toplampuan = 0, dogrupuan = 10, azalacakpuan = 3, harfpuan = 2, bospuan = 1;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sureli_oyun);

        Bilgi = findViewById(R.id.bilgitxt);
        il = findViewById(R.id.ilgview);
        tahmin = findViewById(R.id.tahminedittext);
        puans = (TextView) findViewById(R.id.txtrekor);
        kalansure = (TextView) findViewById(R.id.txtKalanSure);
        alharf=(Button)findViewById(R.id.alharf);
        ettahmin=(Button)findViewById(R.id.ettahmin);
       restart=(ImageButton) findViewById(R.id.restart);
        txtrekor=(TextView)findViewById(R.id.txtrekor);

        alharf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Character> ilHarfleri = new ArrayList<>();
                for (char c : gelenil.toCharArray()) {
                    ilHarfleri.add(c);
                }


                if (ilHarfleri.size() > 0) {
                    rndharfnumber = rndharf.nextInt(ilHarfleri.size());
                    String[] txtHarfler = il.getText().toString().split(" ");
                    char[] gelenIlHarfler = gelenil.toCharArray();

                    for (int i = 0; i < gelenil.length(); i++) {
                        if (txtHarfler[i].equals("-") && (gelenIlHarfler[i] == ilHarfleri.get(rndharfnumber))) {
                            txtHarfler[i] = String.valueOf(ilHarfleri.get(rndharfnumber));
                        }
                    }

                    StringBuilder newIlBoyut = new StringBuilder();
                    for (String harf : txtHarfler) {
                        newIlBoyut.append(harf).append(" ");
                    }
                    il.setText(newIlBoyut.toString());
                    ilHarfleri.remove(rndharfnumber);
                }
            }
        });

       restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(sureliOyunActivity.this,sureliOyunActivity.class);
                startActivity(intent);
            }
        });
      CountDownTimer  count= new CountDownTimer(toplamsure, 1000) {
            @Override
            public void onTick(long l) {
                kalansure.setText((l/60000) +":"+ ((l % 60000)/1000));

            }

            @Override
            public void onFinish() {
                Toast.makeText(sureliOyunActivity.this,"Süreniz Bitti \n Puanınız :"+toplampuan,Toast.LENGTH_SHORT).show();
                alharf.setEnabled(false);
                ettahmin.setEnabled(false);
                tahmin.setEnabled(false);
                kalansure.setText("00:00");
                restart.setVisibility(View.VISIBLE);
            }
        }.start();
        rndharf = new Random();
        setRandomValues();
    }

    public void etTahmin(View v) {
        gelentahmin = tahmin.getText().toString();

        if (!TextUtils.isEmpty(gelentahmin)) {
            if (gelentahmin.equalsIgnoreCase(gelenil)) {
                toplampuan += dogrupuan;

                puans.setText(String.valueOf(toplampuan));
                Toast.makeText(sureliOyunActivity.this, "+10 Perfect ", Toast.LENGTH_SHORT).show();
                setRandomValues();
                tahmin.setText("");


            } else {
                Bilgi.setText("Yanlış tahmin");
                toplampuan -= azalacakpuan;
                Toast.makeText(sureliOyunActivity.this, "3 Puanınız Silindi ", Toast.LENGTH_SHORT).show();
                puans.setText(String.valueOf(toplampuan));
            }
        } else {
            Bilgi.setText("Boş değer olamaz");
            toplampuan -= bospuan;
            puans.setText(String.valueOf(toplampuan));
        }
    }

    private void setRandomValues() {
        ilboyut = "";
        rndIl = new Random();
        rndilnumber = rndIl.nextInt(Illler.length);
        gelenil = Illler[rndilnumber];

        Bilgi.setText(gelenil.length() + " " + "harfli ilimiz");

        if (gelenil.length() >= 5 && gelenil.length() < 7) {
            baslangicharfsayisi = 1;
        } else if (gelenil.length() >= 7 && gelenil.length() < 10) {
            baslangicharfsayisi = 2;
        } else {
            baslangicharfsayisi = 3;
        }

        for (int i = 0; i < gelenil.length(); i++) {
            if (i < gelenil.length() - 1) {
                ilboyut += "- ";
            } else {
                ilboyut += "-";
            }
        }
        il.setText(ilboyut);

        for (int c = 0; c < baslangicharfsayisi; c++) {
            alHarf(Bilgi);
        }

    }


    public void alHarf(View v) {
/*
        ArrayList<Character> ilHarfleri = new ArrayList<>();
        for (char c : gelenil.toCharArray()) {
            ilHarfleri.add(c);
        }


        if (ilHarfleri.size() > 0) {
            rndharfnumber = rndharf.nextInt(ilHarfleri.size());
            String[] txtHarfler = il.getText().toString().split(" ");
            char[] gelenIlHarfler = gelenil.toCharArray();

            for (int i = 0; i < gelenil.length(); i++) {
                if (txtHarfler[i].equals("-") && (gelenIlHarfler[i] == ilHarfleri.get(rndharfnumber))) {
                    txtHarfler[i] = String.valueOf(ilHarfleri.get(rndharfnumber));
                }
            }

            StringBuilder newIlBoyut = new StringBuilder();
            for (String harf : txtHarfler) {
                newIlBoyut.append(harf).append(" ");
            }
            il.setText(newIlBoyut.toString());
            ilHarfleri.remove(rndharfnumber);
        }*/
    }

}