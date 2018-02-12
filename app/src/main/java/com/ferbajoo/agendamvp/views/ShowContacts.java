package com.ferbajoo.agendamvp.views;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;

import com.ferbajoo.agendamvp.R;
import com.ferbajoo.agendamvp.database.Contact;
import com.ferbajoo.agendamvp.database.Database;
import com.ferbajoo.agendamvp.interactor.MainInteractor;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by
 * feuribe on 09/02/2018.
 */

public class ShowContacts extends AsyncTask<Void, Integer, Boolean> {

    private WeakReference<MainInteractor.OnMainFinishedListener> wListenner;
    private WeakReference<Context> wContext;
    private Dialog mDialog;
    private List<Contact> mContact;

    public ShowContacts(MainInteractor.OnMainFinishedListener listener, Context context) {
        this.wListenner = new WeakReference<>(listener);
        this.wContext = new WeakReference<>(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (wContext.get() != null) {
            mDialog = new Dialog(wContext.get(), R.style.Theme_AppCompat_DayNight_Dialog);
            mDialog.setContentView(R.layout.dialog_loading);
            mDialog.setCancelable(false);
            mDialog.show();
        }


    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        mContact = Database.contactDao.fetchAllContact();
        return true;
    }


    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if (result) {
            wListenner.get().OnShowContacts(mContact);
        }
        if (mContact.size() <= 0) {
            wListenner.get().OnErrorMessage("No se encontro información");
        }

        mDialog.dismiss();
    }
}
