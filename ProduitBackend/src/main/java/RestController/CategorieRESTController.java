package RestController;

import entities.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CategorieService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategorieRESTController {

    @Autowired
    private CategorieService categorieService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Categorie> getAllCategories() {
        return categorieService.getAllCategories();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Categorie getCategorieById(@PathVariable("id") Long id) {
        return categorieService.getCategorie(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Categorie createCategorie(@RequestBody Categorie categorie) {
        return categorieService.saveCategorie(categorie);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Categorie updateCategorie(@RequestBody Categorie categorie) {
        return categorieService.updateCategorie(categorie);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public void deleteCategorie(@PathVariable("id") Long id) {
        categorieService.deleteCategorieById(id);
    }
}