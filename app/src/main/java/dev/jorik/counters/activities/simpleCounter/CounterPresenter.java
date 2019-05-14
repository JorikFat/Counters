package dev.jorik.counters.activities.simpleCounter;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import dev.jorik.counters.R;
import dev.jorik.counters.model.DbHandler;

@InjectViewState
public class CounterPresenter extends MvpPresenter<CounterView>
    implements
        View.OnClickListener{
    private CounterModel model;

    public CounterPresenter(DbHandler database, long id){
        model = new CounterModel(database, id);
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
        model.updateRx();
        getViewState().updateValue(String.valueOf(model.getCounter().getCount()));
    }
}
