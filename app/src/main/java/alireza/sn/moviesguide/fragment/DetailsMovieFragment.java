package alireza.sn.moviesguide.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import alireza.sn.moviesguide.R;
import alireza.sn.moviesguide.adapter.ImageSliderAdapter;
import alireza.sn.moviesguide.databinding.FragmentDetailsMovieBinding;
import alireza.sn.moviesguide.model.Data;
import alireza.sn.moviesguide.viewmodel.DetailsViewModel;

public class DetailsMovieFragment extends Fragment {
    FragmentDetailsMovieBinding binding;
    DetailsViewModel detailsViewModel;

    public DetailsMovieFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent("label"));

        binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_details_movie, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (detailsViewModel == null) {

            detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);

            detailsViewModel.getMutableLiveData(((Data) getArguments().get("data")).getId()).observe(getViewLifecycleOwner(), details -> {
                if (details!=null)
                binding.setDetail(details);

                binding.watchListImg.setOnClickListener(v -> {

                    detailsViewModel.addToWatchList((Data) getArguments().get("data"));
                    Toast.makeText(getActivity(), "add to watch list", Toast.LENGTH_SHORT).show();
                    binding.watchListImg.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_favorite));
                });

                if (details.getImages() != null) {
                    binding.layoutIndicator.setVisibility(View.VISIBLE);
                    setImageSlider(details.getImages());
                    setCurrentSliderIndicator(0);

                    binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                            setCurrentSliderIndicator(position);
                        }
                    });
                }

            });

        }
    }

    private void setImageSlider(List<String> images) {
        binding.viewPager.setAdapter(new ImageSliderAdapter(images));

        ImageView[] imageView = new ImageView[images.size()];

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(4, 0, 4, 0);

        for (int i = 0; i < imageView.length; i++) {
            imageView[i] = new ImageView(getContext());
            imageView[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.indicator_inactive));
            imageView[i].setLayoutParams(params);
            binding.layoutIndicator.addView(imageView[i]);
        }
    }

    public void setCurrentSliderIndicator(int position) {
        int childCount = binding.layoutIndicator.getChildCount();

        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) binding.layoutIndicator.getChildAt(i);

            if (i == position)
                imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.indicatior_active));
            else
                imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.indicator_inactive));
        }
    }
}