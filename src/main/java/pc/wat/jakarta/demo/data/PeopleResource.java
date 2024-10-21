package pc.wat.jakarta.demo.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("people")
public class PeopleResource {
    
    @Inject
    DataBean dataAccessor;
    
    @GET
    @Produces("application/json")
    public Response allPeople() throws JsonProcessingException{
        System.out.println("WS:GET:allPeople");
        var allPeople = dataAccessor.getAllPeople();
        String json = new ObjectMapper().writeValueAsString(allPeople);
        return Response
                .ok(json)
                .build();
    }
}
