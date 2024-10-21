
package pc.wat.jakarta.demo.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("places")
public class PlacesResource {
    @Inject
    DataBean dataAccessor;
    
    @GET
    @Produces("application/json")
    public Response allPlaces() throws JsonProcessingException{
        System.out.println("WS:GET:allPlaces");
        var allPlaces = dataAccessor.getAllPlaces();
        String json = new ObjectMapper().writeValueAsString(allPlaces);
        return Response
                .ok(json)
                .build();
    }
}
