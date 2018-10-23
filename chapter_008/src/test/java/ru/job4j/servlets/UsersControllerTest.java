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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ValidateService.class})
@PowerMockIgnore("javax.management.*")
public class UsersControllerTest {

    /**
     * Test doGet - get all users
     */
    @Test
    public void whenGetAllUsers() throws IOException, ServletException {
        Validate validate = new ValidateStub();
        String path = "/WEB-INF/views/UsersView.jsp";
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        Mockito.when(req.getRequestDispatcher(path)).thenReturn(dispatcher);
        validate.add(new User("kot", "Kot", "kot@kot.mya", "123", 1, new Date()));
        UsersController controller = new UsersController();
        controller.doGet(req, resp);
        assertThat(validate.findAll().iterator().next().getName(), is("Kot"));
    }
}