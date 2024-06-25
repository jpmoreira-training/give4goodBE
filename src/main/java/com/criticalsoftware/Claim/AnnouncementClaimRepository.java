package com.criticalsoftware.Claim;

import com.criticalsoftware.giveaway.Announcement;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.bson.types.ObjectId;

@ApplicationScoped
public class AnnouncementClaimRepository implements PanacheMongoRepositoryBase<Announcement, ObjectId> {

    public Announcement findById(String id) {
        return find("_id", id).firstResult();
    }


    @Transactional
    public void undoClaim(String id) {
        Announcement announcement = findById(id);
        if (announcement != null && announcement.getUserDoneeId() != null) {
            announcement.setUserDoneeId(null);
        }
        persistOrUpdate(announcement);
    }


}
