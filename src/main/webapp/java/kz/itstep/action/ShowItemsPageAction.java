package kz.itstep.action;

import kz.itstep.dao.ItemDao;
import kz.itstep.entity.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static kz.itstep.util.AppConstant.ITEMS;
import static kz.itstep.util.AppConstant.URL_ITEMS_PAGE;

public class ShowItemsPageAction implements Action {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ItemDao itemDao = new ItemDao();
        List<Item> items = itemDao.findAllAvailableItems();
        request.setAttribute(ITEMS, items);

        request.getRequestDispatcher(URL_ITEMS_PAGE).forward(request, response);
    }
}
