package com.criticalsoftware;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;
import java.util.List;

@ApplicationScoped
public class AnnouncementRepository implements PanacheMongoRepository<Announcement> {

    // Encontra um anúncio pelo ID
    public Announcement findById(ObjectId id) {
        return find("_id", id).firstResult();
    }

    // Lista todos os anúncios ordenados pela data de criação (descendente)
    public List<Announcement> listAllSortedByDate() {
        return listAll(Sort.by("date").descending());
    }

    // Encontra anúncios por descrição de produto
    public List<Announcement> findByProductDescription(String description) {
        return list("product.description", description);
    }

    // Encontra anúncios pelo ID do doador
    public List<Announcement> findByDonorId(ObjectId donorId) {
        return list("userDonorId", donorId);
    }

    // Encontra anúncios pelo ID do beneficiário
    public List<Announcement> findByDoneeId(ObjectId doneeId) {
        if (doneeId != null) {
            return list("userDoneeId", doneeId);
        }
        return list("userDoneeId is null");
    }
}