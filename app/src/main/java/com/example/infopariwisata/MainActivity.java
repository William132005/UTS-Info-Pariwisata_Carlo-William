package com.example.infopariwisata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WisataAdapter adapter;
    private List<Wisata> wisataList;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);

        wisataList = new ArrayList<>();

        wisataList.add(new Wisata(
                "Candi Borobudur", "Magelang, Jawa Tengah",
                "Candi Borobudur adalah candi Buddha terbesar di dunia...",
                R.drawable.borobudur,
                "09:00 - 17:00 WIB", "(0293) 788266", "Lokal: Rp 50.000",
                4.8 // Rating
        ));

        wisataList.add(new Wisata(
                "Gunung Bromo", "Probolinggo, Jawa Timur",
                "Gunung Bromo terkenal dengan pemandangan matahari terbit...",
                R.drawable.bromo,
                "24 Jam (Kawasan)", "(0335) 541011", "Lokal: Mulai Rp 29.000",
                4.8 // Rating
        ));

        wisataList.add(new Wisata(
                "Tanah Lot", "Tabanan, Bali",
                "Tanah Lot adalah pura yang terletak di atas batu besar...",
                R.drawable.tanahlot,
                "07:00 - 19:00 WITA", "(0361) 880361", "Lokal: Rp 20.000",
                4.6 // Rating
        ));

        wisataList.add(new Wisata(
                "Raja Ampat", "Papua Barat Daya",
                "Raja Ampat terkenal sebagai surga bawah laut dunia...",
                R.drawable.rajaampat,
                "24 Jam (Kawasan)", "N/A", "PIN/Tarif Jasa: Rp 500.000",
                4.8 // Rating
        ));

        wisataList.add(new Wisata(
                "Labuan Bajo", "Nusa Tenggara Timur",
                "Labuan Bajo merupakan pintu gerbang menuju Pulau Komodo...",
                R.drawable.labuanbajo,
                "24 Jam (Kawasan)", "N/A", "Tergantung Pulau",
                4.6 // Rating
        ));

        wisataList.add(new Wisata(
                "Danau Toba", "Sumatera Utara",
                "Danau Toba adalah danau vulkanik terbesar di dunia...",
                R.drawable.danautoba,
                "24 Jam (Kawasan)", "N/A", "Gratis (Kawasan)",
                4.7 // Rating
        ));

        wisataList.add(new Wisata(
                "Pulau Komodo", "Nusa Tenggara Timur",
                "Pulau Komodo adalah habitat asli hewan purba Komodo...",
                R.drawable.pulaukomodo,
                "07:00 - 17:00 WITA", "(0385) 41004", "Lokal: Mulai Rp 150.000",
                4.7 // Rating
        ));

        wisataList.add(new Wisata(
                "Tana Toraja", "Sulawesi Selatan",
                "Tana Toraja terkenal dengan kebudayaan unik...",
                R.drawable.tanatoraja,
                "08:00 - 17:00 WITA", "N/A", "Tergantung Lokasi",
                4.6 // Rating
        ));

        wisataList.add(new Wisata(
                "Ubud", "Gianyar, Bali",
                "Ubud adalah pusat seni dan budaya Bali...",
                R.drawable.ubud,
                "24 Jam (Kawasan)", "N/A", "Gratis (Kawasan)",
                4.5 // Rating
        ));

        wisataList.add(new Wisata(
                "Pulau Lombok", "Nusa Tenggara Barat",
                "Pulau Lombok memiliki pantai-pantai indah...",
                R.drawable.lombok,
                "24 Jam (Kawasan)", "N/A", "Gratis (Kawasan)",
                4.5 // Rating
        ));

        // --------------------------------------------------

        adapter = new WisataAdapter(this, wisataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}