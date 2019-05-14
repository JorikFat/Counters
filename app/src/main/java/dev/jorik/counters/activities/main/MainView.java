package dev.jorik.counters.activities.main;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import dev.jorik.counters.entities.SimpleCounter;

public interface MainView extends MvpView {
    void showToast(String message);
    void loadData(List<SimpleCounter> data);
    void addCounter(int position);
    void updateItem(int position);
    void removeItem(int position);
    void openActivity(long id);
    void createCounter();
}
