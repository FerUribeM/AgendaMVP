package com.ferbajoo.agendamvp.interactor;

import com.ferbajoo.agendamvp.database.Contact;

import java.util.List;

/**
 * Created by
 * feuribe on 08/02/2018.
 */

public interface MainInteractor {

    interface OnMainFinishedListener {
        void OnErrorMessage(String error);

        void OnSaveMessage(String message, long id_contact);

        void OnShowContacts(List<Contact> mContact);

        void OnAddNewContact(Contact id_contact);

        void OnEditMessage(String message, int ic_contact, int index);

        void OnAddEditContact(Contact contact, int index);

        void OnDeleteMessage(String message, int index);
    }


    void showAddContactDialog(OnMainFinishedListener listener);

    void showEditContactDialog(OnMainFinishedListener listener, Contact contact, int adapterPosition);

    void showDeleteContact(OnMainFinishedListener listener, Contact contact, int adapterPosition);

    void fillAdapterContacts(OnMainFinishedListener listener);

    void addNewContact(OnMainFinishedListener listener, long id_contact);

    void addEditContact(OnMainFinishedListener listener, int ic_contact, int index);

}
