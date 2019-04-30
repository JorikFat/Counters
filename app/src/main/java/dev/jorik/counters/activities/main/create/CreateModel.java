package dev.jorik.counters.activities.main.create;

import dev.jorik.counters.App;
import dev.jorik.counters.entities.SimpleCounter;

public class CreateModel {

    public void addCounter(SimpleCounter counter) {
        App.getCounters().add(counter);
    }
}
