package com.dao;

import com.models.Person;
//import jdk.vm.ci.meta.SpeculationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class Dao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Dao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static int count = 0;


    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person", new PersonRowMapper());
    }

    public Person show(int id){

        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new PersonRowMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Person person)
    {
        jdbcTemplate.update("INSERT INTO Person VALUES (1 , ?, ?, ?)", person.getName(), person.getAge(),
                person.getEmail());
    }

    public void update(int id, Person newPerson) {

        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?", newPerson.getName(),
                newPerson.getAge(), newPerson.getEmail(), id);
    }

    public void delete(int id){

        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
