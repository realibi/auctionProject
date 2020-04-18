package kz.itstep.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static kz.itstep.util.AppConstant.*;

public class ChangeLanguageAction implements Action {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("language");

        ResourceBundle bundle = ResourceBundle.getBundle("resources", new Locale(language));

        HttpSession session = request.getSession(true);
        session.setAttribute(BUNDLE, bundle);

        request.getRequestDispatcher(URL_ITEMS_PAGE).forward(request, response);
    }
}
