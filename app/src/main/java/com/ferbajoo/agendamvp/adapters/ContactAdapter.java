package com.ferbajoo.agendamvp.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.ferbajoo.agendamvp.action_interface.OnItemClickListener;
import com.ferbajoo.agendamvp.database.Contact;
import com.ferbajoo.agendamvp.databinding.ItemContactsBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * feuribe on 09/02/2018.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactsViewHolder> implements Filterable {

    private List<Contact> contacts, contactsFilter;
    private OnItemClickListener mListener;
    private Filter mFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return resultValue.toString().trim();
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            List<Contact> suggestion = new ArrayList<>();

            if (charSequence.length() == 0) {
                suggestion.addAll(contacts);
            } else {
                for (Contact contact : contacts) {
                    if (contact.getName().toLowerCase().trim().contains(charSequence.toString().toLowerCase().trim()) ||
                            contact.getPhone().toLowerCase().trim().contains(charSequence.toString().toLowerCase().trim())) {
                        suggestion.add(contact);
                    }
                }
            }
            results.values = suggestion;
            results.count = suggestion.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            contactsFilter = new ArrayList<>();
            //noinspection unchecked
            contactsFilter.addAll((List<Contact>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public ContactAdapter(List<Contact> contacts, OnItemClickListener listener) {
        this.contacts = this.contactsFilter = contacts;
        this.mListener = listener;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemContactsBinding binding = ItemContactsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ContactsViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        holder.binding.setContacts(contactsFilter.get(position));
    }

    @Override
    public int getItemCount() {
        return contactsFilter.size();
    }


    public void addNewElement(Contact contact) {
        contactsFilter.add(contact);
        notifyItemInserted(contactsFilter.size());
    }

    public void editElement(Contact contact, int index) {
        contactsFilter.set(index,contact);
        notifyItemChanged(index);
    }

    public void deleteElement(int index) {
        contactsFilter.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        ItemContactsBinding binding;

        ContactsViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() > -1) {
                mListener.onItemClick(contactsFilter.get(getAdapterPosition()), getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (getAdapterPosition() > -1) {
                mListener.onItemLongClick(contactsFilter.get(getAdapterPosition()), getAdapterPosition());
            }
            return true;
        }
    }
}
