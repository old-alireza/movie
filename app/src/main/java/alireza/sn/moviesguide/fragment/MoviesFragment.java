package alireza.sn.moviesguide.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.Navigation;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import alireza.sn.moviesguide.R;
import alireza.sn.moviesguide.adapter.MoviesAdapter;
import alireza.sn.moviesguide.databinding.FragmentMoviesBinding;
import alireza.sn.moviesguide.interfaces.OnClickListener;
import alireza.sn.moviesguide.model.Data;
import alireza.sn.moviesguide.viewmodel.MoviesViewModel;

public class MoviesFragment extends Fragment implements OnClickListener {
    RecyclerView recyclerView;
    MoviesAdapter adapter;
    static FragmentMoviesBinding binding;
    Bundle bundle;

    public MoviesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments()!=null){
            bundle = getArguments();
        }

        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent("label").putExtra("label_name", (bundle == null) ? "movies" : "movie by genre"));
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);
        finds(binding);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        modifyRecyclerView();
    }

    private void modifyRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        adapter = new MoviesAdapter(this);

        if (bundle != null) {
            int id = bundle.getInt("id");
            MoviesViewModel.id=id;
            getArguments().clear();
        } else
            MoviesViewModel.id = 0;

        MoviesViewModel moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        moviesViewModel.getPagedListLiveData().observe(getViewLifecycleOwner(),data -> {
            adapter.submitList(data);
        });

        recyclerView.setAdapter(adapter);
    }

    private void finds(FragmentMoviesBinding binding) {
        recyclerView = binding.recyclerViewMovies;
    }

    public static void showProgress() {
        binding.setIsLoadingUp(true);
    }

    public static void hideProgress() {
        binding.setIsLoadingUp(false);
    }

    public static void showProgress2() {
        binding.setIsLoadingDown(true);
    }

    public static void hideProgress2() {
        binding.setIsLoadingDown(false);
    }

    @Override
    public void onClick(Data data) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        Navigation.findNavController(recyclerView)
                .navigate(R.id.action_moviesFragment_to_detailsMovieFragment, bundle);
    }

    @Override
    public void onClickById(int id) {}
}
