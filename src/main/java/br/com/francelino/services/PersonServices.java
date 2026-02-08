package br.com.francelino.services;

import br.com.francelino.exception.ResourceNotFoundException;
import br.com.francelino.model.Person;
import br.com.francelino.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    @Autowired
    private PersonRepository personRepository;

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(Long id){
        logger.info("Inside findById");

        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
    }

    public List<Person> findAll() {
        logger.info("Inside findAll");
        return personRepository.findAll();
    }

    public Person create(Person person) {
        logger.info("Inside create");

        return personRepository.save(person);
    }

    public Person update(Person person) {
        logger.info("Inside update");

        Person entity = personRepository.findById(person.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Person not found"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return personRepository.save(entity);
    }

    public void delete(Long id) {
        logger.info("Inside delete");

        Person entity = personRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Person not found"));

        personRepository.delete(entity);
    }

}
