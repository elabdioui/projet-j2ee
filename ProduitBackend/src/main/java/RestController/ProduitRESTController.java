package RestController;

import DTO.ProduitDTO;
import entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ProduitService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProduitRESTController {

    @Autowired
    ProduitService produitService;

    @GetMapping
    public List<ProduitDTO> getAllProduits() {
        return produitService.getAllProduits();
    }

    @GetMapping("/{id}")
    public ProduitDTO getProduitById(@PathVariable("id") Long id) {
        return produitService.getProduit(id);
    }

    @PostMapping
    public ProduitDTO createProduit(@RequestBody ProduitDTO produitDTO) {
        return produitService.saveProduit(produitService.convertDtoToEntity(produitDTO));
    }

    @PutMapping
    public ProduitDTO updateProduit(@RequestBody ProduitDTO produitDTO) {
        return produitService.updateProduit(produitService.convertDtoToEntity(produitDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable("id") Long id) {
        produitService.deleteProduitById(id);
    }

    @GetMapping("/prodscat/{idCat}")
    public List<Produit> getProduitsByCatId(@PathVariable("idCat") Long idCat) {
        return produitService.findByCategorieIdCat(idCat);
    }
}