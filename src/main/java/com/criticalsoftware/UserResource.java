package com.criticalsoftware;

import org.bson.types.ObjectId;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/users")
public class UserResource {
    @Inject
    UserRepository userRepository;

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, User user) {
        try {
            // Check if the id is a valid ObjectId and if the user exists
            ObjectId objectId = new ObjectId(id);
            if (userRepository.findById(objectId) == null) {
                return Response.status(Status.NOT_FOUND).entity("User not found").build();
            }

            user.id = objectId;
            userRepository.update(user);
            return Response.ok(user).build();
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
                return Response.status(Status.NOT_FOUND).entity("User not found").build();
            }

            userRepository.delete(user);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST).entity("Invalid user ID").build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("An error occurred while deleting the user").build();
        }
    }
}

