package com.ferbajoo.agendamvp.presenter;

import com.ferbajoo.agendamvp.interactor.ContactInteractor;
import com.ferbajoo.agendamvp.interactor.MainInteractor;
import com.ferbajoo.agendamvp.views.ContactView;

/**
 * Created by
 *          feuribe on 08/02/2018.
 */

public class ContactPresenterImpl implements ContactPresenter, ContactInteractor.OnContactFinishedListenner {

    private ContactView contactView;
    private ContactInteractor contactInteractor;

    public ContactPresenterImpl(ContactView contactView, ContactInteractor contactInteractor) {
        this.contactView = contactView;
        this.contactInteractor = contactInteractor;
    }

    @Override
    public void saveContact(String name, String phone) {
        contactInteractor.onSaveContact(name, phone,this);
    }

    @Override
    public void editContact(String name, String phone, int id_contact, int index) {
        contactInteractor.onEditContact(name, phone, id_contact,index,this);
    }

    @Override
    public void deleteContact(int id_contact, int index, MainInteractor.OnMainFinishedListener listener) {
        contactInteractor.onDeteleContact(id_contact, index, listener);
    }

    @Override
    public void onDestroy() {
        contactView = null;
    }

    @Override
    public void OnErrorMessage(String error) {
        if (contactView != null){
            contactView.setSaveErrorContact(error);
        }
    }

    @Override
    public void OnSuccessMessage(String message, long id_contact) {
        if (contactView != null){
            contactView.setSaveSuccessContact(message, id_contact);
        }
    }

    @Override
    public void OnEmptyNameMessage(String message) {
        if (contactView != null){
            contactView.setErrorEmptyName(message);
        }
    }

    @Override
    public void OnEmptyPhoneMessage(String message) {
        if (contactView != null){
            contactView.setErrorEmptyPhone(message);
        }
    }

    @Override
    public void OnEditSuccessMessage(String message, int id_contact, int index) {
        if (contactView != null){
            contactView.setEditContact(message, id_contact, index);
        }
    }

    public ContactView getContactView() {
        return contactView;
    }
}
