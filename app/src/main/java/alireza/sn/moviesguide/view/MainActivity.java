package alireza.sn.moviesguide.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import alireza.sn.moviesguide.R;
import alireza.sn.moviesguide.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    NavController controller;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //set title action bar
        binding.setLabelName("movies");

        finds();

        NavigationUI.setupWithNavController(bottomNavigationView, controller);
    }

    private void modifyActionBar() {
//        Set<Integer> set = new HashSet<>();
//        set.add(R.id.moviesFragment);
//        set.add(R.id.categoryFragment);
//        AppBarConfiguration configuration = new AppBarConfiguration.Builder(set).build();
//        NavigationUI.setupActionBarWithNavController(this, controller, configuration);
    }

    private void finds() {
        bottomNavigationView = binding.buttonNav;
        controller = Navigation.findNavController(this, R.id.nav_host_fragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

   BroadcastReceiver setActionBarTitle = new BroadcastReceiver() {
       @Override
       public void onReceive(Context context, Intent intent) {
           if (intent.getExtras()!=null){
               binding.setLabelName(intent.getExtras().getString("label_name"));
               if (binding.searIcon.getVisibility() == View.GONE){
                   binding.searIcon.setVisibility(View.VISIBLE);
                   binding.watchlistIcon.setVisibility(View.VISIBLE);
               }
           }

           //to hide the watchlist in detail fragment
           else{
               binding.searIcon.setVisibility(View.GONE);
               binding.watchlistIcon.setVisibility(View.GONE);
           }
       }
   } ;

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(setActionBarTitle,new IntentFilter("label"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(setActionBarTitle);
    }

    public void goToWatchList(View view) {
        Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.action_moviesFragment_to_watchlistFragment);
    }
}