package com.example.infopariwisata;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.ViewHolder> implements Filterable {
    private final Context context;
    private final List<Wisata> wisataList;
    private final List<Wisata> wisataListFull;

    public WisataAdapter(Context context, List<Wisata> wisataList) {
        this.context = context;
        this.wisataList = new ArrayList<>(wisataList);
        this.wisataListFull = new ArrayList<>(wisataList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_wisata, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Wisata wisata = wisataList.get(position);

        holder.namaText.setText(wisata.getNama());
        holder.lokasiText.setText(wisata.getLokasi());
        holder.gambarView.setImageResource(wisata.getGambarResId());
        holder.txtRating.setText(String.valueOf(wisata.getRating()));
        holder.btnFavorite.setImageResource(
                wisata.isFavorite() ? R.drawable.ic_favorite_filled : R.drawable.ic_favorite_border
        );
        holder.btnFavorite.setOnClickListener(v -> {
            boolean newStatus = !wisata.isFavorite();
            wisata.setFavorite(newStatus);
            notifyItemChanged(holder.getAdapterPosition());

            if (newStatus) {
                Toast.makeText(context, wisata.getNama() + " ditambahkan ke favorit ❤️", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, wisata.getNama() + " dihapus dari favorit 🤍", Toast.LENGTH_SHORT).show();
            }
        });

        // ... (Logika itemView setOnClickListener biarkan saja)
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);

            intent.putExtra("nama", wisata.getNama());
            intent.putExtra("lokasi", wisata.getLokasi());
            intent.putExtra("deskripsi", wisata.getDeskripsi());
            intent.putExtra("gambar", wisata.getGambarResId());
            intent.putExtra("jamBuka", wisata.getJamBuka());
            intent.putExtra("telepon", wisata.getTelepon());
            intent.putExtra("hargaTiket", wisata.getHargaTiket());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return wisataList.size();
    }

    // ============ MODIFIKASI VIEW HOLDER DI SINI ============
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView gambarView, btnFavorite;
        TextView namaText, lokasiText;
        TextView txtRating; // <-- 2. TAMBAHKAN BARIS INI (Deklarasi)

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gambarView = itemView.findViewById(R.id.imgWisata);
            namaText = itemView.findViewById(R.id.txtNamaWisata);
            lokasiText = itemView.findViewById(R.id.txtLokasiWisata);
            btnFavorite = itemView.findViewById(R.id.btnFavorite);
            txtRating = itemView.findViewById(R.id.txtRating); // <-- 3. TAMBAHKAN BARIS INI (Inisialisasi)
        }
    }
    // ====================================================

    // ... (Filter pencarian biarkan saja)
    @Override
    public Filter getFilter() {
        return wisataFilter;
    }

    private final Filter wisataFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Wisata> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(wisataListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Wisata item : wisataListFull) {
                    if (item.getNama().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            wisataList.clear();
            wisataList.addAll((List<Wisata>) results.values);
            notifyDataSetChanged();
        }
    };
}