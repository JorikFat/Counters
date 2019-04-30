package dev.jorik.counters.activities.main;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import dev.jorik.counters.entities.SimpleCounter;

public interface MainView extends MvpView {
    void showToast(String message);
    void loadData(List<SimpleCounter> data);
    void addCounter(SimpleCounter counter);
    void updateItem(int position);
    void openActivity(int id);
    void createCounter();
}
