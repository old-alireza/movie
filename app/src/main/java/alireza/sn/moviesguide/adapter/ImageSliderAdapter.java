package alireza.sn.moviesguide.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import alireza.sn.moviesguide.R;
import alireza.sn.moviesguide.databinding.ItemContainerSliderImageBinding;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ViewHolder> {
LayoutInflater inflater;
List<String> imgUrlList;

    public ImageSliderAdapter(List<String> list) {
        this.imgUrlList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater==null)
            inflater = LayoutInflater.from(parent.getContext());

        ItemContainerSliderImageBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.item_container_slider_image,parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setImageURL(imgUrlList.get(position));
    }

    @Override
    public int getItemCount() {
        return imgUrlList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ItemContainerSliderImageBinding binding;

        public ViewHolder(@NonNull ItemContainerSliderImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
