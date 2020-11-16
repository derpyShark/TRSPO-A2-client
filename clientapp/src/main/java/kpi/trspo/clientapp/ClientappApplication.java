package kpi.trspo.clientapp;

import kpi.trspo.clientapp.model.*;
import kpi.trspo.clientapp.services.*;
import kpi.trspo.clientapp.testingutil.Name;
import kpi.trspo.clientapp.testingutil.RandomUtility;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@SpringBootApplication
public class ClientappApplication implements CommandLineRunner {

    PersonRoleService personRoleService = new PersonRoleService();
    CargoTypeService cargoTypeService = new CargoTypeService();
    HandlingRequestService handlingRequestService = new HandlingRequestService();
    MachineryService machineryService = new MachineryService();
    MachineryTypeService machineryTypeService = new MachineryTypeService();
    PersonService personService = new PersonService();
    CargoContainerService cargoContainerService = new CargoContainerService();
    RandomUtility randomUtility = new RandomUtility();

    public static void main(String[] args) {
        SpringApplication.run(ClientappApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception
    {
        deleteAllData();
        addTypes();
        addMachinery();
        addContainersAndHandlingRequests();
    }

    private void deleteAllData(){
        handlingRequestService.deleteAllHandlingRequests();
        cargoContainerService.deleteAllCargoContainers();
        machineryService.deleteAllMachinery();
        personService.deleteAllPersons();
        personRoleService.deleteAllPersonRoles();
        machineryTypeService.deleteAllMachineryTypes();
        cargoTypeService.deleteAllCargoTypes();
    }
    private PersonRole captain;
    private PersonRole driver;
    private PersonRole operator;
    private MachineryType crane;
    private MachineryType ship;
    private MachineryType truck;
    private CargoType provisions;
    private CargoType parts;
    private CargoType toys;
    private CargoType medicine;
    private CargoType furniture;

    private void addTypes(){
        captain = personRoleService.addPersonRole(PersonRole.builder().roleName("captain").build());
        driver = personRoleService.addPersonRole(PersonRole.builder().roleName("driver").build());
        operator = personRoleService.addPersonRole(PersonRole.builder().roleName("operator").build());
        crane = machineryTypeService.addMachineryType(MachineryType.builder().machineryTypeName("crane").build());
        ship = machineryTypeService.addMachineryType(MachineryType.builder().machineryTypeName("ship").build());
        truck = machineryTypeService.addMachineryType(MachineryType.builder().machineryTypeName("truck").build());
        provisions = cargoTypeService.addCargoType(CargoType.builder().cargoTypeName("provisions").build());
        parts = cargoTypeService.addCargoType(CargoType.builder().cargoTypeName("parts").build());
        toys = cargoTypeService.addCargoType(CargoType.builder().cargoTypeName("toys").build());
        medicine = cargoTypeService.addCargoType(CargoType.builder().cargoTypeName("medicine").build());
        furniture = cargoTypeService.addCargoType(CargoType.builder().cargoTypeName("furniture").build());

    }

    private void addMachinery(){
        Calendar cal = Calendar.getInstance();
        cal.set(1960, 1, 1);
        Date early = cal.getTime();
        cal.set(2000, 1, 1);
        Date late = cal.getTime();
        int numberOfTrucks = 16;
        int numberOfShips = 1;
        int numberOfCranes = 1;
        Person person = null;

        for(int i = 0; i < numberOfTrucks;i++){
            person = personService.addPerson(Person.builder().name(RandomUtility.getRandomName()).
                    surname(RandomUtility.getRandomSurname()).
                    dateOfBirth(RandomUtility.getRandomDate(early,late)).
                    role(driver).build());
            machineryService.addMachinery(Machinery.builder().operator(person).machineryType(truck).build());
        }

        for(int i = 0; i < numberOfShips;i++){
            person = personService.addPerson(Person.builder().name(RandomUtility.getRandomName()).
                    surname(RandomUtility.getRandomSurname()).
                    dateOfBirth(RandomUtility.getRandomDate(early,late)).
                    role(captain).build());
            machineryService.addMachinery(Machinery.builder().operator(person).machineryType(ship).build());
        }

        for(int i = 0; i < numberOfCranes; i++){
            person = personService.addPerson(Person.builder().name(RandomUtility.getRandomName()).
                    surname(RandomUtility.getRandomSurname()).
                    dateOfBirth(RandomUtility.getRandomDate(early,late)).
                    role(operator).build());
            machineryService.addMachinery(Machinery.builder().operator(person).machineryType(crane).build());
        }
    }

    private void addContainersAndHandlingRequests(){
        List<Machinery> machineryList = machineryService.getAllMachinery();

        List<Machinery> trucksToLoad = new ArrayList<>();
        List<Machinery> trucksToUnload = new ArrayList<>();
        Machinery ship = null;
        int counter = 0;
        for(Machinery machinery: machineryList){
            switch (machinery.getMachineryType().getMachineryTypeName()){
                case("ship"):
                    ship = machinery;
                    break;
                case("truck"):
                    if(counter%2 == 0){
                        trucksToLoad.add(machinery);
                    }
                    else{
                        trucksToUnload.add(machinery);
                    }
                    counter++;
                    break;
            }
        }

        CargoContainer container = null;
        CargoType[] cargoTypes = {provisions,parts,toys,medicine,furniture};
        for(int i = 0 ; i < 2;i++){
            container = cargoContainerService.addCargoContainer(
                    CargoContainer.builder().cargoWeight(RandomUtility.getRandomWeight(3000,20000)).
                            cargoType(cargoTypes[RandomUtility.getRandomInt(cargoTypes.length)]).
                            machineryWithContainer(ship).build()
            );
        }
        HandlingRequest handlingRequest = null;
        for(Machinery machinery : trucksToUnload){
            container = cargoContainerService.addCargoContainer(
                    CargoContainer.builder().cargoWeight(RandomUtility.getRandomWeight(3000,20000)).
                            cargoType(cargoTypes[RandomUtility.getRandomInt(cargoTypes.length)]).
                            machineryWithContainer(machinery).build());
            handlingRequestService.addHandlingRequest(
                    HandlingRequest.builder().containerToHandle(container)
                    .giverMachinery(machinery)
                    .receiverMachinery(ship)
                    .build()
            );
        }
        for(Machinery machinery : trucksToLoad){
            container = cargoContainerService.addCargoContainer(
                    CargoContainer.builder().cargoWeight(RandomUtility.getRandomWeight(3000,20000)).
                            cargoType(cargoTypes[RandomUtility.getRandomInt(cargoTypes.length)]).
                            machineryWithContainer(ship).build());
            handlingRequestService.addHandlingRequest(
                    HandlingRequest.builder().containerToHandle(container)
                            .giverMachinery(ship)
                            .receiverMachinery(machinery)
                            .build()
            );
        }

    }

}
