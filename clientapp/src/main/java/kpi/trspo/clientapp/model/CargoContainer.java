package kpi.trspo.clientapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargoContainer {

    private UUID containerId;
    private float cargoWeight;
    private CargoType cargoType;
    private Machinery machineryWithContainer;

}
