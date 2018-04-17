package com.tp.exercises.elastic.spring_elastic_demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tp.exercises.elastic.spring_elastic_demo.model.Users;
import com.tp.exercises.elastic.spring_elastic_demo.repository.UserElasticRepository;

@RestController
@RequestMapping("/rest/search")
public class SearchController {
	
	@Autowired
	UserElasticRepository userElasticRepository;
	
	
	
	@GetMapping(value = "/name/{text}")
	public List<Users> searchName(@PathVariable final String text)
	{
		return userElasticRepository.findByName(text);
		
	}
	
	@GetMapping(value = "/salary/{salary}")
	public List<Users> searchSalary(@PathVariable final Integer salary)
	{
		return userElasticRepository.findBySalary(salary);
		
	}
	
	@GetMapping(value = "/teamname/{text}")
	public List<Users> searchTeamName(@PathVariable final String text)
	{
		return userElasticRepository.findByTeamName(text);
		
	}
	
	@GetMapping(value = "/all")
	public List<Users> searchAll()
	{
		List<Users> userList = new ArrayList<Users>();
		Iterable<Users> users = userElasticRepository.findAll();
		users.forEach(userList :: add);
		return userList;
		
	}
	
	
	
	

}
