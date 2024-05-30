package org.gestionAgent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String nomMission ;
    private  String statut ;
    @ManyToOne
    @JoinColumn(name = "categorieID")
    private CategorieMission categorieMisssion ;
    private  String description ;
    @OneToMany(mappedBy = "mission", fetch = FetchType.EAGER)
    private List<Agent> agentsAssigner ;
    private Date dateDebut ;
    private  Date dataFin ;
}
