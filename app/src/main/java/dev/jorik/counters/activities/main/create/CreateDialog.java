package dev.jorik.counters.activities.main.create;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import dev.jorik.counters.App;
import dev.jorik.counters.R;

public class CreateDialog extends MvpAppCompatDialogFragment implements CreateView{
    @InjectPresenter CreatePresenter presenter;
    private EditText name;
    private Callback callback;

    @ProvidePresenter
    public CreatePresenter providePresenter(){
        return new CreatePresenter(((App)getActivity().getApplication()).getDatabase());
    }

    public static CreateDialog constructor(Callback callback){
        CreateDialog dialog = new CreateDialog();
        dialog.setCallback(callback);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        name = new EditText(getActivity());
        name.setHint(R.string.createDialog_nameHint);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.createDialog_title);
        builder.setView(name);
        builder.setPositiveButton(R.string.createDialog_positiveButton, presenter);
        builder.setNegativeButton(R.string.createDialog_negativeButton, presenter);
        return builder.create();
    }

    private void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onResult(boolean create) {
        callback.result(create);
    }

    @Override
    public void passName() {
        presenter.createCounter(this.name.getText().toString());
    }

    public interface Callback{
        void result(boolean create);
    }
}
