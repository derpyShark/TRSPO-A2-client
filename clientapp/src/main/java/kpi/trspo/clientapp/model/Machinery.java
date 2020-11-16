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
public class Machinery {

    private UUID machineryId;
    private Person operator;
    private MachineryType machineryType;

}
