package org.acme;

import io.smallrye.mutiny.Uni;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Path("/test")
//@Blocking
public class GreetingResource {

    @GET
    @Path("1")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("TERMINAL_ROLE")
    public List<TestDTO> getAll() {
        return Collections.emptyList();
    }

    @POST
    @Path("2")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("TERMINAL_ROLE")
    public Uni<TestDTO> save(TestDTO n) {

        return Uni.createFrom().item(n);
    }

    @GET
    public void call() {
        Client client = ClientBuilder.newClient();

        client.target("http://localhost:8082/resources/test/1")
                .request().accept(MediaType.APPLICATION_JSON_TYPE).header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .get();

        client.target("http://localhost:8082/resources/test/2")
                .request().accept(MediaType.APPLICATION_JSON_TYPE).header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .post(Entity.entity("{}", MediaType.APPLICATION_JSON_TYPE));
    }
}