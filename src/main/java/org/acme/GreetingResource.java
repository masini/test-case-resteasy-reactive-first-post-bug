package org.acme;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class GreetingResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("TERMINAL_ROLE")
    public TestDTO save(TestDTO n) {

        return n;
    }

    @GET
    public void call() {
        Client client = ClientBuilder.newClient();

        client.target("http://localhost:8082/resources/test")
                .request().accept(MediaType.APPLICATION_JSON_TYPE).header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .post(Entity.entity("{}", MediaType.APPLICATION_JSON_TYPE));
    }
}