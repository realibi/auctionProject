package kz.itstep.action;

import kz.itstep.dao.CourseDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteCourseAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("courseId");
        CourseDao courseDao = new CourseDao();
        courseDao.delete(Integer.parseInt(login));
        response.sendRedirect("/fs/hi");
    }
}
