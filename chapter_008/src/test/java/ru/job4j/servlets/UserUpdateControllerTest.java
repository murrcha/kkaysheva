package ru.job4j.servlets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.service.User;
import ru.job4j.service.Validate;
import ru.job4j.service.ValidateService;
import ru.job4j.service.ValidateStub;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * UserUpdateController Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ValidateService.class})
@PowerMockIgnore("javax.management.*")
public class UserUpdateControllerTest {

    /**
     * Test doPost - update user
     */
    @Test
    public void whenUpdateUserThenUserUpdated() throws IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        User user = new User("dog", "Dog", "dog@dog.woof", "123", 1, new Date());
        validate.add(user);
        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("login")).thenReturn("kot");
        when(req.getParameter("name")).thenReturn("Kot");
        when(req.getParameter("email")).thenReturn("kot@kot.mya");
        when(req.getParameter("password")).thenReturn("kot");
        when(req.getParameter("role")).thenReturn("1");
        UserUpdateController controller = new UserUpdateController();
        controller.doPost(req, resp);
        assertThat(validate.findById(1).getName(), is("Kot"));
        assertThat(validate.findAll().iterator().next().getName(), is("Kot"));
    }
}