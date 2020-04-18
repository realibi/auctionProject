package kz.itstep.action;

import kz.itstep.dao.CourseDao;
import kz.itstep.dao.ItemDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteItemAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ItemDao itemDao= new ItemDao();
        itemDao.delete(id);
        response.sendRedirect("/fs/manageItems");
    }
}
