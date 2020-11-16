package kpi.trspo.clientapp.services;

import kpi.trspo.clientapp.model.MachineryType;
import kpi.trspo.clientapp.model.Person;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class MachineryTypeService {
    RestTemplate restTemplate = new RestTemplate();

    final String URL = "http://localhost:8080/api/machineryTypes";

    public void deleteMachineryType(UUID id)
    {
        String workingURL = URL + "/" + id;
        restTemplate.delete(workingURL);
    }

    public MachineryType addMachineryType(MachineryType addedMachineryType){
        HttpEntity<MachineryType> request = new HttpEntity<>(addedMachineryType);
        return restTemplate.postForObject(URL,request,MachineryType.class);
    }

    public List<MachineryType> getAllMachineryTypes(){
        Optional<MachineryType[]> machineryTypesMaybe = Optional.ofNullable(
                restTemplate.getForObject(URL, MachineryType[].class));
        List<MachineryType> machineryTypes = new ArrayList<>();
        if(machineryTypesMaybe.isPresent()){
            machineryTypes = Arrays.asList(machineryTypesMaybe.get());
        }
        return machineryTypes;
    }

    public MachineryType getMachineryTypeById(UUID machineryTypeId){
        String workingURL = URL + "/" + machineryTypeId;
        return restTemplate.getForObject(workingURL, MachineryType.class);
    }

    public void deleteAllMachineryTypes(){
        List<MachineryType> machineryTypes = getAllMachineryTypes();

        for(MachineryType machineryType : machineryTypes){
            deleteMachineryType(machineryType.getTypeId());
        }
    }

    public void putMachineryType(UUID machineryTypeId, MachineryType machineryTypeObject){
        String workingURL = URL + "/" + machineryTypeId;
        restTemplate.put(workingURL,machineryTypeObject);
    }
}
