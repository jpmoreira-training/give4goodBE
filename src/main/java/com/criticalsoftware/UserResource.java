package com.criticalsoftware;

import org.bson.types.ObjectId;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import jakarta.ws.rs.core.Response.Status;

@Path("/users")

public class UserResource {

    @Inject
    private UserRepository repository;
    UserRepository userRepository;

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


    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, Contact contact) {
        try {
            // Check if the id is a valid ObjectId and if the user exists
            ObjectId objectId = new ObjectId(id);
            User userFromDb = userRepository.findById(objectId);
            if (userFromDb == null) {
                return Response.status(Status.NOT_FOUND).entity("User not found").build();
            }
            userFromDb.setContact(contact); // Updates the contact information
            userRepository.update(userFromDb); // Saves the updated user in the database
            return Response.ok(contact).build(); // Returns the updated user information
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST).entity("Invalid user ID").build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("An error occurred while updating the user").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            User user = userRepository.findById(objectId);
            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("User not found.").build();
                return Response.status(Status.NOT_FOUND).entity("User not found").build();
            }
            UserResponse userResponse = new UserResponse(user.getId(), user.getName(), user.getDateBirth(), user.getContact());
            return Response.ok(userResponse).build();
            userRepository.delete(user);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST).entity("Invalid user ID").build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("An error occurred while deleting the user").build();
        }

}

