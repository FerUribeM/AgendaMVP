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

import com.ferbajoo.agendamvp.database.Contact;
import com.ferbajoo.agendamvp.databinding.DialogContactDeleteBinding;
import com.ferbajoo.agendamvp.interactor.ContactInteractorImpl;
import com.ferbajoo.agendamvp.interactor.MainInteractor;
import com.ferbajoo.agendamvp.presenter.ContactPresenter;
import com.ferbajoo.agendamvp.presenter.ContactPresenterImpl;

/**
 * Created by
 * feuribe on 10/02/2018.
 */

public class DialogDeleteContact extends DialogFragment implements View.OnClickListener{

    private ContactPresenter presenter;
    private MainInteractor.OnMainFinishedListener listener;

    public static DialogDeleteContact newInstance(MainInteractor.OnMainFinishedListener listener, Contact contact, int adapterPosition) {
        Bundle args = new Bundle();
        args.putInt("INDEX", adapterPosition);
        args.putSerializable("CONTACT", contact);
        DialogDeleteContact fragment = new DialogDeleteContact();
        fragment.setListener(listener);
        fragment.setArguments(args);
        return fragment;
    }

    public void setListener(MainInteractor.OnMainFinishedListener listener) {
        this.listener = listener;
    }

    public MainInteractor.OnMainFinishedListener getListener() {
        return listener;
    }


    public Contact getContact() {
        return (Contact) getArguments().getSerializable("CONTACT");
    }

    public int getIndex(){
        return getArguments().getInt("INDEX", 0);
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
        DialogContactDeleteBinding binding = DialogContactDeleteBinding.inflate(LayoutInflater.from(getContext()), container, false);

        setCancelable(false);

        binding.btnCancelContactDel.setOnClickListener(this);
        binding.btnDeleteContact.setOnClickListener(this);

        binding.setContact(getContact());

        presenter = new ContactPresenterImpl(null,new ContactInteractorImpl(getContext()));

        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel_contact_del:
                if (getListener() != null){
                    getListener().OnErrorMessage("No se realizo ninguna acción!");
                }
                dismiss();
                break;
            case R.id.btn_delete_contact:
                presenter.deleteContact(getContact().getId_contact(),getIndex(), getListener());
                dismiss();
                break;
        }
    }



}
