package org.gestionAgent.service.impl;

import org.gestionAgent.dao.CategorieMissionDao;
import org.gestionAgent.entity.CategorieMission;
import org.gestionAgent.service.CategorieMissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieMissionImpl implements CategorieMissionService {
    private final CategorieMissionDao categorieMissionDao ;

    public CategorieMissionImpl(CategorieMissionDao categorieMissionDao) {
        this.categorieMissionDao = categorieMissionDao;
    }

    @Override
    public CategorieMission ajouterCategorieMission(CategorieMission categorieMission) {
        return categorieMissionDao.save(categorieMission) ;
    }

    @Override
    public List<CategorieMission> listeCategoriesMission() {
        return categorieMissionDao.findAll() ;

    }

    @Override
    public CategorieMission midifierCategorieMission(CategorieMission categorieMisssion) {
        return null;
    }

    @Override
    public CategorieMission selectCategorieMission(Long categorieMissionId) {

        return  categorieMissionDao.findById(categorieMissionId).orElse(null);
    }

    @Override
    public String supprimerCategorieMission(CategorieMission categorieMisssion) {
        return null;
    }
}
