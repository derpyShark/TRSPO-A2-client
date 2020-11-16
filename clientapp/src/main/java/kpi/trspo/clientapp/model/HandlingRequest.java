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
public class HandlingRequest {
    private UUID handlingRequestId;
    private CargoContainer containerToHandle;
    private Machinery giverMachinery;
    private Machinery receiverMachinery;

}
