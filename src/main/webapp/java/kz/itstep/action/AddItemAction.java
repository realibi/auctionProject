package kz.itstep.action;

import kz.itstep.dao.ItemDao;
import kz.itstep.entity.Item;
import kz.itstep.exception.NullSessionAttributeException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import static kz.itstep.util.AppConstant.*;

public class AddItemAction implements Action {
    private String name;
    private int categoryId;
    private int startPrice;
    private int currentPrice;
    private Timestamp startTime;
    private Timestamp endTime;
    private int userId;
    private int lastUpdatedUserId;


    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ItemDao itemDao = new ItemDao();
        try {
            fillData(request);
            Item item = setItem();
            if(itemDao.insert(item)){
                response.sendRedirect("/fs/items");
            }else{
                request.getRequestDispatcher(URL_ERROR_PAGE).forward(request, response);
            }
        } catch (NullSessionAttributeException e) {
            e.printStackTrace();
        }
    }

    private Item setItem(){
        Item item = new Item();

        item.setName(name);
        item.setCategoryId(categoryId);
        item.setStartPrice(startPrice);
        item.setCurrentPrice(currentPrice);
        item.setStartTime(startTime);
        item.setEndTime(endTime);
        item.setUserId(userId);
        item.setLastUpdatedUserId(lastUpdatedUserId);

        return item;
    }

    private void fillData(HttpServletRequest request) throws NullSessionAttributeException{
        int daysOffset = Integer.parseInt(request.getParameter("daysOffset"));

        Calendar dateWithOffset = Calendar.getInstance();
        dateWithOffset.add(Calendar.DATE, daysOffset);
        HttpSession session = request.getSession();

        name = request.getParameter("name");
        categoryId = Integer.parseInt(request.getParameter("categoryId"));
        startPrice = Integer.parseInt(request.getParameter("startPrice"));
        currentPrice = startPrice;
        startTime = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        endTime = new java.sql.Timestamp(dateWithOffset.getTime().getTime());

        if(session.getAttribute(USER_ID) != null)
            userId = Integer.parseInt(session.getAttribute("userId").toString());
        else
            throw new NullSessionAttributeException("Session attribute not found: ", "userId");

        lastUpdatedUserId = userId;
    }
}
