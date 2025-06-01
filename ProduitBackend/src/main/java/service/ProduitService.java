package service;

import DTO.ProduitDTO;
import entities.Categorie;
import entities.Produit;

import java.util.List;

public interface ProduitService {
    ProduitDTO saveProduit(Produit p);
    ProduitDTO updateProduit(Produit p);
    void deleteProduit(Produit p);
    void deleteProduitById(Long id);
    ProduitDTO getProduit(Long id);
    List<ProduitDTO> getAllProduits();

    List<Produit> findByNomProduit(String nom);
    List<Produit> findByNomProduitContains(String nom);
    List<Produit> findByNomPrix(String nom, Double prix);
    List<Produit> findByCategorie(Categorie categorie);
    List<Produit> findByCategorieIdCat(Long id);
    List<Produit> findByOrderByNomProduitAsc();
    List<Produit> trierProduitsNomsPrix();

    ProduitDTO convertEntityToDto(Produit produit);
    Produit convertDtoToEntity(ProduitDTO produitDto);
}