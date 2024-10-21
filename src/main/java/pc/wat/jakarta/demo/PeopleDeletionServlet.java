
package pc.wat.jakarta.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import static pc.wat.jakarta.demo.PeopleManagementBase.CITY_PARAM_NAME;
import static pc.wat.jakarta.demo.PeopleManagementBase.FIRST_NAME_PARAM_NAME;
import static pc.wat.jakarta.demo.PeopleManagementBase.FRIENDS_IDENTIFICATION_NUMBERS_PARAM_NAME;
import static pc.wat.jakarta.demo.PeopleManagementBase.PERSON_IDENTIFICATION_NUMBER_PARAM_NAME;
import static pc.wat.jakarta.demo.PeopleManagementBase.SECOND_NAME_PARAM_NAME;
import static pc.wat.jakarta.demo.PeopleManagementBase.STREET_NAME_PARAM_NAME;
import pc.wat.jakarta.demo.data.Person;
import pc.wat.jakarta.demo.data.Place;
import pc.wat.jakarta.demo.data.PlaceId;

@WebServlet(name="people deletion",urlPatterns={"/delete_people"})
public class PeopleDeletionServlet extends PeopleManagementBase {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var personData = req.getParameterMap();
        
        personData.forEach( (k,vs) -> {String log = k + " " + vs.length + ":" + vs[0]; System.out.println(log);});
        
        String idAsText = personData.get(PERSON_IDENTIFICATION_NUMBER_PARAM_NAME).length > 0 ? personData.get(PERSON_IDENTIFICATION_NUMBER_PARAM_NAME)[0] : null;
        int id = Integer.parseInt(idAsText);
        
        Person existingPerson = 
                Person.builder()
                        .identificationNumber(id)
                        .build();
        
        dataAccessor.deletePerson(existingPerson);
    }
}
