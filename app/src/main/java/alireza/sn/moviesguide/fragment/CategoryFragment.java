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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import alireza.sn.moviesguide.R;
import alireza.sn.moviesguide.adapter.GenreAdapter;
import alireza.sn.moviesguide.databinding.FragmentCategoryBinding;
import alireza.sn.moviesguide.interfaces.OnClickListener;
import alireza.sn.moviesguide.model.Data;
import alireza.sn.moviesguide.model.Genres;
import alireza.sn.moviesguide.viewmodel.CategoryViewModel;

public class CategoryFragment extends Fragment implements OnClickListener {
    FragmentCategoryBinding binding;
    RecyclerView recyclerView;

    public CategoryFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent("label").putExtra("label_name", "category"));
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
        finds(binding);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        modifyRecycler();
    }

    private void modifyRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        getCategoryResponse();
    }

    private void getCategoryResponse() {
        CategoryViewModel viewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        viewModel.getMutableLiveData().observe(getViewLifecycleOwner(), list -> {
            recyclerView.setAdapter(new GenreAdapter(list, CategoryFragment.this));
        });
    }

    private void finds(FragmentCategoryBinding binding) {
        recyclerView = binding.recyclerViewCategory;
    }

    @Override
    public void onClick(Data data) {}

    @Override
    public void onClickById(int id) {
        Bundle bundle = new Bundle();
        bundle.putString("kind","by_genre");
        bundle.putInt("id",id);
        Navigation.findNavController(recyclerView).navigate(R.id.action_categoryFragment_to_moviesFragment,bundle);

    }
}