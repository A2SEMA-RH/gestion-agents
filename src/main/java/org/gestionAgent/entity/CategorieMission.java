package org.gestionAgent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CategorieMission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private  String nom ;
    private  String description;
    @OneToMany(mappedBy = "categorieMisssion", fetch = FetchType.EAGER)
    private List<Mission> mission ;

}
