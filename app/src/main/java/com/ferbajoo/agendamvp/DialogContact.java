package com.ferbajoo.agendamvp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ferbajoo.agendamvp.database.Contact;
import com.ferbajoo.agendamvp.interactor.ContactInteractorImpl;
import com.ferbajoo.agendamvp.interactor.MainInteractor;
import com.ferbajoo.agendamvp.presenter.ContactPresenter;
import com.ferbajoo.agendamvp.presenter.ContactPresenterImpl;
import com.ferbajoo.agendamvp.views.ContactView;

/**
 * Created by
 * feuribe on 08/02/2018.
 */

public class DialogContact extends DialogFragment implements View.OnClickListener, ContactView {

    private EditText et_contact_name, et_contact_phone;
    private TextView tv_error;
    private MainInteractor.OnMainFinishedListener listener;
    private ContactPresenter presenter;
    private Contact mContact;

    public static DialogContact newInstance(MainInteractor.OnMainFinishedListener listener, String action) {
        Bundle args = new Bundle();
        DialogContact fragment = new DialogContact();
        args.putString("ACTION", action);
        fragment.setListener(listener);
        fragment.setArguments(args);
        return fragment;
    }

    public static DialogContact newInstance(MainInteractor.OnMainFinishedListener listener, Contact contact, String action,int index) {
        Bundle args = new Bundle();
        DialogContact fragment = new DialogContact();
        args.putString("ACTION", action);
        args.putInt("INDEX", index);
        args.putSerializable("CONTACT", contact);
        fragment.setListener(listener);
        fragment.setArguments(args);
        return fragment;
    }

    public MainInteractor.OnMainFinishedListener getListener() {
        return listener;
    }

    public void setListener(MainInteractor.OnMainFinishedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window dialogFragment = getDialog().getWindow();
        if (dialogFragment != null) {
            dialogFragment.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialogFragment.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.dialog_contact, container, false);

        setCancelable(false);

        mView.findViewById(R.id.btn_save_contact).setOnClickListener(this);
        mView.findViewById(R.id.btn_cancel_contact).setOnClickListener(this);

        et_contact_name = mView.findViewById(R.id.et_contact_name);
        et_contact_phone = mView.findViewById(R.id.et_contact_phone);
        tv_error = mView.findViewById(R.id.tv_error_message);

        if (getActionEdit()) {
            mContact = getContact();
            et_contact_name.setText(mContact.getName());
            et_contact_phone.setText(mContact.getPhone());
            ((Button) mView.findViewById(R.id.btn_save_contact)).setText(R.string.label_edit);
        }

        presenter = new ContactPresenterImpl(this, new ContactInteractorImpl(getContext()));

        return mView;
    }

    public boolean getActionEdit() {
        return getArguments().getString("ACTION", "").equals("EDIT");
    }

    public int getIndex(){
        return getArguments().getInt("INDEX", 0);
    }

    public Contact getContact() {
        return (Contact) getArguments().getSerializable("CONTACT");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save_contact:
                if (getActionEdit()){
                    presenter.editContact(et_contact_name.getText().toString().trim(), et_contact_phone.getText().toString().trim(), mContact.getId_contact(),getIndex());
                }else {
                    presenter.saveContact(et_contact_name.getText().toString().trim(), et_contact_phone.getText().toString().trim());
                }
                break;
            case R.id.btn_cancel_contact:
                if (getListener() != null) {
                    getListener().OnErrorMessage("No se realizo ninguna accion!");
                }
                dismiss();
                break;
        }
    }

    @Override
    public void setErrorEmptyPhone(String message) {
        tv_error.setVisibility(View.VISIBLE);
        tv_error.setText(message);
    }

    @Override
    public void setErrorEmptyName(String message) {
        tv_error.setVisibility(View.VISIBLE);
        tv_error.setText(message);
    }

    @Override
    public void setSaveErrorContact(String error) {
        tv_error.setVisibility(View.GONE);
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSaveSuccessContact(String message, long id_contact) {
        tv_error.setVisibility(View.GONE);
        if (getListener() != null) {
            getListener().OnSaveMessage(message, id_contact);
        }
        dismiss();
    }

    @Override
    public void setEditContact(String message, int id_contact, int index) {
        if (getListener() != null){
            getListener().OnEditMessage(message,id_contact,index);
        }
        dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


}
