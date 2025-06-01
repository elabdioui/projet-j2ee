package service;

import entities.Categorie;
import java.util.List;

public interface CategorieService {
    Categorie saveCategorie(Categorie categorie);
    Categorie updateCategorie(Categorie categorie);
    void deleteCategorie(Categorie categorie);
    void deleteCategorieById(Long id);
    Categorie getCategorie(Long id);
    List<Categorie> getAllCategories();
}