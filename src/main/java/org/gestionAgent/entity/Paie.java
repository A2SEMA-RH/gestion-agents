package org.gestionAgent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Paie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @ManyToOne
    @JoinColumn(name = "agentId")
    private Agent  agent ;
    private Date  date ;
    private int heursTravail ;


}
