package kz.itstep.action;

import kz.itstep.dao.ItemDao;
import kz.itstep.dao.UserDao;
import kz.itstep.entity.Item;
import kz.itstep.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static kz.itstep.util.AppConstant.*;


public class  LoginAction implements Action {
    private static Logger logger = Logger.getLogger(LoginAction.class);

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDao userDao = new UserDao();

        String login = request.getParameter("login");
        String password = DigestUtils.md5Hex(request.getParameter("password"));
        logger.info("Trying to identify user: " + login);
        User user = userDao.findByLoginAndPassword(login,password);
        if(user != null){
            System.out.println("Success");

            HttpSession session = request.getSession(true);
            session.setAttribute(USER_ID, user.getId());

            setItemsLists(request);
            request.getRequestDispatcher(URL_ITEMS_PAGE).forward(request, response);
        } else {
            logger.error("User wasn't found: " + login);
            System.out.println("user not found");
            request.setAttribute(LOGIN_ERROR, true);
            request.getRequestDispatcher(URL_AUTHORIZATION_PAGE).forward(request, response);
        }
        System.out.println(login + " " + password);

    }

    private void setItemsLists(HttpServletRequest request){
        ItemDao itemDao = new ItemDao();
        List<Item> items = itemDao.findAllAvailableItems();
        request.setAttribute(ITEMS, items);
    }
}
