package dev.jorik.counters.activities.main;

import android.os.Parcelable;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import dev.jorik.counters.entities.SimpleCounter;

public interface MainView extends MvpView {
    void loadData(List<SimpleCounter> data);
    void addCounter(SimpleCounter counter);
    void updateItem(int position);
    void openActivity(Parcelable counter);
}
