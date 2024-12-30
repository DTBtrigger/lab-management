package org.example.labmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabDTO {
    private String name;
    private int state;
    private int quantity;
    private String description;
    private String managerName;
}
