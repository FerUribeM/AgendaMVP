package com.ferbajoo.agendamvp.interactor;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.ferbajoo.agendamvp.DialogContact;
import com.ferbajoo.agendamvp.database.Contact;
import com.ferbajoo.agendamvp.database.Database;
import com.ferbajoo.agendamvp.DialogDeleteContact;
import com.ferbajoo.agendamvp.views.ShowContacts;

/**
 * Created by
 * feuribe on 08/02/2018.
 */

public class MainInteractorImpl implements MainInteractor {

    private Context context;

    public MainInteractorImpl(Context context) {
        this.context = context;
    }


    @Override
    public void showAddContactDialog(OnMainFinishedListener listener) {
        DialogContact dialog = DialogContact.newInstance(listener,"ADD");
        dialog.show(((AppCompatActivity)context).getSupportFragmentManager(),"DialogContact");
    }

    @Override
    public void showEditContactDialog(OnMainFinishedListener listener, Contact contact, int adapterPosition) {
        DialogContact dialog = DialogContact.newInstance(listener, contact,"EDIT",adapterPosition);
        dialog.show(((AppCompatActivity)context).getSupportFragmentManager(),"DialogContact");
    }

    @Override
    public void showDeleteContact(OnMainFinishedListener listener, Contact contact, int adapterPosition) {
        DialogDeleteContact dialog = DialogDeleteContact.newInstance(listener, contact,adapterPosition);
        dialog.show(((AppCompatActivity)context).getSupportFragmentManager(),"DialogContact");
    }

    @Override
    public void fillAdapterContacts(OnMainFinishedListener listener) {
        new ShowContacts(listener, context).execute();
    }

    @Override
    public void addNewContact(OnMainFinishedListener listener, long id_contact) {
        listener.OnAddNewContact(Database.contactDao.fetchContactById((int) id_contact));
    }

    @Override
    public void addEditContact(OnMainFinishedListener listener, int ic_contact, int index) {
        listener.OnAddEditContact(Database.contactDao.fetchContactById(ic_contact),index);
    }

}
