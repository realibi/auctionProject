package kz.itstep.action;

import kz.itstep.dao.UserDao;
import kz.itstep.entity.User;
import kz.itstep.exception.CheckException;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static kz.itstep.util.AppConstant.URL_AUTHORIZATION_PAGE;
import static kz.itstep.util.AppConstant.URL_REGISTRATION_PAGE;


public class RegistrationAction implements Action {
    private String login;
    private String password;
    private String retypePassword;
    private String firstName;
    private String secondName;

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getMethod().equals("GET")){
            request.getRequestDispatcher(URL_REGISTRATION_PAGE).forward(request, response);
        } else if(request.getMethod().equals("POST")){
            UserDao userDao = new UserDao();
            fillData(request);
            try {
                checkFields();
                User user = setUser();
                if(userDao.insert(user)){
                    request.getRequestDispatcher(URL_AUTHORIZATION_PAGE).forward(request, response);
                } else {
                    request.getRequestDispatcher(URL_REGISTRATION_PAGE).forward(request, response);
                }

            } catch (CheckException e){
                e.printStackTrace();
                System.out.println(e.getMessage() + " " + e.getField());
                request.getRequestDispatcher(URL_REGISTRATION_PAGE).forward(request, response);
            }

        }

    }

    private void checkFields() throws CheckException {
        if(login.length() < 5) throw new CheckException("Field not correct: ", "login");
        if(password.length() < 5 || !password.equals(retypePassword))
            throw new CheckException("Field not correct: ", "password");
        if(firstName.length() < 2) throw new CheckException("Field not correct: ", "firstName");
        if(secondName.length() < 2) throw new CheckException("Field not correct: ", "secondName");
    }

    private User setUser(){
        User user = new User();
        user.setSecondName(firstName);
        user.setFirstName(secondName);
        user.setLogin(login);
        user.setPassword(DigestUtils.md5Hex(password));
        return user;
    }

    private void fillData(HttpServletRequest request){
        login = request.getParameter("login");
        password = request.getParameter("password");
        retypePassword = request.getParameter("password2");
        firstName = request.getParameter("firstName");
        secondName = request.getParameter("secondName");
    }
}