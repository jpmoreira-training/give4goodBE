package com.criticalsoftware;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

@Path("/announcements")
public class AnnouncementResource {

    @Inject
    private AnnouncementRepository repository;

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        try {
            // Validate ObjectId
            if (!ObjectId.isValid(id)) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid announcement ID").build();
            }

            ObjectId objectId = new ObjectId(id);
            Announcement announcement = repository.findById(objectId);
            if (announcement == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Announcement not found.").build();
            }

            // Use the repository method to delete by ID
            repository.deleteById(id);

            return Response.noContent().build();
        } catch (Exception e) {
            // Log exception for debugging
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred while deleting the announcement").build();
        }
    }

    // Method to update an announcement by its ID
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, Product product) {
        try {
            // Validate if the provided ID is a valid ObjectId
            if (!ObjectId.isValid(id)) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid announcement ID").build();
            }

            // Convert the string ID to ObjectId
            ObjectId objectId = new ObjectId(id);

            // Retrieve the announcement from the repository using the ObjectId
            Announcement announcement = repository.findById(objectId);
            if (announcement == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Announcement not found").build();
            }

            // Get the existing product from the announcement
            Product existingProduct = announcement.getProduct();

            // Update the existing product's fields with the new product's data
            existingProduct.setCategory(product.getCategory());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPhotoUrl(product.getPhotoUrl());
            existingProduct.setName(product.getName());

            // Set the updated product back to the announcement
            announcement.setProduct(existingProduct);

            // Update the announcement in the repository
            repository.update(announcement);

            // Return the updated product as a response
            return Response.ok(existingProduct).build();
        } catch (Exception e) {
            // Return an internal server error response if any exception occurs
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred while updating the announcement").build();
        }
    }
}
