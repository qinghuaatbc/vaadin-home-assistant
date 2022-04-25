package com.example.application.Service;

import com.example.application.Domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;


    public List<Person> findAll() {
        return this.personRepository.findAll();
    }

    public Person findById(String id) {
        return this.personRepository.findById(id).get();

    }

    public Person save(Person person) {
        return this.personRepository.save(person);
    }

    public Person update(Person person) {
        return personRepository.save(person);
    }

    public void deleteById(String id) {

        personRepository.deleteById(id);
    }

}
