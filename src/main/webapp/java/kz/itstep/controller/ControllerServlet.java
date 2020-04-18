package kz.itstep.controller;
import kz.itstep.action.Action;
import kz.itstep.action.ActionFactory;
import kz.itstep.dao.UserDao;
import kz.itstep.entity.User;
import kz.itstep.filter.EncodingFilter;
import kz.itstep.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ControllerServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(ControllerServlet.class);

    ActionFactory actionFactory;
    @Override
    public void init() throws ServletException {
        super.init();
        actionFactory = new ActionFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Controller servlet " + req.getPathInfo());
        resp.setContentType("text/html");
        Action action = actionFactory.getAction(req, resp);
        if(action != null){
            action.service(req,resp);
        } else {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.dispose();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.destroy();
    }

}
