package com.ferbajoo.agendamvp.views;

import com.ferbajoo.agendamvp.database.Contact;

import java.util.List;

/**
 * Created by
 *          feuribe on 08/02/2018.
 */
public interface MainView {

    void setErrorContact(String error);

    void setSaveContact(String message, long id_contact);

    void setShowContacts(List<Contact> contact);

    void setAddNewContact(Contact ic_contact);

    void setEditContact(String message, int ic_contact, int index);

    void setAddEditContact(Contact contact, int index);

    void setDeleteContact(String message, int index);
}
