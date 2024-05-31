package org.gestionAgent.exceptionds;

public class CategorieMissionNotFindException extends RuntimeException{
    public CategorieMissionNotFindException(Long categorieMissionId){
        super("categorie mission not found with ID"+ categorieMissionId);
    }
}
