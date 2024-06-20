package com.criticalsoftware;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.ConstraintViolationException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;

@Path("/announcements")
public class AnnouncementResource {

    @Inject
    private AnnouncementRepository announcementRepository;

    @Inject
    private AnnouncementService announcementService;

    @Inject
    private UserRepository userRepository;

    /**
     * Creates a new announcement.
     * @param request The announcement request data.
     * @return The created announcement response.
     */
    @POST
    public Response create(@Valid AnnouncementRequest request) {
        try {
            // Validate and fetch the user donor from the database
            User userDonor = userRepository.findById(new ObjectId(request.getUserDonorId()));
            if (userDonor == null) {
                return Response.status(Status.BAD_REQUEST)
                        .entity("User donor ID does not exist.")
                        .build();
            }

            // Validate all product fields
            if (isProductFieldsEmpty(request)) {
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
                    userDonor.getId()
            );

            // Generate a new String ID for the announcement
            announcement.setId(new ObjectId().toString());

            // Persist the announcement in the repository
            announcementRepository.persist(announcement);

            // Create a response map
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("message", "Announcement created successfully with ID: " + announcement.getId());
            responseMap.put("announcement", new AnnouncementResponse(
                    announcement.getId(),
                    announcement.getProduct(),
                    announcement.getUserDonorId(),
                    announcement.getUserDonneeId(),
                    announcement.getDate()
            ));

            // Return a successful creation response
            return Response.created(new URI("/announcements/" + announcement.getId()))
                    .entity(responseMap)
                    .build();
        } catch (ConstraintViolationException e) {
            String errorMessages = e.getConstraintViolations().stream()
                    .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                    .collect(Collectors.joining(", "));
            return Response.status(Status.BAD_REQUEST)
                    .entity("Validation error: " + errorMessages)
                    .build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity("Error in processing the request: " + e.getMessage())
                    .build();
        }
    }

    private boolean isProductFieldsEmpty(AnnouncementRequest request) {
        return request.getProductName() == null || request.getProductDescription() == null ||
                request.getProductPhotoUrl() == null || request.getProductCategory() == null;
    }

    /**
     * Retrieves announcements by donor ID.
     * @param donorId The donor's ID.
     * @return List of announcements.
     */
    @GET
    @Path("/donor/{donorId}")
    public Response getByDonorId(@PathParam("donorId") String donorId) {
        try {
            List<AnnouncementResponse> announcements = announcementService.getAnnouncementsByDonorId(donorId);
            return Response.ok(announcements).build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity("Error in processing the request: " + e.getMessage())
                    .build();
        }
    }

    /**
     * Retrieves announcements by donee ID.
     * @param doneeId The donee's ID.
     * @return List of announcements.
     */
    @GET
    @Path("/donee/{doneeId}")
    public Response getByDoneeId(@PathParam("doneeId") String doneeId) {
        try {
            List<AnnouncementResponse> announcements = announcementService.getAnnouncementsByDoneeId(doneeId);
            return Response.ok(announcements).build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity("Error in processing the request: " + e.getMessage())
                    .build();
        }
    }

    /**
     * Retrieves announcements by donor and donee IDs.
     * @param donorId The donor's ID.
     * @param doneeId The donee's ID.
     * @return List of announcements.
     */
    @GET
    @Path("/donor/{donorId}/donee/{doneeId}")
    public Response getByDonorAndDoneeId(@PathParam("donorId") String donorId, @PathParam("doneeId") String doneeId) {
        try {
            List<AnnouncementResponse> announcements = announcementService.getAnnouncementsByDonorAndDoneeId(donorId, doneeId);
            return Response.ok(announcements).build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity("Error in processing the request: " + e.getMessage())
                    .build();
        }
    }
}
