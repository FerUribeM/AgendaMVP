package com.ferbajoo.agendamvp.database;

import java.io.Serializable;

/**
 * Created by
 *          feuribe on 09/02/2018.
 */

public class Contact implements Serializable{


    private int id_contact;
    private String name;
    private String phone;

    public int getId_contact() {
        return id_contact;
    }

    public void setId_contact(int id_contact) {
        this.id_contact = id_contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
