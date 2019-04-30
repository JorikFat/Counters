package dev.jorik.counters.activities.main.create;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class CreateDialog extends MvpAppCompatDialogFragment implements CreateView{
    @InjectPresenter CreatePresenter presenter;
    private EditText name;
    private Callback callback;

    public static CreateDialog constructor(Callback callback){
        CreateDialog dialog = new CreateDialog();
        dialog.setCallback(callback);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        name = new EditText(getActivity());
        name.setHint("Имя счетчика");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Создать счетчик");
        builder.setView(name);
        builder.setPositiveButton("ok", presenter);
        builder.setNegativeButton("cancel", presenter);
        return builder.create();
    }

    private void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onResult(boolean create) {
//        if (create){
//            callback.result(create);
//        } else {
//            dismiss();
//        }
        callback.result(create);
    }

    @Override
    public void passName() {
        presenter.createDialog(this.name.getText().toString());
    }

    public interface Callback{
        void result(boolean create);
    }
}
