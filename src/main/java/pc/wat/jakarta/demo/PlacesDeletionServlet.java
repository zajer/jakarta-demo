
package pc.wat.jakarta.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import pc.wat.jakarta.demo.data.Place;

@WebServlet(name = "places deletion", urlPatterns = {"/delete_places"})
public class PlacesDeletionServlet extends PlacesManagementBase {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var personData = req.getParameterMap();
        
        personData.forEach( (k,vs) -> {String log = k + " " + vs.length + ":" + vs[0]; System.out.println(log);});
        
        String city = personData.get(FIRST_NAME_PARAM_NAME).length > 0 ? personData.get(FIRST_NAME_PARAM_NAME)[0] : "";
        String street = personData.get(SECOND_NAME_PARAM_NAME).length > 0 ? personData.get(SECOND_NAME_PARAM_NAME)[0] : "";
        
        Place existingPlace = 
                Place
                    .builder()
                    .city(city)
                    .street(street)
                    .build();
        
        dataAccessor.deleteLocation(existingPlace);
        super.doPost(req, resp);
    }
    
}
