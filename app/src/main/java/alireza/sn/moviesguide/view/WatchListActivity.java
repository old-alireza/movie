package alireza.sn.moviesguide.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import alireza.sn.moviesguide.R;
import alireza.sn.moviesguide.adapter.WatchListAdapter;
import alireza.sn.moviesguide.databinding.ActivityWatchListBinding;
import alireza.sn.moviesguide.interfaces.WatchListListener;
import alireza.sn.moviesguide.model.Data;
import alireza.sn.moviesguide.viewmodel.DetailsViewModel;

public class WatchListActivity extends AppCompatActivity implements WatchListListener{
    DetailsViewModel watchListViewModel;
    ActivityWatchListBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_watch_list);
    }



    @Override
    protected void onResume() {
        super.onResume();
        loadWatchList();
    }

    private void loadWatchList() {
        watchListViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
        watchListViewModel.getListLiveData().observe(this, data -> {
            binding.recyclerviewWatchlist.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
            binding.recyclerviewWatchlist.setAdapter(new WatchListAdapter((data!=null)?data : new ArrayList<>(),this));
        });
    }

    @Override
    public void watchlistClick(Data data) {

    }

    @Override
    public void removeWatchlist(Data data, int position) {
    }
}