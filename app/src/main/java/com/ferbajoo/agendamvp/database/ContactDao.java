package com.ferbajoo.agendamvp.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * feuribe on 09/02/2018.
 */

public class ContactDao extends DbContentProvider implements IContactSchema, IContactDao {

    public static final String TAG = ContactDao.class.getSimpleName();

    private Cursor cursor;
    private ContentValues contentValues;
    private Contact contact;

    public ContactDao(SQLiteDatabase mDb) {
        super(mDb);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Contact cursorToEntity(Cursor cursor) {
        Contact contact = new Contact();
        if (cursor != null) {
            if (cursor.getColumnIndex(COLUMN_ID) != -1) {
                contact.setId_contact(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            }

            if (cursor.getColumnIndex(COLUMN_NAME) != -1) {
                contact.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
            }

            if (cursor.getColumnIndex(COLUMN_PHONE) != -1) {
                contact.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE)));
            }
        }

        return contact;
    }

    @Override
    public Contact fetchContactById(int contactID) {
        final String selectionArgs[] = {String.valueOf(contactID)};
        final String selection = String.format("%s = ?", COLUMN_ID);

        Contact contact = new Contact();
        cursor = super.query(CONTACT_TABLE, CONTACT_COLUMNS, selection, selectionArgs, COLUMN_ID);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                contact = cursorToEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return contact;
    }


    @Override
    public List<Contact> fetchAllContact() {
        List<Contact> contactList = new ArrayList<>();
        cursor = super.query(CONTACT_TABLE, CONTACT_COLUMNS, null, null, COLUMN_ID);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Contact contact = cursorToEntity(cursor);
                contactList.add(contact);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return contactList;
    }

    @Override
    public long addContact(Contact contact) {
        setContentValue(contact);
        try {
            return super.insert(CONTACT_TABLE, getContentValues());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean update(Contact contact) {
        setContentValue(contact);
        String selection = String.format("%s = ?", COLUMN_ID);
        String[] args = new String[]{String.valueOf(contact.getId_contact())};
        try {
            return super.update(CONTACT_TABLE, getContentValues(), selection, args) > 0;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return false;
    }


    @Override
    public boolean addContacts(List<Contact> contacts) {
        return false;
    }

    @Override
    public boolean deleteAllContacts() {
        return false;
    }

    @Override
    public boolean deleteContact(int contactID) {
        String selection = String.format("%s = ?", COLUMN_ID);
        String[] args = new String[]{String.valueOf(contactID)};
        try {
            return super.delete(CONTACT_TABLE, selection, args) > 0;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return false;
    }

    private void setContentValue(Contact contact) {
        contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, contact.getName());
        contentValues.put(COLUMN_PHONE, contact.getPhone());
    }

    private ContentValues getContentValues() {
        return contentValues;
    }

}
