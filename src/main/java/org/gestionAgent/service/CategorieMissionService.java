package org.gestionAgent.service;

import org.gestionAgent.entity.CategorieMission;

import java.util.List;

public interface CategorieMissionService {

    public  CategorieMission ajouterCategorieMission(CategorieMission categorieMission);
    public List<CategorieMission> listeCategoriesMission();
    public CategorieMission midifierCategorieMission(CategorieMission categorieMisssion);
    public  CategorieMission selectCategorieMission(Long categorieMissionId);
    public String supprimerCategorieMission(CategorieMission categorieMisssion);
}
