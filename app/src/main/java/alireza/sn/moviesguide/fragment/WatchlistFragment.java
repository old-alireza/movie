package alireza.sn.moviesguide.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import alireza.sn.moviesguide.R;
import alireza.sn.moviesguide.adapter.WatchListAdapter;
import alireza.sn.moviesguide.databinding.ActivityWatchListBinding;
import alireza.sn.moviesguide.databinding.FragmentWatchlistBinding;
import alireza.sn.moviesguide.interfaces.WatchListListener;
import alireza.sn.moviesguide.model.Data;
import alireza.sn.moviesguide.viewmodel.DetailsViewModel;


public class WatchlistFragment extends Fragment implements WatchListListener {
    DetailsViewModel watchListViewModel;
    FragmentWatchlistBinding binding;
    WatchListAdapter adapter;

    public WatchlistFragment() {}

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent("label").putExtra("label_name", "watchlist"));

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_watchlist,container,false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadWatchList();
    }

    private void loadWatchList() {

        watchListViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
        watchListViewModel.getListLiveData().observe(getViewLifecycleOwner(), data -> {
            adapter = new WatchListAdapter((data!=null)?data : new ArrayList<>(),this);
            binding.recyclerviewWatchlist.setLayoutManager(new GridLayoutManager(getContext(),3));
            binding.recyclerviewWatchlist.setAdapter(adapter);
        });
    }

    @Override
    public void watchlistClick(Data data) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",data);
        Navigation.findNavController(binding.recyclerviewWatchlist).navigate(R.id.action_watchlistFragment_to_detailsMovieFragment,bundle);
    }

    @Override
    public void removeWatchlist(Data data, int position) {
        watchListViewModel.removeFromWatchlist(data);
        adapter.notifyItemRemoved(position);
    }
}