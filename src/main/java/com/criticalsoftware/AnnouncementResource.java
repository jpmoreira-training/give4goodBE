package com.criticalsoftware;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

@Path("/announcements")
public class AnnouncementResource {

    @Inject
    private AnnouncementRepository repository;

    @Inject
    private UserRepository userRepository;

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {

        // Check if the provided ID is valid
        if (!id.matches("[a-fA-F0-9]{24}")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid ID format").build();
        }
        // Find the announcement by ID
        Announcement announcement = repository.findById(id);
        if (announcement == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Announcement not found.").build();
        }

        // Use the repository method to delete by ID
        repository.deleteById(id);

        // Check if the announcement still exists
        announcement = repository.findById(id);
        if (announcement != null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred while deleting the announcement").build();
        }
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, AnnouncementRequest announcementRequest) {
        try {
            Announcement announcement = repository.findById(id);
            if (!id.matches("[a-fA-F0-9]{24}")) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid ID format").build();
            }

            if (announcement == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Announcement not found").build();
            }

            // Check if the product details are present and not just spaces in the request
            if (announcementRequest.getProductName() == null || announcementRequest.getProductName().trim().isEmpty() ||
                    announcementRequest.getProductDescription() == null || announcementRequest.getProductDescription().trim().isEmpty() ||
                    announcementRequest.getProductPhotoUrl() == null || announcementRequest.getProductPhotoUrl().trim().isEmpty() ||
                    announcementRequest.getProductCategory() == null || announcementRequest.getProductCategory().trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Product details are missing or empty").build();
            }

            Product newProduct = new Product(announcementRequest.getId(), announcementRequest.getProductDescription(), announcementRequest.getProductPhotoUrl(), announcementRequest.getProductCategory(), announcementRequest.getProductName());
            announcement.setProduct(newProduct);

            // Update the announcement in the repository
            repository.persistOrUpdate(announcement);

            return Response.ok(announcement).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred while updating the announcement").build();
        }
    }

    @PUT
    @Path("/{announcementId}/userDonee/{userId}")
        public Response updateUserDonee(@PathParam("announcementId") String announcementId, @PathParam("userId") String userId) {
        try {

                // Check if the announcementId is valid
                Announcement announcement = repository.findById(announcementId);
                if (!announcementId.matches("[a-fA-F0-9]{24}")) {
                    return Response.status(Response.Status.BAD_REQUEST).entity("Invalid ID format").build();
                }

                // Find the announcement by ID
                if (announcement == null) {
                    return Response.status(Response.Status.NOT_FOUND).entity("Announcement not found").build();
                }

                // Check if the userDoneeId is valid
                User userDoneeId = userRepository.findById(userId);
                if (!userId.matches("[a-fA-F0-9]{24}")) {
                    return Response.status(Response.Status.BAD_REQUEST).entity("Invalid ID format").build();
                }

                // Find the user by ID
                if (userDoneeId == null) {
                    return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
                }

                // Check if userDonorId and userDoneeId are not the same
            if (announcement.getUserDonorId().equals(new ObjectId(userId))) {
                return Response.status(Response.Status.BAD_REQUEST).entity("The doner Id and donee Id cannot be the same!").build();
            }

                // Set the userDoneeId field of the announcement
                announcement.setUserDoneeId(userDoneeId.getId());

                // Update the announcement in the repository
                repository.persistOrUpdate(announcement);

                return Response.ok(announcement).build();
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred while updating the userDonee").build();
        }
    }
}