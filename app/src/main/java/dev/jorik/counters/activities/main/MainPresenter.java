package dev.jorik.counters.activities.main;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.InjectPresenter;

import dev.jorik.counters.R;
import dev.jorik.counters.entities.SimpleCounter;
import dev.jorik.counters.utils.DataSet;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView>
    implements
        CounterAdapter.Callback,
        View.OnClickListener{

    public void viewIsReady(){
        getViewState().loadData(DataSet.tempList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_mainA_add:
                SimpleCounter counter = DataSet.getTempCounter();
                DataSet.tempList.add(counter);
                getViewState().updateItem(DataSet.tempList.indexOf(counter));
                break;
            default:
        }
    }

    @Override
    public void onClick(int position) {
        //null
    }

    @Override
    public boolean onHold(int position) {
        return DataSet.tempList.remove(position) != null;
    }

    @Override
    public void plusClick(int position) {
        DataSet.tempList.get(position).increase();
    }

    @Override
    public void minusClick(int position) {
        DataSet.tempList.get(position).degrease();
    }
}
