package com.github.pwalan.mysql.service;


import com.github.pwalan.mysql.domain.Person;

public interface DemoService {
	public Person savePersonWithRollBack(Person person);
	public Person savePersonWithoutRollBack(Person person);

}
