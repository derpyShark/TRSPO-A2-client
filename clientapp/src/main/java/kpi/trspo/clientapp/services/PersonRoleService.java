package kpi.trspo.clientapp.services;

import kpi.trspo.clientapp.model.CargoContainer;
import kpi.trspo.clientapp.model.Person;
import kpi.trspo.clientapp.model.PersonRole;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import javax.xml.catalog.Catalog;
import java.util.*;

public class PersonRoleService {

    RestTemplate restTemplate = new RestTemplate();

    final String URL = "http://localhost:8080/api/personRoles";

    public void deletePersonRole(UUID id)
    {
        String workingURL = URL + "/" + id;
        restTemplate.delete(workingURL);
    }

    public PersonRole addPersonRole(PersonRole addedPersonRole){
        HttpEntity<PersonRole> request = new HttpEntity<>(addedPersonRole);
        return restTemplate.postForObject(URL,request,PersonRole.class);
    }

    public List<PersonRole> getAllPersonRoles(){
        Optional<PersonRole[]> rolesMaybe = Optional.ofNullable(
                restTemplate.getForObject(URL, PersonRole[].class));
        List<PersonRole> personRoles = new ArrayList<>();
        if(rolesMaybe.isPresent()){
            personRoles = Arrays.asList(rolesMaybe.get());
        }
        return personRoles;
    }

    public PersonRole getPersonRoleById(UUID roleId){
        String workingURL = URL + "/" + roleId;
        return restTemplate.getForObject(workingURL, PersonRole.class);
    }

    public void deleteAllPersonRoles(){
        List<PersonRole> personRoles = getAllPersonRoles();

        for(PersonRole personRole : personRoles){
            deletePersonRole(personRole.getRoleId());
        }
    }

    public void putPersonRole(UUID roleId, PersonRole personRoleObject){
        String workingURL = URL + "/" + roleId;
        restTemplate.put(workingURL,personRoleObject);
    }


}
