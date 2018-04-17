package com.tp.exercises.elastic.spring_elastic_demo.model;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "users", type = "users", shards = 1)
public class Users {
	private String name;
	private Long id;
	private String teamName;
	private Integer salary;

	public Users() {
		// no argument constructor
	}

	public Users(String name, Long id, String teamName, Integer salary) {
		this.name = name;
		this.id = id;
		this.teamName = teamName;
		this.salary = salary;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the salary
	 */
	public Integer getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName
	 *            the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}
