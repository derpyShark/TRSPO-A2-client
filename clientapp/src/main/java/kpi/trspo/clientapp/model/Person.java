package kpi.trspo.clientapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private UUID driverId;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private PersonRole role;
}
