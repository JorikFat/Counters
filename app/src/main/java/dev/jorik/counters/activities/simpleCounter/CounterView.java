package dev.jorik.counters.activities.simpleCounter;

import com.arellomobile.mvp.MvpView;

import dev.jorik.counters.entities.SimpleCounter;

public interface CounterView extends MvpView {
    void setupTitle(String name);
    void updateValue(String newVal);
}
