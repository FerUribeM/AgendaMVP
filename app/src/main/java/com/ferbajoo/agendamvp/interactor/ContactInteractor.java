package com.ferbajoo.agendamvp.interactor;

/**
 * Created by
 *          feuribe on 08/02/2018.
 */

public interface ContactInteractor {

    interface OnContactFinishedListenner{

        void OnErrorMessage(String error);
        void OnSuccessMessage(String message, long id_contact);

        void OnEmptyNameMessage(String message);

        void OnEmptyPhoneMessage(String message);

        void OnEditSuccessMessage(String message, int id_contact, int index);

    }

    void onDeteleContact(int id_contact, int index, MainInteractor.OnMainFinishedListener listener);

    void onSaveContact(String name, String phone, OnContactFinishedListenner listenner);

    void onEditContact(String name, String phone, int id_contact,int index, OnContactFinishedListenner listenner);


}
