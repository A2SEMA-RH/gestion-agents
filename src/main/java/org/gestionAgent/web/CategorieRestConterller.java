package org.gestionAgent.web;

import org.gestionAgent.entity.CategorieMission;
import org.gestionAgent.service.CategorieMissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catogirie")

public class CategorieRestConterller {
    private  final CategorieMissionService categorieMissionService ;

    public CategorieRestConterller(CategorieMissionService categorieMissionService) {
        this.categorieMissionService = categorieMissionService;
    }

    @PostMapping(value ="/ajouter-categoire")
    public CategorieMission addCategorie(@RequestBody CategorieMission categorieMission){
        return categorieMissionService.ajouterCategorieMission(categorieMission);

    }

    @GetMapping(value = "/liste-categories")
    public List<CategorieMission>listCategorie(){
        return categorieMissionService.listeCategoriesMission();
    }


}
