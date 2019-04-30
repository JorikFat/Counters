package dev.jorik.counters.activities.main.create;

import com.arellomobile.mvp.MvpView;

public interface CreateView extends MvpView {
    void onResult(boolean create);
    void passName();
}
