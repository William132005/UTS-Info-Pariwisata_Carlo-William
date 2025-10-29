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
                "Candi Borobudur",
                "Magelang, Jawa Tengah",
                "Candi Borobudur adalah candi Buddha terbesar di dunia dan salah satu keajaiban dunia yang dibangun pada abad ke-8.",
                R.drawable.borobudur,
                "09:00 - 17:00 WIB",
                "(0293) 788266",
                "Lokal: Rp 50.000"
        ));

        wisataList.add(new Wisata(
                "Gunung Bromo",
                "Probolinggo, Jawa Timur",
                "Gunung Bromo terkenal dengan pemandangan matahari terbit yang spektakuler dan lautan pasir yang luas.",
                R.drawable.bromo,
                "24 Jam (Kawasan)",
                "(0335) 541011",
                "Lokal: Mulai Rp 29.000"
        ));

        wisataList.add(new Wisata(
                "Tanah Lot",
                "Tabanan, Bali",
                "Tanah Lot adalah pura yang terletak di atas batu besar di tepi laut, menjadi ikon wisata utama di Bali.",
                R.drawable.tanahlot,
                "07:00 - 19:00 WITA",
                "(0361) 880361",
                "Lokal: Rp 20.000"
        ));

        wisataList.add(new Wisata(
                "Raja Ampat",
                "Papua Barat Daya",
                "Raja Ampat terkenal sebagai surga bawah laut dunia dengan keindahan terumbu karang dan biota laut yang sangat kaya.",
                R.drawable.rajaampat,
                "24 Jam (Kawasan)",
                "N/A",
                "PIN/Tarif Jasa: Rp 500.000"
        ));

        wisataList.add(new Wisata(
                "Labuan Bajo",
                "Nusa Tenggara Timur",
                "Labuan Bajo merupakan pintu gerbang menuju Pulau Komodo, dengan pemandangan laut dan sunset yang memukau.",
                R.drawable.labuanbajo,
                "24 Jam (Kawasan)",
                "N/A",
                "Tergantung Pulau"
        ));

        wisataList.add(new Wisata(
                "Danau Toba",
                "Sumatera Utara",
                "Danau Toba adalah danau vulkanik terbesar di dunia, dikelilingi pemandangan pegunungan dan Pulau Samosir di tengahnya.",
                R.drawable.danautoba,
                "24 Jam (Kawasan)",
                "N/A",
                "Gratis (Kawasan)"
        ));

        wisataList.add(new Wisata(
                "Pulau Komodo",
                "Nusa Tenggara Timur",
                "Pulau Komodo adalah habitat asli hewan purba Komodo, sekaligus salah satu situs warisan dunia UNESCO.",
                R.drawable.pulaukomodo,
                "07:00 - 17:00 WITA",
                "(0385) 41004",
                "Lokal: Mulai Rp 150.000"
        ));

        wisataList.add(new Wisata(
                "Tana Toraja",
                "Sulawesi Selatan",
                "Tana Toraja terkenal dengan kebudayaan unik, rumah adat Tongkonan, dan upacara adat pemakaman Rambu Solo.",
                R.drawable.tanatoraja,
                "08:00 - 17:00 WITA",
                "N/A",
                "Tergantung Lokasi"
        ));

        wisataList.add(new Wisata(
                "Ubud",
                "Gianyar, Bali",
                "Ubud adalah pusat seni dan budaya Bali dengan suasana alam sawah, galeri seni, dan yoga retreat yang terkenal.",
                R.drawable.ubud,
                "24 Jam (Kawasan)",
                "N/A",
                "Gratis (Kawasan)"
        ));

        wisataList.add(new Wisata(
                "Pulau Lombok",
                "Nusa Tenggara Barat",
                "Pulau Lombok memiliki pantai-pantai indah, Gunung Rinjani, dan budaya Sasak yang khas.",
                R.drawable.lombok,
                "24 Jam (Kawasan)",
                "N/A",
                "Gratis (Kawasan)"
        ));

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