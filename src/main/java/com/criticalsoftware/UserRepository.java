package com.criticalsoftware;
import java.util.List;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User> {

    public User findByName(String name) {
        return find("name", name).firstResult();
    }

    public List<User> findOrderedName() {
        return listAll(Sort.by("name"));
    }

}
