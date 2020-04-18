package kz.itstep.action;

import kz.itstep.dao.ItemDao;
import kz.itstep.entity.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static kz.itstep.util.AppConstant.*;

public class OfferNewPriceAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("userId") != null){
            int userId = (Integer)request.getSession().getAttribute("userId");
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            int newPrice = Integer.parseInt(request.getParameter("newPrice"));

            ItemDao itemDao = new ItemDao();
            Item item = itemDao.findById(itemId);

            if(itemDao.updateItemCurrentPrice(item, newPrice, userId)){
                response.sendRedirect("/fs/items");
            }
            else{
                request.getRequestDispatcher(URL_ERROR_PAGE).forward(request, response);
            }
        }else{
            System.out.println("user is unauthorized!");
            request.getRequestDispatcher(URL_AUTHORIZATION_PAGE).forward(request, response);
        }
    }
}
