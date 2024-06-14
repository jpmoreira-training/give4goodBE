package com.criticalsoftware;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class AnnouncementRepository implements PanacheMongoRepository<Announcement> {

    // Find an announcement by ID
    public Announcement findById(ObjectId id) {
        return find("_id", id).firstResult();
    }

    // List all announcements sorted by date
    public List<Announcement> listAllSortedByDate() {
        return listAll(Sort.by("date").descending());
    }

    // Find announcements by donor ID
    public List<Announcement> findByDonorId(ObjectId donorId) {
        return list("userDonorId", donorId);
    }

    // Find announcements by donee ID
    public List<Announcement> findByDoneeId(ObjectId doneeId) {
        if (doneeId != null) {
            return list("userDoneeId", doneeId);
        }
        return list("userDoneeId is null");
    }

    // Delete an announcement by ID
    public void deleteById(String id) {
        ObjectId objectId; // Declare a variable to hold the ObjectId
        try {
            // Attempt to convert the string ID to an ObjectId
            objectId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            // If the ID is not a valid ObjectId format, throw an IllegalArgumentException with a message
            throw new IllegalArgumentException("Invalid ID format", e);
        }
        // Delete the announcement from the collection using the ObjectId
        delete("_id", objectId);
    }
}
