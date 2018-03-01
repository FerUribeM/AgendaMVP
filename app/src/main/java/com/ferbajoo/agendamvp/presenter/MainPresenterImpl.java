package com.ferbajoo.agendamvp.presenter;

import com.ferbajoo.agendamvp.database.Contact;
import com.ferbajoo.agendamvp.interactor.MainInteractor;
import com.ferbajoo.agendamvp.views.MainView;

import java.util.List;

/**
 * Created by
 *          feuribe on 08/02/2018.
 */

public class MainPresenterImpl implements MainPresenter, MainInteractor.OnMainFinishedListener {

    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenterImpl(MainView mainView, MainInteractor mainInteractor) {
        this.mainView = mainView;
        this.mainInteractor = mainInteractor;
    }

    @Override
    public void showDialogAddContact() {
        mainInteractor.showAddContactDialog(this);
    }

    @Override
    public void showDialogEditContact(Contact contact, int adapterPosition) {
        mainInteractor.showEditContactDialog(this, contact, adapterPosition);
    }

    @Override
    public void showDialogDeleteContact(Contact contact, int adapterPosition) {
        mainInteractor.showDeleteContact(this,contact, adapterPosition);
    }

    @Override
    public void fillAdapter() {
        mainInteractor.fillAdapterContacts(this);
    }

    @Override
    public void addNewElement(long id_contact) {
        mainInteractor.addNewContact(this, id_contact);
    }

    @Override
    public void addEditElement(int ic_contact, int index) {
        mainInteractor.addEditContact(this, ic_contact, index);
    }


    @Override
    public void OnErrorMessage(String error) {
        if (mainView != null){
            mainView.setErrorContact(error);
        }
    }

    @Override
    public void OnSaveMessage(String message, long id_contact) {
        if (mainView != null){
            mainView.setSaveContact(message, id_contact);
        }
    }

    @Override
    public void OnShowContacts(List<Contact> contact) {
        if (mainView != null){
            mainView.setShowContacts(contact);
        }
    }

    @Override
    public void OnAddNewContact(Contact contact) {
        if (mainView != null){
            mainView.setAddNewContact(contact);
        }
    }

    @Override
    public void OnEditMessage(String message, int ic_contact, int index) {
        if (mainView != null){
            mainView.setEditContact(message, ic_contact, index);
        }
    }

    @Override
    public void OnAddEditContact(Contact contact, int index) {
        if (mainView != null){
            mainView.setAddEditContact(contact, index);
        }
    }

    @Override
    public void OnDeleteMessage(String message, int index) {
        if (mainView != null){
            mainView.setDeleteContact(message,index);
        }
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    public MainView getMainView() {
        return mainView;
    }
}
