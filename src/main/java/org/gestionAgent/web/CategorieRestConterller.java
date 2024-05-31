package org.gestionAgent.web;

import org.gestionAgent.entity.CategorieMission;
import org.gestionAgent.service.CategorieMissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(value =  "/selection-categorie/{id}")
    public Optional<CategorieMission>selectCategorieMission(@PathVariable Long id){
        return categorieMissionService.selectCategorieMission(id);
    }


}
