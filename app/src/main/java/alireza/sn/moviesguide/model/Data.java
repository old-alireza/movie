package alireza.sn.moviesguide.model;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity(tableName = "data_tb")
public class Data implements Serializable {

    @PrimaryKey
    private int id;

    private String title;
    public String poster;

    public Data() {}

    @BindingAdapter("android:ImageURL")
    public static void setImageURL (ImageView imageView , String poster){
        Picasso.get().load(poster).into(imageView);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
