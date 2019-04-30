package dev.jorik.counters.activities.simpleCounter;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import dev.jorik.counters.R;
import dev.jorik.counters.entities.SimpleCounter;

@InjectViewState
public class CounterPresenter extends MvpPresenter<CounterView>
    implements
        View.OnClickListener{
    private CounterModel model;

    public CounterPresenter(SimpleCounter counter){
        model = new CounterModel(counter);
    }

    public void viewIsReady(){
        getViewState().setupTitle(model.getCounter().getName());
        getViewState().updateValue(String.valueOf(model.getCounter().getCount()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_counterA_minus:
                model.getCounter().degrease();
                break;
            case R.id.btn_counterA_plus:
                model.getCounter().increase();
                break;
        }
        getViewState().updateValue(String.valueOf(model.getCounter().getCount()));
    }
}
