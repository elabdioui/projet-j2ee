import { Component, OnInit } from '@angular/core';
import { Produit } from '../model/produit.model';
import { ProduitService } from '../services/produit.service';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { Image } from '../model/image.model';

@Component({
  selector: 'app-produits',
  templateUrl: './produits.component.html',
  styleUrls: ['./produits.component.css']
})
export class ProduitsComponent implements OnInit {
  produits: Produit[] = [];
  apiurl: string = 'http://localhost:8080/produits/api';

  constructor(
    private produitService: ProduitService,
    public authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.chargerProduits();
  }

  chargerProduits() {
    this.produitService.listeProduit().subscribe(prods => {
      console.log(prods);
      this.produits = prods;
      this.produits.forEach((prod) => {
        if (prod.image) {
          this.produitService
            .loadImage(prod.image.idImage)
            .subscribe((img: Image) => {
              prod.imageStr = 'data:' + img.type + ';base64,' + img.image;
            });
        } else if (prod.images && prod.images.length > 0) {
          // Afficher la première image si plusieurs images
          prod.imageStr = 'data:' + prod.images[0].type + ';base64,' + prod.images[0].image;
        }
      });
    });
  }

  supprimerProduit(p: Produit) {
    let conf = confirm("Etes-vous sûr ?");
    if (conf)
      this.produitService.supprimerProduit(p.idProduit).subscribe(() => {
        console.log("produit supprimé");
        this.chargerProduits();
      });
  }
}
