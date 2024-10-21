
package pc.wat.jakarta.demo;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import pc.wat.jakarta.demo.data.DataBean;
import pc.wat.jakarta.demo.data.Person;
import pc.wat.jakarta.demo.data.Place;
import pc.wat.jakarta.demo.data.PlaceId;

@WebServlet(name = "places acquisition", urlPatterns = {"/input_places"})
public class PlacesAcquisitionServlet extends HttpServlet {

    @Inject
    DataBean dataAccessor;
    
    private static final String firstNameParamName = "city";
    private static final String secondNameParamName = "street";
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var personData = req.getParameterMap();
        
        personData.forEach( (k,vs) -> {String log = k + " " + vs.length + ":" + vs[0]; System.out.println(log);});
        
        String city = personData.get(firstNameParamName).length > 0 ? personData.get(firstNameParamName)[0] : "";
        String street = personData.get(secondNameParamName).length > 0 ? personData.get(secondNameParamName)[0] : "";
        
        Place newPlace = 
                Place
                    .builder()
                    .city(city)
                    .street(street)
                    .build();
        
        dataAccessor.addLocation(newPlace);
    }
    
}
