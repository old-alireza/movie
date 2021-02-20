package alireza.sn.moviesguide.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import alireza.sn.moviesguide.R;
import alireza.sn.moviesguide.databinding.ItemMoviesBinding;
import alireza.sn.moviesguide.interfaces.OnClickListener;
import alireza.sn.moviesguide.model.Data;

public class MoviesAdapter extends PagedListAdapter<Data, MoviesAdapter.Holder> {
    OnClickListener onClickListener;
    LayoutInflater inflater;

    public MoviesAdapter(OnClickListener onClickListener) {
        super(dataItemCallback);
        this.onClickListener = onClickListener;
    }

    static DiffUtil.ItemCallback<Data> dataItemCallback = new DiffUtil.ItemCallback<Data>() {
        @Override
        public boolean areItemsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null)
            inflater = LayoutInflater.from(parent.getContext());

        ItemMoviesBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.item_movies, parent, false);

        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.binding.setData(getItem(position));
        holder.itemView.setOnClickListener(v -> onClickListener.onClick(getItem(position)));
    }

    static class Holder extends RecyclerView.ViewHolder {
        ItemMoviesBinding binding;

        public Holder(@NonNull ItemMoviesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
