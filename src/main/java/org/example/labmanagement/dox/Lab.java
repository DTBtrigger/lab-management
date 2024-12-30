package org.example.labmanagement.dox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lab {
    @Id
    @CreatedBy
    private String id;
    private String name;
    private int state;
    private int quantity;
    private String description;
    private String manager;
    private int enableEquipment;
}
