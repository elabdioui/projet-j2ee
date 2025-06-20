package com.produit.produitbackend;

import entities.Categorie;
import entities.Produit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import repos.CategorieRepository;
import repos.ProduitRepository;
import service.ProduitService;

import java.util.Date;
import java.util.List;

@SpringBootTest
class ProduitBackendApplicationTests {

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private ProduitService produitService;

    @Test
    public void testCreateCategorie() {
        Categorie cat = new Categorie();
        cat.setNomCat("Électronique");
        cat.setDescriptionCat("Produits électroniques");
        categorieRepository.save(cat);
    }

    @Test
    public void testCreateProduit() {
        Produit prod = new Produit("PC Dell", 2200.500, new Date());
        produitRepository.save(prod);
    }

    @Test
    public void testFindProduit() {
        Produit p = produitRepository.findById(1L).get();
        System.out.println(p);
    }

    @Test
    public void testUpdateProduit() {
        Produit p = produitRepository.findById(1L).get();
        p.setPrixProduit(1000.0);
        produitRepository.save(p);
    }

    @Test
    public void testDeleteProduit() {
        produitRepository.deleteById(1L);
    }

    @Test
    public void testListerTousProduits() {
        List<Produit> prods = produitRepository.findAll();
        for (Produit p : prods) {
            System.out.println(p);
        }
    }

    @Test
    public void testFindByNomProduit() {
        List<Produit> prods = produitRepository.findByNomProduit("PC Dell");
        for (Produit p : prods) {
            System.out.println(p);
        }
    }

    @Test
    public void testFindByNomProduitContains() {
        List<Produit> prods = produitRepository.findByNomProduitContains("PC");
        for (Produit p : prods) {
            System.out.println(p);
        }
    }

    @Test
    public void testfindByNomPrix() {
        List<Produit> prods = produitRepository.findByNomPrix("PC", 1000.0);
        for (Produit p : prods) {
            System.out.println(p);
        }
    }

    @Test
    public void testfindByCategorie() {
        Categorie cat = new Categorie();
        cat.setIdCat(1L);
        List<Produit> prods = produitRepository.findByCategorie(cat);
        for (Produit p : prods) {
            System.out.println(p);
        }
    }

    @Test
    public void testfindByCategorieIdCat() {
        List<Produit> prods = produitRepository.findByCategorieIdCat(1L);
        for (Produit p : prods) {
            System.out.println(p);
        }
    }

    @Test
    public void testfindByOrderByNomProduitAsc() {
        List<Produit> prods = produitRepository.findByOrderByNomProduitAsc();
        for (Produit p : prods) {
            System.out.println(p);
        }
    }

    @Test
    public void testTrierProduitsNomsPrix() {
        List<Produit> prods = produitRepository.trierProduitsNomsPrix();
        for (Produit p : prods) {
            System.out.println(p);
        }
    }

    @Test
    public void testProduitService() {
        // Test du service avec DTO
        System.out.println("Test du service ProduitService...");
        var produits = produitService.getAllProduits();
        System.out.println("Nombre de produits : " + produits.size());
    }
}