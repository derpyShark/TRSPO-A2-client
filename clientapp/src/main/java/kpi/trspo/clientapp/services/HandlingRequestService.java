package kpi.trspo.clientapp.services;

import kpi.trspo.clientapp.model.HandlingRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;


public class HandlingRequestService {
    RestTemplate restTemplate = new RestTemplate();

    final String URL = "http://localhost:8080/api/handlingRequests";

    public void deleteHandlingRequest(UUID id)
    {
        String workingURL = URL + "/" + id;
        restTemplate.delete(workingURL);
    }

    public HandlingRequest addHandlingRequest(HandlingRequest addedHandlingRequest){
        HttpEntity<HandlingRequest> request = new HttpEntity<>(addedHandlingRequest);
        return restTemplate.postForObject(URL,request,HandlingRequest.class);
    }

    public List<HandlingRequest> getAllHandlingRequests(){
        Optional<HandlingRequest[]> handlingRequestsMaybe = Optional.ofNullable(
                restTemplate.getForObject(URL, HandlingRequest[].class));
        List<HandlingRequest> handlingRequests = new ArrayList<>();
        if(handlingRequestsMaybe.isPresent()){
            handlingRequests = Arrays.asList(handlingRequestsMaybe.get());
        }
        return handlingRequests;
    }

    public HandlingRequest getHandlingRequestById(UUID handlingRequestId){
        String workingURL = URL + "/" + handlingRequestId;
        return restTemplate.getForObject(workingURL, HandlingRequest.class);
    }

    public void deleteAllHandlingRequests(){
        List<HandlingRequest> handlingRequests = getAllHandlingRequests();
        for(HandlingRequest handlingRequest : handlingRequests){
            deleteHandlingRequest(handlingRequest.getHandlingRequestId());
        }
    }

    public void putHandlingRequest(UUID handlingRequestId, HandlingRequest handlingRequestObject){
        String workingURL = URL + "/" + handlingRequestId;
        restTemplate.put(workingURL,handlingRequestObject);
    }
}
