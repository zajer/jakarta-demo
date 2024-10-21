
package pc.wat.jakarta.demo;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebFilter(filterName = "throttle filter",urlPatterns = {"/person.html","/place.html"})
public class ThrotleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest hsr = (HttpServletRequest) sr;
        var cookies = hsr.getCookies();
        boolean isHaltingCookiePresent = Arrays.stream(cookies).anyMatch( cookie -> cookie.getName().equals("data_modified") );
        if (isHaltingCookiePresent)
        {
            HttpServletResponse hsresp = (HttpServletResponse) sr1;
            hsresp.sendRedirect("wait.html");
        }
        fc.doFilter(sr, sr1);
        
    }
    
}
