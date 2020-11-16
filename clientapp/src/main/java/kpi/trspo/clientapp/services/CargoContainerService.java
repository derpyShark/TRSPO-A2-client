package kpi.trspo.clientapp.services;


import kpi.trspo.clientapp.model.CargoContainer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class CargoContainerService {

    RestTemplate restTemplate = new RestTemplate();

    final String URL = "http://localhost:8080/api/cargoContainers";

    public void deleteCargoContainer(UUID id)
    {
        String workingURL = URL + "/" + id;
        restTemplate.delete(workingURL);
    }

    public CargoContainer addCargoContainer(CargoContainer addedCargoContainer){
        HttpEntity<CargoContainer> request = new HttpEntity<>(addedCargoContainer);
        return restTemplate.postForObject(URL,request,CargoContainer.class);
    }

    public List<CargoContainer> getAllCargoContainers(){
        Optional<CargoContainer[]> cargoContainersMaybe = Optional.ofNullable(
                restTemplate.getForObject(URL, CargoContainer[].class));
        List<CargoContainer> cargoContainers = new ArrayList<>();
        if(cargoContainersMaybe.isPresent()){
            cargoContainers = Arrays.asList(cargoContainersMaybe.get());
        }
        return cargoContainers;
    }

    public CargoContainer getCargoContainerById(UUID cargoContainerId){
        String workingURL = URL + "/" + cargoContainerId;
        return restTemplate.getForObject(workingURL, CargoContainer.class);
    }

    public void deleteAllCargoContainers(){
        List<CargoContainer> cargoContainers = getAllCargoContainers();

        for(CargoContainer cargoContainer : cargoContainers){
            deleteCargoContainer(cargoContainer.getContainerId());
        }
    }

    public void putCargoContainer(UUID cargoContainerId, CargoContainer cargoContainerObject){
        String workingURL = URL + "/" + cargoContainerId;
        restTemplate.put(workingURL,cargoContainerObject);
    }


}
