package com.criticalsoftware;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Path("/users")

public class UserResource {

    @Inject
    private UserRepository repository;

        //This method handles GET requests to /users/{id}.
        //It retrieves a user by its ID and returns the userResponse in the response.
        @GET
        @Path("/{id}")
        public Response getById(@PathParam("id") String id){
            User user = repository.findById(new ObjectId(id));
            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("User not found.").build();
            }
            UserResponse userResponse = new UserResponse(user.getId(), user.getName(), user.getDateBirth(), user.getContact());
            return Response.ok(userResponse).build();
        }

        //This method handles GET requests to /users.
        //It retrieves all users entities and returns as userResponses in the response.
        @GET
        public Response getAll() {
            //Creates an empty User List
            List<User> users = new ArrayList<>();
            users = repository.listAll();

            //Creates an empty userResponse list
            List<UserResponse> userResponses = new ArrayList<>();
            //For each User, a UserResponse is created and added to the list
            for(User user: users) {
                UserResponse userResponse = new UserResponse(user.getId(), user.getName(), user.getDateBirth(), user.getContact());
                userResponses.add(userResponse);
            }
            return Response.ok(userResponses).build();
        }
}
