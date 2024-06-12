package com.criticalsoftware;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;


@Path("/users")

public class UserResource {

    @Inject
    private UserRepository repository;

        //This method handles GET requests to /users/{id}.
        //It retrieves a user by its ID and returns it in the response.
        @GET
        @Path("/{id}")
        public Response get(@PathParam("id") String id){
            User user = repository.findById(new ObjectId(id));
            return Response.ok(user).build();
        }

        //This method handles GET requests to /users.
        //It retrieves all users entities and returns them in the response.
        @GET
        public Response get() {
            return Response.ok(repository.listAll()).build();
        }

        //his method handles GET requests to /users/search/{name}
        //It retrieves a user by its name and returns it in the response.
        //If no such user is found, it returns a 404 Not Found status.
        @GET
        @Path("/search/{name}")
        public Response search(@PathParam("name") String name) {
            User user = repository.findByName(name);
            return user != null ? Response.ok(user).build() : Response.status(Response.Status.NOT_FOUND).build();
        }

}
