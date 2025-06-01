package service;

import DTO.ProduitDTO;
import entities.Categorie;
import entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repos.ImageRepository;
import repos.ProduitRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ProduitDTO saveProduit(Produit p) {
        return convertEntityToDto(produitRepository.save(p));
    }

    @Override
    public ProduitDTO updateProduit(Produit p) {
        Long oldProdImageId = null;
        if (this.getProduit(p.getIdProduit()).getImage() != null) {
            oldProdImageId = this.getProduit(p.getIdProduit()).getImage().getIdImage();
        }

        Long newProdImageId = null;
        if (p.getImage() != null) {
            newProdImageId = p.getImage().getIdImage();
        }

        Produit prodUpdated = produitRepository.save(p);

        if (oldProdImageId != null && newProdImageId != null && !oldProdImageId.equals(newProdImageId)) {
            imageRepository.deleteById(oldProdImageId);
        }

        return convertEntityToDto(prodUpdated);
    }

    @Override
    public void deleteProduit(Produit p) {
        produitRepository.delete(p);
    }

    @Override
    public void deleteProduitById(Long id) {
        Produit p = produitRepository.findById(id).orElse(null);
        if (p != null && p.getImagePath() != null) {
            try {
                Files.delete(Paths.get(System.getProperty("user.home") + "/images/" + p.getImagePath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        produitRepository.deleteById(id);
    }

    @Override
    public ProduitDTO getProduit(Long id) {
        return convertEntityToDto(produitRepository.findById(id).get());
    }

    @Override
    public List<ProduitDTO> getAllProduits() {
        return produitRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Produit> findByNomProduit(String nom) {
        return produitRepository.findByNomProduit(nom);
    }

    @Override
    public List<Produit> findByNomProduitContains(String nom) {
        return produitRepository.findByNomProduitContains(nom);
    }

    @Override
    public List<Produit> findByNomPrix(String nom, Double prix) {
        return produitRepository.findByNomPrix(nom, prix);
    }

    @Override
    public List<Produit> findByCategorie(Categorie categorie) {
        return produitRepository.findByCategorie(categorie);
    }

    @Override
    public List<Produit> findByCategorieIdCat(Long id) {
        return produitRepository.findByCategorieIdCat(id);
    }

    @Override
    public List<Produit> findByOrderByNomProduitAsc() {
        return produitRepository.findByOrderByNomProduitAsc();
    }

    @Override
    public List<Produit> trierProduitsNomsPrix() {
        return produitRepository.trierProduitsNomsPrix();
    }

    @Override
    public ProduitDTO convertEntityToDto(Produit produit) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ProduitDTO produitDTO = modelMapper.map(produit, ProduitDTO.class);

        if (produit.getCategorie() != null) {
            produitDTO.setNomCat(produit.getCategorie().getNomCat());
        }

        return produitDTO;
    }

    @Override
    public Produit convertDtoToEntity(ProduitDTO produitDto) {
        Produit produit = modelMapper.map(produitDto, Produit.class);
        return produit;
    }
}