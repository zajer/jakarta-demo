package pc.wat.jakarta.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CommonServlet extends HttpServlet {
    
    protected void addThrottlePreventingCookie(HttpServletResponse resp){
        var throttleCookie = new Cookie("data_modified","ignoreMe");
        throttleCookie.setMaxAge(10);
        resp.addCookie(throttleCookie);
    }
    protected void redirectAfterSuccess(HttpServletResponse resp) throws IOException{
        resp.sendRedirect("success.html");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addThrottlePreventingCookie(resp);
        redirectAfterSuccess(resp);
    }
    
}
