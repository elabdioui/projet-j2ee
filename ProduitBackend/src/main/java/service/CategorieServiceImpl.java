package service;

import entities.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repos.CategorieRepository;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public Categorie saveCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie updateCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public void deleteCategorie(Categorie categorie) {
        categorieRepository.delete(categorie);
    }

    @Override
    public void deleteCategorieById(Long id) {
        categorieRepository.deleteById(id);
    }

    @Override
    public Categorie getCategorie(Long id) {
        return categorieRepository.findById(id).orElse(null);
    }

    @Override
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }
}