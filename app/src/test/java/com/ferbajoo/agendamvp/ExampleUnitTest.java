package com.ferbajoo.agendamvp;

import com.ferbajoo.agendamvp.interactor.MainInteractor;
import com.ferbajoo.agendamvp.presenter.MainPresenterImpl;
import com.ferbajoo.agendamvp.views.MainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Mock
    MainView view;
    @Mock
    MainInteractor iterator;

    private MainPresenterImpl presenter;

    @Before
    public void setUp() throws Exception{
        presenter = new MainPresenterImpl(view, iterator);
    }

    @Test
    public void setShowDialog () throws Exception{
        presenter.showDialogAddContact();
    }

    @Test
    public void checkIfViewReleasedOnDestroy(){
        presenter.onDestroy();
        assertNull(presenter.getMainView());
    }




}