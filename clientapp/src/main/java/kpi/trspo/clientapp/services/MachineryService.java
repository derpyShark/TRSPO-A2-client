package kpi.trspo.clientapp.services;

import kpi.trspo.clientapp.model.Machinery;
import kpi.trspo.clientapp.model.Machinery;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class MachineryService {
    RestTemplate restTemplate = new RestTemplate();

    final String URL = "http://localhost:8080/api/machinery";

    public void deleteMachinery(UUID id)
    {
        String workingURL = URL + "/" + id;
        restTemplate.delete(workingURL);
    }

    public Machinery addMachinery(Machinery addedMachinery){
        HttpEntity<Machinery> request = new HttpEntity<>(addedMachinery);
        return restTemplate.postForObject(URL,request,Machinery.class);
    }

    public List<Machinery> getAllMachinery(){
        Optional<Machinery[]> machineryMaybe = Optional.ofNullable(
                restTemplate.getForObject(URL, Machinery[].class));
        List<Machinery> machinery = new ArrayList<>();
        if(machineryMaybe.isPresent()){
            machinery = Arrays.asList(machineryMaybe.get());
        }
        return machinery;
    }

    public Machinery getMachineryById(UUID machineryId){
        String workingURL = URL + "/" + machineryId;
        return restTemplate.getForObject(workingURL, Machinery.class);
    }

    public void deleteAllMachinery(){
        List<Machinery> machinery = getAllMachinery();

        for(Machinery machineryObject : machinery){
            deleteMachinery(machineryObject.getMachineryId());
        }
    }

    public void putMachinery(UUID machineryId, Machinery machineryObject){
        String workingURL = URL + "/" + machineryId;
        restTemplate.put(workingURL,machineryObject);
    }
}
