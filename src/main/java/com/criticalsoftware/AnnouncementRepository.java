package com.criticalsoftware;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class AnnouncementRepository implements PanacheMongoRepository<Announcement> {

    /**
     * Find an announcement by ID.
     * @param id The ID of the announcement.
     * @return The announcement.
     */
    public Announcement findById(String id) {
        return find("_id", id).firstResult();
    }

    /**
     * List all announcements sorted by creation date (descending).
     * @return List of announcements.
     */
    public List<Announcement> listAllSortedByDate() {
        return listAll(Sort.by("date").descending());
    }

    /**
     * Find announcements by product description.
     * @param description The product description.
     * @return List of announcements.
     */
    public List<Announcement> findByProductDescription(String description) {
        return list("product.description", description);
    }

    /**
     * Find announcements by donor ID.
     * @param donorId The donor's ID.
     * @return List of announcements.
     */
    public List<Announcement> findByDonorId(ObjectId donorId) {
        return list("userDonorId", donorId);
    }

    /**
     * Find announcements by donee ID.
     * @param doneeId The donee's ID.
     * @return List of announcements.
     */
    public List<Announcement> findByDoneeId(ObjectId doneeId) {
        return list("userDonneeId", doneeId);
    }

    /**
     * Find announcements by donor and donee ID.
     * @param donorId The donor's ID.
     * @param doneeId The donee's ID.
     * @return List of announcements.
     */
    public List<Announcement> findByDonorAndDoneeId(ObjectId donorId, ObjectId doneeId) {
        return list("userDonorId = ?1 and userDonneeId = ?2", donorId, doneeId);
    }
}
