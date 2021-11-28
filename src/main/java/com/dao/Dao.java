package com.dao;

import com.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Dao {
    private List<Person> people;
    private static int count = 0;
    {
        people = new ArrayList<>();

        people.add(new Person(++count, "Damir", 15, "damir.@yandex.ru"));
        people.add(new Person(++count, "Tom", 44, "Tomik@mail.com"));
        people.add(new Person(++count, "Matt", 34, "tt@yo.com"));
        people.add(new Person(++count, "Daemon", 22, "cha@yandex.ru"));
    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person)
    {
        person.setId(++count);
        people.add(person);
    }

    public void update(int id, Person newPerson) {
        Person oldPerson = show(id);
        oldPerson.setName(newPerson.getName());
        oldPerson.setAge(newPerson.getAge());
        oldPerson.setEmail(newPerson.getEmail());
    }

    public void delete(int id){
        people.removeIf(person -> person.getId() == id);
    }
}
