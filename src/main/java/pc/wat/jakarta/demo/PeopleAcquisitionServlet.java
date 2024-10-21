
package pc.wat.jakarta.demo;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import pc.wat.jakarta.demo.data.DataBean;
import pc.wat.jakarta.demo.data.Person;
import pc.wat.jakarta.demo.data.Place;
import pc.wat.jakarta.demo.data.PlaceId;

@WebServlet(name = "people acquisition", urlPatterns = {"/input_people"})
public class PeopleAcquisitionServlet extends HttpServlet {

    private static final String firstNameParamName = "fname";
    private static final String secondNameParamName = "sname";
    private static final String personIdentificationNumberParamName = "pid";
    private static final String friendsIdentifiactionNumbersParamName = "fids";
    private static final String cityParamName = "city";
    private static final String streetParamName = "street";
    
    @Inject
    DataBean dataAccessor;
    
    private List<Integer> parseFriendsIds(String friendsIdsSeperatedBySemicolon){
        if (! friendsIdsSeperatedBySemicolon.equals(""))
            return Arrays
                .stream(friendsIdsSeperatedBySemicolon.split(";"))
                .map(fidAsString -> Integer.valueOf(fidAsString))
                .toList();
        else
            return new LinkedList();
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var personData = req.getParameterMap();
        
        personData.forEach( (k,vs) -> {String log = k + " " + vs.length + ":" + vs[0]; System.out.println(log);});
        
        String firstName = personData.get(firstNameParamName).length > 0 ? personData.get(firstNameParamName)[0] : "";
        String secondName = personData.get(secondNameParamName).length > 0 ? personData.get(secondNameParamName)[0] : "";
        String idAsText = personData.get(personIdentificationNumberParamName).length > 0 ? personData.get(personIdentificationNumberParamName)[0] : null;
        String friendsIdsAsText = personData.get(friendsIdentifiactionNumbersParamName).length > 0 ? personData.get(friendsIdentifiactionNumbersParamName)[0] : null;
        String city = personData.get(cityParamName).length > 0 ? personData.get(cityParamName)[0] : null;
        String street = personData.get(streetParamName).length > 0 ? personData.get(streetParamName)[0] : null;
        
        int id = Integer.parseInt(idAsText);
        List<Integer> friendsIds = parseFriendsIds(friendsIdsAsText);
        
        Place place = dataAccessor.getPlace(new PlaceId(city,street));
        List<Person> friends = dataAccessor.getPeopleByIds(friendsIds);
        
        Person newPerson = 
                Person.builder()
                        .firstName(firstName)
                        .lastName(secondName)
                        .identificationNumber(id)
                        .friends(friends)
                        .location(place)
                        .build();
        
        dataAccessor.addPerson(newPerson);
    }
    
}
