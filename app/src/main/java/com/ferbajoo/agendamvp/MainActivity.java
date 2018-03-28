package com.ferbajoo.agendamvp;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.ferbajoo.agendamvp.action_interface.OnItemClickListener;
import com.ferbajoo.agendamvp.adapters.ContactAdapter;
import com.ferbajoo.agendamvp.database.Contact;
import com.ferbajoo.agendamvp.interactor.MainInteractorImpl;
import com.ferbajoo.agendamvp.presenter.MainPresenter;
import com.ferbajoo.agendamvp.presenter.MainPresenterImpl;
import com.ferbajoo.agendamvp.views.MainView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, SearchView.OnQueryTextListener, OnItemClickListener {

    private MainPresenter presenter;

    private RecyclerView rv_contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle(R.string.contacts);
            toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
            setSupportActionBar(toolbar);
        }

        rv_contacts = findViewById(R.id.rv_contacts);

        presenter = new MainPresenterImpl(this, new MainInteractorImpl(this));
        presenter.fillAdapter();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.search_contact);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
            searchView.setOnQueryTextListener(this);
        }
        if (searchView != null) {
            EditText searchEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
            searchEditText.setTextColor(getResources().getColor(android.R.color.white));
            searchEditText.setHintTextColor(getResources().getColor(android.R.color.white));
            if (searchManager != null)
                searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setOnQueryTextListener(this);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_contact) {
            presenter.showDialogAddContact();
           /* ListaFotos fotos =  new Gson().fromJson(JSONResourceReader(R.raw.listafotos),ListaFotos.class);
            Log.e("Mensaje", fotos.getStatus());*/
            return true;
        }
        return false;
    }

    public String JSONResourceReader(int id) {
        InputStream resourceReader = getResources().openRawResource(id);
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            Log.e("Exception", "Unhandled exception while using JSONResourceReader", e);
        } finally {
            try {
                resourceReader.close();
            } catch (Exception e) {
                Log.e("Exception", "Unhandled exception while using JSONResourceReader", e);
            }
        }

        return writer.toString();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void setErrorContact(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSaveContact(String message, long id_contact) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        presenter.addNewElement(id_contact);
    }

    @Override
    public void setShowContacts(List<Contact> contact) {
        createAdapter(contact);
    }

    @Override
    public void setAddNewContact(Contact contact) {
        if (rv_contacts.getAdapter() != null) {
            ((ContactAdapter) rv_contacts.getAdapter()).addNewElement(contact);
        }
    }

    @Override
    public void setEditContact(String message, int ic_contact, int index) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        presenter.addEditElement(ic_contact,index);
    }

    @Override
    public void setAddEditContact(Contact contact, int index) {
        if (rv_contacts.getAdapter() != null){
            ((ContactAdapter)rv_contacts.getAdapter()).editElement(contact, index);
        }
    }

    @Override
    public void setDeleteContact(String message, int index) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        if (rv_contacts.getAdapter() != null){
            ((ContactAdapter)rv_contacts.getAdapter()).deleteElement(index);
        }
    }

    private void createAdapter(List<Contact> contact) {
        rv_contacts.setHasFixedSize(true);
        rv_contacts.setLayoutManager(new GridLayoutManager(this, 1));
        rv_contacts.setItemAnimator(new DefaultItemAnimator());
        rv_contacts.setAdapter(new ContactAdapter(contact, this));
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (rv_contacts.getAdapter() != null) {
            ((ContactAdapter) rv_contacts.getAdapter()).getFilter().filter(query);
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (rv_contacts.getAdapter() != null) {
            ((ContactAdapter) rv_contacts.getAdapter()).getFilter().filter(newText);
        }
        return true;
    }

    @Override
    public void onItemClick(Contact contact, int adapterPosition) {
        presenter.showDialogEditContact(contact, adapterPosition);
    }

    @Override
    public void onItemLongClick(Contact contact, int adapterPosition) {
        presenter.showDialogDeleteContact(contact, adapterPosition);
    }
}
