package com.tp.exercises.elastic.spring_elastic_demo.repository;
import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.tp.exercises.elastic.spring_elastic_demo.model.Users;

@Repository
public interface UserElasticRepository extends ElasticsearchRepository<Users, Long>{
	
	List<Users> findByName(String text);

	List<Users> findByTeamName(String text);

	List<Users> findBySalary(Integer salary);

}
