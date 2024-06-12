package com.criticalsoftware;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User> {

    // Este método procura um usuário pelo campo 'name'
    public User findByName(String name) {
        return find("name", name).firstResult();
    }

    // Este método recupera todas as entidades de User do banco de dados, ordenadas pelo campo 'name'
    public List<User> findOrderedByName() {
        return listAll(Sort.by("name"));
    }
}