package com.ferbajoo.agendamvp;

import com.ferbajoo.agendamvp.interactor.ContactInteractor;
import com.ferbajoo.agendamvp.presenter.ContactPresenterImpl;
import com.ferbajoo.agendamvp.views.ContactView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by
 *          feuribe on 15/02/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class DialogTest {

    @Mock
    ContactView view;
    @Mock
    ContactInteractor interactor;

    private ContactPresenterImpl presenter;


    @Before
    public void setUp() throws Exception {
        presenter = new ContactPresenterImpl(view, interactor);
    }

    @Test
    public void saveContact(){
        presenter.saveContact("Fer","3317189371");
        verify(view,times(2)).setErrorEmptyName("El nombre no puede quedar vacio");
    }

    @Test
    public void emptyName(){
        presenter.OnEmptyNameMessage("El nombre no puede quedar vacio");
        verify(view,times(2)).setErrorEmptyName("El nombre no puede quedar vacio");
    }

 /*   @Test
    public void editContact(){
        presenter.editContact("Fer","3317189371",0,0);
        verify(view,times(1)).setEditContact("Guardado con éxto", 1, 2);
    }

    @Test
    public void emptyNameMessage(){
        presenter.OnEmptyNameMessage("Error");
        verify(view,times(1)).setErrorEmptyName("Error el campo esta vacio");
    }
*/

    @Test
    public void checkOnDestroy(){
        presenter.onDestroy();
        assertNull(presenter.getContactView());
    }

}
