package kz.itstep.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static kz.itstep.util.AppConstant.BUNDLE;
import static kz.itstep.util.AppConstant.USER_ID;

public class LanguageSettingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Language setting filter started");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        if(request.getSession().getAttribute(BUNDLE) != null){
            ResourceBundle bundle = ResourceBundle.getBundle("resources", new Locale("ru"));
            HttpSession session = request.getSession(true);
            session.setAttribute(BUNDLE, bundle);
        }
    }

    @Override
    public void destroy() {

    }
}
