package otus.class5;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/health")
@Produces(MediaType.APPLICATION_JSON)
public class Endpoint {

    @GET
    public HealthResponse health(){
        return new HealthResponse(HealthResponse.Status.OK);
    }

}
