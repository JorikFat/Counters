package dev.jorik.counters.activities.main;

import android.net.wifi.hotspot2.pps.Credential;

import java.util.List;

import dev.jorik.counters.entities.SimpleCounter;

public class MainModel {
    private List<SimpleCounter> data;

    public MainModel(List<SimpleCounter> data) {
        this.data = data;
    }

    public List<SimpleCounter> getData() {
        return data;
    }
}
