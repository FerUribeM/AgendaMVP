package com.ferbajoo.agendamvp.database;

/**
 * Created by
 *          feuribe on 09/02/2018.
 */

public interface IContactSchema {

    String CONTACT_TABLE = "contacts";
    String COLUMN_ID = "id_contact";
    String COLUMN_NAME = "name";
    String COLUMN_PHONE = "phone";

    String CONTACT_TABLE_CREATE = String.format("CREATE TABLE IF NOT EXISTS  %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL,%s TEXT NOT NULL)",
            CONTACT_TABLE,COLUMN_ID,COLUMN_NAME,COLUMN_PHONE);

    String CONTACT_TABLE_DROP = String.format("DROP TABLE IF EXISTS  %s",CONTACT_TABLE);


    String[] CONTACT_COLUMNS = new String[]{COLUMN_ID,COLUMN_NAME, COLUMN_PHONE};



}
