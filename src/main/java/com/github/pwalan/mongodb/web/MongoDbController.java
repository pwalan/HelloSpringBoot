package com.github.pwalan.mongodb.web;

import com.github.pwalan.mongodb.dao.MongoDBPersonRepository;
import com.github.pwalan.mongodb.domain.Location;
import com.github.pwalan.mongodb.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author AlanP
 * @Date 2017/9/14
 */
@RestController
@RequestMapping("/mongo")
public class MongoDbController {

    @Autowired
    MongoDBPersonRepository mongoDBPersonRepository;

    @RequestMapping("/save")
    public Person save() {
        Person p = new Person("pw", 23);
        Collection<Location> locations = new LinkedHashSet<Location>();
        Location loc1 = new Location("江西", "2010");
        Location loc2 = new Location("北京", "2013");
        Location loc3 = new Location("广州", "2014");
        Location loc4 = new Location("上海", "2015");
        locations.add(loc1);
        locations.add(loc2);
        locations.add(loc3);
        locations.add(loc4);
        p.setLocations(locations);

        return mongoDBPersonRepository.save(p);
    }

    @RequestMapping("/q1")
    public Person q1(String name) {
        return mongoDBPersonRepository.findByName(name);
    }

    @RequestMapping("/q2")
    public List<Person> q2(Integer age) {
        return mongoDBPersonRepository.withQueryFindByAge(age);
    }
}
