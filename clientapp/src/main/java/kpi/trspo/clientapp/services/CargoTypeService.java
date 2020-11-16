package kpi.trspo.clientapp.services;

import kpi.trspo.clientapp.model.CargoType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class CargoTypeService {
    RestTemplate restTemplate = new RestTemplate();

    final String URL = "http://localhost:8080/api/cargoTypes";

    public void deleteCargoType(UUID id)
    {
        String workingURL = URL + "/" + id;
        restTemplate.delete(workingURL);
    }

    public CargoType addCargoType(CargoType addedCargoType){
        HttpEntity<CargoType> request = new HttpEntity<>(addedCargoType);
        return restTemplate.postForObject(URL,request,CargoType.class);
    }

    public List<CargoType> getAllCargoTypes(){
        Optional<CargoType[]> cargoTypesMaybe = Optional.ofNullable(
                restTemplate.getForObject(URL, CargoType[].class));
        List<CargoType> cargoTypes = new ArrayList<>();
        if(cargoTypesMaybe.isPresent()){
            cargoTypes = Arrays.asList(cargoTypesMaybe.get());
        }
        return cargoTypes;
    }

    public CargoType getCargoTypeById(UUID cargoTypeId){
        String workingURL = URL + "/" + cargoTypeId;
        return restTemplate.getForObject(workingURL, CargoType.class);
    }

    public void deleteAllCargoTypes(){
        List<CargoType> cargoTypes = getAllCargoTypes();
        for(CargoType cargoType : cargoTypes){
            deleteCargoType(cargoType.getCargoTypeId());
        }
    }

    public void putCargoType(UUID cargoTypeId, CargoType cargoTypeObject){
        String workingURL = URL + "/" + cargoTypeId;
        restTemplate.put(workingURL,cargoTypeObject);
    }
}
