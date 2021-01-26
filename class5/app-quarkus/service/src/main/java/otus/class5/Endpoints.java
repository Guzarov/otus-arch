package otus.class5;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class Endpoints {

    @GET
    @Path("/health")
    public HealthResponse health(){
        return new HealthResponse(HealthResponse.Status.OK);
    }

    @GET
    public Response index(){
        return Response.noContent().status(Response.Status.OK).build();
    }

}
