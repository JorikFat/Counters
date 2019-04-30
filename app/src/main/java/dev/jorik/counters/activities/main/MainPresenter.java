package dev.jorik.counters.activities.main;

import android.graphics.ColorSpace;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import dev.jorik.counters.R;
import dev.jorik.counters.entities.SimpleCounter;
import dev.jorik.counters.utils.DataSet;
import dev.jorik.counters.utils.SimpleCounterWrapper;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView>
    implements
        CounterAdapter.Callback,
        View.OnClickListener{
    private MainModel model;

    public MainPresenter(List<SimpleCounter> counters) {
        this.model = new MainModel(counters);
    }

    public void viewIsReady(){
        getViewState().loadData(model.getData());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_mainA_add:
                SimpleCounter counter = DataSet.getTempCounter();
                model.getData().add(counter);
                getViewState().updateItem(model.getData().indexOf(counter));
                break;
            default:
        }
    }

    @Override
    public void onClick(int position) {
        getViewState().openActivity(position);
    }

    @Override
    public boolean onHold(int position) {
        return model.getData().remove(position) != null;
    }

    @Override
    public void plusClick(int position) {
        model.getData().get(position).increase();
    }

    @Override
    public void minusClick(int position) {
        model.getData().get(position).degrease();
    }
}
