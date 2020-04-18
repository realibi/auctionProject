package kz.itstep.action;

import kz.itstep.dao.CategoryDao;
import kz.itstep.dao.ItemDao;
import kz.itstep.dao.SaleDao;
import kz.itstep.entity.Category;
import kz.itstep.entity.Sale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import static kz.itstep.util.AppConstant.*;

public class ShowManageItemsPageAction implements Action {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute(USER_ID) != null){
            int userId = (Integer)request.getSession().getAttribute(USER_ID);

            CategoryDao categoryDao = new CategoryDao();
            List<Category> categories = categoryDao.findAll();
            Timestamp currentDate = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
            ItemDao itemDao = new ItemDao();
            itemDao.updateItems();

            SaleDao saleDao = new SaleDao();
            List<Sale> sales = saleDao.getUserTopFive(userId);

            request.setAttribute(CATEGORIES, categories);
            request.setAttribute(USER_SALES, sales);
            request.setAttribute(CURRENT_DATE, currentDate);
            request.setAttribute(USER_ID, userId);
            request.setAttribute(USER_ITEMS, itemDao.getItemsByUserId(userId));
            request.getRequestDispatcher(URL_MANAGE_ITEMS_PAGE).forward(request, response);
        }else{
            System.out.println("user is unauthorized!");
            request.getRequestDispatcher(URL_AUTHORIZATION_PAGE).forward(request, response);
        }
    }
}
