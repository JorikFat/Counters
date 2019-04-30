package dev.jorik.counters;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import dev.jorik.counters.entities.SimpleCounter;
import dev.jorik.counters.utils.DataSet;

public class App extends Application {
    public static List<SimpleCounter> counters = new ArrayList<>(DataSet.tempList);

    public static List<SimpleCounter> getCounters(){
        return counters;
    }
}
