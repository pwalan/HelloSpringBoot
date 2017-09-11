package com.github.pwalan.mysql.service;


import com.github.pwalan.mysql.domain.Person;

public interface DemoService {
	public Person savePersonWithRollBack(Person person);
	public Person savePersonWithoutRollBack(Person person);
	public Person save(Person person);
	public void remove(int id);
	public Person findOne(Person person);
}
