package org.gestionAgent.service;

import org.gestionAgent.entity.CategorieMission;

import java.util.List;
import java.util.Optional;

public interface CategorieMissionService {

    public  CategorieMission ajouterCategorieMission(CategorieMission categorieMission);
    public List<CategorieMission> listeCategoriesMission();
    public CategorieMission midifierCategorieMission(CategorieMission categorieMisssion);
    public Optional<CategorieMission> selectCategorieMission(Long categorieMissionId);
    public String supprimerCategorieMission(CategorieMission categorieMisssion);
}
