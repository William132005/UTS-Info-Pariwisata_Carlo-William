package com.example.infopariwisata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imgDetail = findViewById(R.id.imgDetail);
        TextView txtNama = findViewById(R.id.txtNamaDetail);
        TextView txtLokasi = findViewById(R.id.txtLokasiDetail);
        TextView txtDeskripsi = findViewById(R.id.txtDeskripsi);
        TextView txtJam = findViewById(R.id.txtJamBuka);
        TextView txtTelepon = findViewById(R.id.txtTelepon);
        TextView txtHarga = findViewById(R.id.txtHargaTiket);
        ImageView btnKembali = findViewById(R.id.btnKembali);

        LinearLayout btnMapWrapper = findViewById(R.id.btnMapWrapper);
        LinearLayout btnTeleponWrapper = findViewById(R.id.btnTeleponWrapper);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("nama");
        String lokasi = intent.getStringExtra("lokasi");
        String deskripsi = intent.getStringExtra("deskripsi");
        int gambar = intent.getIntExtra("gambar", 0);
        String jamBuka = intent.getStringExtra("jamBuka");
        String telepon = intent.getStringExtra("telepon");
        String hargaTiket = intent.getStringExtra("hargaTiket");

        txtNama.setText(nama);
        txtLokasi.setText(lokasi);
        txtDeskripsi.setText(deskripsi);
        imgDetail.setImageResource(gambar);

        txtJam.setText("Jam buka: " + (jamBuka != null ? jamBuka : "Tidak tersedia"));
        txtTelepon.setText("Telepon: " + (telepon != null ? telepon : "Tidak tersedia"));
        txtHarga.setText("Harga tiket: " + (hargaTiket != null ? hargaTiket : "Tidak tersedia"));

        Toast.makeText(this, "Menampilkan detail " + nama, Toast.LENGTH_SHORT).show();

        btnKembali.setOnClickListener(v -> finish());

        btnTeleponWrapper.setOnClickListener(v -> {
            if (telepon != null && !telepon.isEmpty() && !telepon.equalsIgnoreCase("N/A")) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + telepon));
                startActivity(dialIntent);
            } else {
                Toast.makeText(this, "Nomor telepon tidak tersedia", Toast.LENGTH_SHORT).show();
            }
        });

        btnMapWrapper.setOnClickListener(v -> {
            if (lokasi != null && !lokasi.isEmpty()) {

                String query = nama + ", " + lokasi;
                String encodedQuery = Uri.encode(query);

                try {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + encodedQuery);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);

                } catch (ActivityNotFoundException e) {
                    try {
                        String webUrl = "https://maps.google.com/?q=" + encodedQuery;
                        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
                        startActivity(webIntent);
                    } catch (ActivityNotFoundException ex) {
                        Toast.makeText(this, "Tidak ada aplikasi peta atau browser", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(this, "Lokasi tidak ditemukan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}