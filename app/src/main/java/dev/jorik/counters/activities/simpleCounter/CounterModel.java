package dev.jorik.counters.activities.simpleCounter;

import dev.jorik.counters.entities.SimpleCounter;

public class CounterModel {
    private SimpleCounter counter;

    public CounterModel(SimpleCounter counter) {
        this.counter = counter;
    }

    public SimpleCounter getCounter() {
        return counter;
    }
}
