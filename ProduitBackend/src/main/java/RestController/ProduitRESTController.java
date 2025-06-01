package RestController;

import DTO.ProduitDTO;
import entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ProduitService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProduitRESTController {

    @Autowired
    ProduitService produitService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ProduitDTO> getAllProduits() {
        return produitService.getAllProduits();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ProduitDTO getProduitById(@PathVariable("id") Long id) {
        return produitService.getProduit(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ProduitDTO createProduit(@RequestBody ProduitDTO produitDTO) {
        return produitService.saveProduit(produitService.convertDtoToEntity(produitDTO));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ProduitDTO updateProduit(@RequestBody ProduitDTO produitDTO) {
        return produitService.updateProduit(produitService.convertDtoToEntity(produitDTO));
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public void deleteProduit(@PathVariable("id") Long id) {
        produitService.deleteProduitById(id);
    }

    @RequestMapping(value="/prodscat/{idCat}", method = RequestMethod.GET)
    public List<Produit> getProduitsByCatId(@PathVariable("idCat") Long idCat) {
        return produitService.findByCategorieIdCat(idCat);
    }
}
