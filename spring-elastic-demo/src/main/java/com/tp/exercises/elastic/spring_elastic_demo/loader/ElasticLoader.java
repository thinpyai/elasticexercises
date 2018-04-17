package com.tp.exercises.elastic.spring_elastic_demo.loader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tp.exercises.elastic.spring_elastic_demo.model.Users;
import com.tp.exercises.elastic.spring_elastic_demo.repository.UserElasticRepository;

@Component
public class ElasticLoader {

	@Autowired
	ElasticsearchOperations operations;

	@Autowired
	UserElasticRepository userElasticRpository;

	@PostConstruct
	@Transactional
	public void loadAll() {
		operations.putMapping(Users.class);
		System.out.println("Loading Data");
		userElasticRpository.saveAll(getData());
		System.out.println("Loading Completed");

	}

	private List<Users> getData() {
		List<Users> userList = new ArrayList<>();

		List<String> nameList = new ArrayList<>(Arrays.asList("Mr.A, Ms.B, Mrs.C, Mr.D, Ms.E"));
		List<String> teamNameList = new ArrayList<>(Arrays.asList("Accounting, Finance, Development, Development, Development"));
		List<Integer> salaryList = new ArrayList<>(Arrays.asList(800000, 800000, 500000, 1500000, 1300000));

		for (Integer count = 1; count <= 5; count++) {
			
			Users user = new Users(nameList.get(count),count.longValue(), teamNameList.get(count), salaryList.get(count));
			userList.add(user);
		}

		return userList;
	}

}
