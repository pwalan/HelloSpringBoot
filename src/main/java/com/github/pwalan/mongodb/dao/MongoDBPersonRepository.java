package com.github.pwalan.mongodb.dao;

import com.github.pwalan.mongodb.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author AlanP
 * @Date 2017/9/14
 */
public interface MongoDBPersonRepository extends MongoRepository<Person, String> {

    Person findByName(String name);

    @Query("{'age': ?0}")
    List<Person> withQueryFindByAge(Integer age);

}
