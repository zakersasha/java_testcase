package com.usercrud.user.repo;

import com.usercrud.user.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
