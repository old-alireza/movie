package alireza.sn.moviesguide.interfaces;

import alireza.sn.moviesguide.model.Data;

public interface WatchListListener {
    void watchlistClick (Data data);

    void removeWatchlist(Data data , int position);
}
