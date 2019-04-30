package dev.jorik.counters.utils;

import android.support.v4.widget.SlidingPaneLayout;

import java.util.ArrayList;
import java.util.List;

import dev.jorik.counters.entities.SimpleCounter;

public class DataSet {
    public static List<SimpleCounter> tempList = new ArrayList<>();
    static {
        tempList.add(new SimpleCounter("первый", 0));
        tempList.add(new SimpleCounter("второй", 8));
        tempList.add(new SimpleCounter("третий", 48));
        tempList.add(new SimpleCounter("четвертый", 100));
        tempList.add(new SimpleCounter("пятый", 98));
        tempList.add(new SimpleCounter("шестой", 1));
    }

    public static SimpleCounter getTempCounter(){
        return new SimpleCounter("Созданный", 555);
    }
}
