package com.criticalsoftware;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.net.URI;
import java.net.URISyntaxException;


@Path("/users")
public class UserResource {
    @Inject
    UserRepository repository;

    @POST
    public Response create(@Valid UserRequest userRequest) throws URISyntaxException {
        try {
            User user = new User(userRequest.getName(), userRequest.getDateBirth(), userRequest.getContact());
            repository.persist(user);
            return Response.created(new URI("/users/" + user.id)).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Status.BAD_REQUEST).entity("Validation error: " + e.getConstraintViolations()).build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Error creating user: " + e.getMessage()).build();
        }
    }
}