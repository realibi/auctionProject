package kz.itstep.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static final Map<String, Action> PAGES = new HashMap<>();

    public ActionFactory() {
        init();
    }

    private void init(){
        PAGES.put("GET/info", new InfoAction());
        PAGES.put("GET/hi", new HiAction());
        PAGES.put("GET/registration", new RegistrationAction());
        PAGES.put("GET/authorization", new AuthorizationAction());
        PAGES.put("GET/manageItems", new ShowManageItemsPageAction());
        PAGES.put("GET/items", new ShowItemsPageAction());
        PAGES.put("GET/changeLanguage", new ChangeLanguageAction());

        PAGES.put("POST/authorization", new LoginAction());
        PAGES.put("POST/hi", new HiAction());
        PAGES.put("POST/registration", new RegistrationAction());
        PAGES.put("POST/deleteCourse", new DeleteCourseAction());
        PAGES.put("POST/addItem", new AddItemAction());
        PAGES.put("POST/deleteItem", new DeleteItemAction());
        PAGES.put("POST/offerNewPrice", new OfferNewPriceAction());
    }

    public Action getAction(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getMethod() + request.getPathInfo());
        return PAGES.get(request.getMethod() + request.getPathInfo());
    }
}
