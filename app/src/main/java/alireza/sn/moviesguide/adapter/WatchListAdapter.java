package alireza.sn.moviesguide.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import alireza.sn.moviesguide.R;
import alireza.sn.moviesguide.databinding.ItemMoviesBinding;
import alireza.sn.moviesguide.interfaces.WatchListListener;
import alireza.sn.moviesguide.model.Data;

public class WatchListAdapter extends RecyclerView.Adapter< WatchListAdapter.Holder> {
    WatchListListener watchListListener;
    LayoutInflater inflater;
    List<Data> dataList;

    public WatchListAdapter(List<Data> data,WatchListListener watchListListener) {
        this.watchListListener = watchListListener;
        this.dataList = data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null)
            inflater = LayoutInflater.from(parent.getContext());

        ItemMoviesBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.item_movies, parent, false);

        //show remove icon in watchlist
        binding.removeItemWatchlist.setVisibility(View.VISIBLE);

        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.binding.setData(dataList.get(position));
        holder.itemView.setOnClickListener(v -> watchListListener.watchlistClick(dataList.get(position)));
        holder.binding.removeItemWatchlist.setOnClickListener(v -> watchListListener.removeWatchlist(dataList.get(position),position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ItemMoviesBinding binding;

        public Holder(@NonNull ItemMoviesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
