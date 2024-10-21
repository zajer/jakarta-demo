
package pc.wat.jakarta.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import pc.wat.jakarta.demo.data.Person;
import pc.wat.jakarta.demo.data.Place;
import pc.wat.jakarta.demo.data.PlaceId;

@WebServlet(name = "people acquisition", urlPatterns = {"/input_people"})
public class PeopleAcquisitionServlet extends PeopleManagementBase {
    
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
        
        String firstName = personData.get(FIRST_NAME_PARAM_NAME).length > 0 ? personData.get(FIRST_NAME_PARAM_NAME)[0] : "";
        String secondName = personData.get(SECOND_NAME_PARAM_NAME).length > 0 ? personData.get(SECOND_NAME_PARAM_NAME)[0] : "";
        String idAsText = personData.get(PERSON_IDENTIFICATION_NUMBER_PARAM_NAME).length > 0 ? personData.get(PERSON_IDENTIFICATION_NUMBER_PARAM_NAME)[0] : null;
        String friendsIdsAsText = personData.get(FRIENDS_IDENTIFICATION_NUMBERS_PARAM_NAME).length > 0 ? personData.get(FRIENDS_IDENTIFICATION_NUMBERS_PARAM_NAME)[0] : null;
        String city = personData.get(CITY_PARAM_NAME).length > 0 ? personData.get(CITY_PARAM_NAME)[0] : null;
        String street = personData.get(STREET_NAME_PARAM_NAME).length > 0 ? personData.get(STREET_NAME_PARAM_NAME)[0] : null;
        
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
        super.doPost(req, resp);
    }
    
}
