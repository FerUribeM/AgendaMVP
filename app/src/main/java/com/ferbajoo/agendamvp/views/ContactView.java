package com.ferbajoo.agendamvp.views;

/**
 * Created by
 *          feuribe on 08/02/2018.
 */

public interface ContactView {

    void setErrorEmptyPhone(String message);

    void setErrorEmptyName(String message);

    void setSaveErrorContact(String error);

    void setSaveSuccessContact(String message, long id_contact);

    void setEditContact(String message, int id_contact, int index);

}
