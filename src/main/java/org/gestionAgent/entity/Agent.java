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
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private  String nom ;
    private  String prenom ;
    private  String sexe ;
    private  String email;
    @ManyToOne
    @JoinColumn(name = "missionId")
    private Mission mission ;
    @OneToMany(mappedBy = "agent", fetch = FetchType.EAGER)
    private List<Paie> paie ;
    private int numero ;
}
