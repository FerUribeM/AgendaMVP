package com.ferbajoo.agendamvp.presenter;

import com.ferbajoo.agendamvp.database.Contact;

/**
 * Created by
 *              feuribe on 08/02/2018.
 */

public interface MainPresenter {

    void showDialogAddContact();

    void fillAdapter();

    void addNewElement(long id_contact);

    void onDestroy();

    void showDialogEditContact(Contact contact, int adapterPosition);

    void addEditElement(int ic_contact, int index);

    void showDialogDeleteContact(Contact contact, int adapterPosition);
}
