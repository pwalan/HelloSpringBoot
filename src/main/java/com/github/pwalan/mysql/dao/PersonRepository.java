package com.github.pwalan.mysql.dao;

import com.github.pwalan.mysql.domain.Person;
import com.github.pwalan.mysql.support.CustomRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PersonRepository extends CustomRepository<Person, Integer> {
	List<Person> findByAddress(String address);
	
	Person findByNameAndAddress(String name, String address);
	
	@Query("select p from Person p where p.name= :name and p.address= :address")
	Person withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);
	
	Person withNameAndAddressNamedQuery(String name, String address);

}
