package kpi.trspo.clientapp.services;

import kpi.trspo.clientapp.model.Person;
import kpi.trspo.clientapp.model.PersonRole;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class PersonService {

    RestTemplate restTemplate = new RestTemplate();

    final String URL = "http://localhost:8080/api/persons";

    public void deletePerson(UUID id)
    {
        String workingURL = URL + "/" + id;
        restTemplate.delete(workingURL);
    }

    public Person addPerson(Person addedPerson){
        HttpEntity<Person> request = new HttpEntity<>(addedPerson);
        return restTemplate.postForObject(URL,request,Person.class);
    }

    public List<Person> getAllPersons(){
        Optional<Person[]> personsMaybe = Optional.ofNullable(
                restTemplate.getForObject(URL, Person[].class));
        List<Person> persons = new ArrayList<>();
        if(personsMaybe.isPresent()){
            persons = Arrays.asList(personsMaybe.get());
        }
        return persons;
    }

    public Person getPersonById(UUID personId){
        String workingURL = URL + "/" + personId;
        return restTemplate.getForObject(workingURL, Person.class);
    }

    public void deleteAllPersons(){
        List<Person> persons = getAllPersons();

        for(Person person : persons){
            deletePerson(person.getDriverId());
        }
    }

    public void putPerson(UUID personId, Person personObject){
        String workingURL = URL + "/" + personId;
        restTemplate.put(workingURL,personObject);
    }

}
