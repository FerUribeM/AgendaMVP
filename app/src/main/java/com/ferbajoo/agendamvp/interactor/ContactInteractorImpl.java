package com.ferbajoo.agendamvp.interactor;

import android.content.Context;
import android.text.TextUtils;

import com.ferbajoo.agendamvp.database.Contact;
import com.ferbajoo.agendamvp.database.Database;

/**
 * Created by
 *          feuribe on 08/02/2018.
 */

public class ContactInteractorImpl implements ContactInteractor {

    private Context context;

    public ContactInteractorImpl(Context context) {
        this.context = context;
    }


    @Override
    public void onSaveContact(String name, String phone, OnContactFinishedListenner listenner) {
        if (TextUtils.isEmpty(name)){
            listenner.OnEmptyNameMessage("El nombre no puede quedar vacio");
            return;
        }
        if (TextUtils.isEmpty(phone)){
            listenner.OnEmptyPhoneMessage("El numero de telefono no puede quedar vacio");
            return;
        }
        Contact contact = new Contact();
        contact.setName(name);
        contact.setPhone(phone);

        long id_contact = Database.contactDao.addContact(contact);
        if (id_contact > 0){
            listenner.OnSuccessMessage("Usuario guardado con éxito", id_contact);
        }else {
            listenner.OnErrorMessage("Error al guardar al usuario");
        }
    }

    @Override
    public void onEditContact(String name, String phone, int id_contact,int index, OnContactFinishedListenner listenner) {
        if (TextUtils.isEmpty(name)){
            listenner.OnEmptyNameMessage("El nombre a editar no puede ser vacio");
            return;
        }
        if (TextUtils.isEmpty(phone)){
            listenner.OnEmptyPhoneMessage("El numero a editar no puede ser vacio");
            return;
        }

        Contact contact = new Contact();
        contact.setId_contact(id_contact);
        contact.setName(name);
        contact.setPhone(phone);

        if (Database.contactDao.update(contact)) {
            listenner.OnEditSuccessMessage("Contacto actualizado con éxito",id_contact,index);
        }else {
            listenner.OnErrorMessage("Error al actualizar el contacto");
        }

    }

    @Override
    public void onDeteleContact(int id_contact, int index, MainInteractor.OnMainFinishedListener listener) {
        if (Database.contactDao.deleteContact(id_contact)){
            listener.OnDeleteMessage("El contacto se elimino con éxito", index);
        }else {
            listener.OnErrorMessage("Error al eliminar el contacto");
        }
    }
}
