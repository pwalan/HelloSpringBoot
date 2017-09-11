package com.github.pwalan.mysql.service.impl;


import com.github.pwalan.mysql.dao.PersonRepository;
import com.github.pwalan.mysql.domain.Person;
import com.github.pwalan.mysql.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DemoServiceImpl implements DemoService {
	@Autowired
	PersonRepository personRepository;
	
	@Transactional(rollbackFor={IllegalArgumentException.class}) //指定特定异常时，数据回滚
	public Person savePersonWithRollBack(Person person){
		Person p =personRepository.save(person);

		if(person.getName().equals("汪云飞")){
			throw new IllegalArgumentException("汪云飞已存在，数据将回滚");
		}
		return p;
	}

	@Transactional(noRollbackFor={IllegalArgumentException.class}) //指定特定异常时，数据不回滚
	public Person savePersonWithoutRollBack(Person person){
		Person p =personRepository.save(person);
		
		if(person.getName().equals("汪云飞")){
			throw new IllegalArgumentException("汪云飞虽已存在，数据将不会回滚");
		}
		return p;
	}

	@Override
	@CachePut(value = "people", key = "#person.id")
	public Person save(Person person) {
		Person p = personRepository.save(person);
		System.out.println("为id、key为:"+p.getId()+"数据做了缓存");
		return p;
	}

	@Override
	@CacheEvict(value = "people")
	public void remove(int id){
		System.out.println("删除了id、key为"+id+"的数据缓存");
	}

	@Override
	@Cacheable(value = "people", key = "#person.id")
	public Person findOne(Person person) {
		Person p = personRepository.findOne(person.getId());
		System.out.println("为id、key为:"+p.getId()+"数据做了缓存");
		return p;
	}
}
