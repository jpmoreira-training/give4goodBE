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
            // Validar e buscar o userDonor do banco de dados
            User userDonor = userRepository.findById(new ObjectId(request.getUserDonorId()));
            if (userDonor == null) {
                return Response.status(Status.BAD_REQUEST)
                        .entity("User donor ID does not exist.")
                        .build();
            }

            // Criar o produto com base nos detalhes fornecidos no request
            Product product = new Product(
                    request.getProduct().getName(),
                    request.getProduct().getDescription(),
                    request.getProduct().getPhotoUrl(),
                    request.getProduct().getCategory()

            );

            // Criar o anúncio com o produto e informações do usuário
            Announcement announcement = new Announcement(
                    product,
                    userDonor.getId(), // Utilizar o ID do userDonor existente
                    request.getDate()

            );

            // Persistir o anúncio no repositório
            announcementRepository.persist(announcement);

            // Retornar resposta de criação bem-sucedida
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
            // Em caso de erro, retornar resposta de erro
            return Response.status(Status.BAD_REQUEST)
                    .entity("Error in processing the request: " + e.getMessage())
                    .build();
        }
    }
}
