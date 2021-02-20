package alireza.sn.moviesguide.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import alireza.sn.moviesguide.R;
import alireza.sn.moviesguide.databinding.ItemCategoryBinding;
import alireza.sn.moviesguide.interfaces.OnClickListener;
import alireza.sn.moviesguide.model.Genres;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.Holder> {
    List<Genres> genreList;
    OnClickListener onClickListener;
    LayoutInflater inflater;

    public GenreAdapter(List<Genres> list, OnClickListener onClickListener) {
        this.genreList = list;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null)
            inflater = LayoutInflater.from(parent.getContext());

        ItemCategoryBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_category, parent, false);

        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.binding.setGenre(genreList.get(position));
        holder.itemView.setOnClickListener(v -> onClickListener.onClickById(genreList.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        ItemCategoryBinding binding;

        public Holder(@NonNull ItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
