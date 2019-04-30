package dev.jorik.counters.utils;

import android.net.wifi.hotspot2.pps.Credential;
import android.os.Parcel;
import android.os.Parcelable;

import dev.jorik.counters.entities.SimpleCounter;

public class SimpleCounterWrapper implements Parcelable {
    private SimpleCounter counter;

    public SimpleCounterWrapper(SimpleCounter counter) {
        this.counter = counter;
    }

    public SimpleCounter getCounter() {
        return counter;
    }

    protected SimpleCounterWrapper(Parcel in) {
        String name = in.readString();
        int count = in.readInt();
        counter = new SimpleCounter(name, count);
    }

    public static final Creator<SimpleCounterWrapper> CREATOR = new Creator<SimpleCounterWrapper>() {
        @Override
        public SimpleCounterWrapper createFromParcel(Parcel in) {
            return new SimpleCounterWrapper(in);
        }

        @Override
        public SimpleCounterWrapper[] newArray(int size) {
            return new SimpleCounterWrapper[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(counter.getName());
        dest.writeInt(counter.getCount());
    }

}