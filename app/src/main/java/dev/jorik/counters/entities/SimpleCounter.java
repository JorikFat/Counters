package dev.jorik.counters.entities;

public class SimpleCounter {
    private String name;
    private int count;

    public SimpleCounter(String name) {
        this.name = name;
        count = 0;
    }

    public SimpleCounter(String name, int count){
        this.name = name;
        this.count = count;
    }

    public void increase(){
        this.count++;
    }

    public void degrease(){
        this.count--;
    }

    public int getCount(){
        return count;
    }

    public String getName() {
        return this.name;
    }
}
