package ru.job4j.servlets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.service.Validate;
import ru.job4j.service.ValidateService;
import ru.job4j.service.ValidateStub;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * UserCreateController Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ValidateService.class})
@PowerMockIgnore("javax.management.*")
public class UserCreateControllerTest {

    /**
     * Test doPost
     */
    @Test
    public void whenCreateUserThenStoreIt() throws IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("login")).thenReturn("kot");
        when(req.getParameter("name")).thenReturn("Kot");
        when(req.getParameter("email")).thenReturn("kot@kot.mya");
        when(req.getParameter("password")).thenReturn("kot");
        when(req.getParameter("role")).thenReturn("1");
        UserCreateController controller = new UserCreateController();
        controller.doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getName(), is("Kot"));
    }
}