package com.ferbajoo.agendamvp.presenter;

import com.ferbajoo.agendamvp.interactor.MainInteractor;

/**
 * Created by
 *          feuribe on 08/02/2018.
 */

public interface ContactPresenter {

    void saveContact(String name, String phone);

    void editContact(String name, String phone, int id_contact, int index);

    void deleteContact(int id_contact, int index, MainInteractor.OnMainFinishedListener listener);

    void onDestroy();
}
