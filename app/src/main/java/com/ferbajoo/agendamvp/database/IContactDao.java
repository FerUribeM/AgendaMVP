package com.ferbajoo.agendamvp.database;

import java.util.List;

/**
 * Created by
 * feuribe on 09/02/2018.
 */
public interface IContactDao {

    Contact fetchContactById(int contactID);

    List<Contact> fetchAllContact();

    long addContact(Contact contact);

    boolean update(Contact contact);

    boolean addContacts(List<Contact> contacts);

    boolean deleteAllContacts();

    boolean deleteContact(int contactID);

}
