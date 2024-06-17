package com.criticalsoftware;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.ConstraintViolationException;
import java.net.URI;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;

@Path("/announcements")
public class AnnouncementResource {

    @Inject
    private AnnouncementRepository announcementRepository;

    @Inject
    private UserRepository userRepository;

    @POST
    public Response create(@Valid AnnouncementRequest request) {
        try {
            // Validate and fetch the userDonor from the database
            User userDonor = userRepository.findById(new ObjectId(request.getUserDonorId()));
            if (userDonor == null) {
                return Response.status(Status.BAD_REQUEST)
                        .entity("User donor ID does not exist.")
                        .build();
            }

            // Ensure all product fields are filled
            if (request.getProductName() == null || request.getProductDescription() == null ||
                    request.getProductPhotoUrl() == null || request.getProductCategory() == null) {
                return Response.status(Status.BAD_REQUEST)
                        .entity("All product fields must be filled.")
                        .build();
            }

            // Create the product based on the provided details
            Product product = new Product(
                    request.getProductName(),
                    request.getProductDescription(),
                    request.getProductPhotoUrl(),
                    request.getProductCategory()
            );

            // Create the announcement with the product and user information
            Announcement announcement = new Announcement(
                    product,
                    userDonor.getId() // Use the existing userDonor ID
            );

            // Persist the announcement in the repository
            announcementRepository.persist(announcement);

            // Return a successful creation response
            return Response.created(new URI("/announcements/" + announcement.getId().toHexString()))
                    .entity("Announcement created successfully with ID: " + announcement.getId().toHexString())
                    .build();
        } catch (ConstraintViolationException e) {
            String errorMessages = e.getConstraintViolations().stream()
                    .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                    .collect(Collectors.joining(", "));
            return Response.status(Status.BAD_REQUEST)
                    .entity("Validation error: " + errorMessages)
                    .build();
        } catch (Exception e) {
            // In case of error, return an error response
            return Response.status(Status.BAD_REQUEST)
                    .entity("Error in processing the request: " + e.getMessage())
                    .build();
        }
    }
}
