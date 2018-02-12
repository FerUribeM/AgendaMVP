package com.ferbajoo.agendamvp.action_interface;

import com.ferbajoo.agendamvp.database.Contact;

/**
 * Created by
 *          feuribe on 10/02/2018.
 */

public interface OnItemClickListener {

    void onItemClick(Contact contact, int adapterPosition);

    void onItemLongClick(Contact contact, int adapterPosition);
}
