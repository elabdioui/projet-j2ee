import { Component, OnInit } from '@angular/core';
import { Produit } from '../model/produit.model';
import { ProduitService } from '../services/produit.service';
import { Router } from '@angular/router';
import { Categorie } from '../model/categorie.model';
import { Image } from '../model/image.model';

@Component({
  selector: 'app-add-produit',
  templateUrl: './add-produit.component.html',
  styleUrls: ['./add-produit.component.css']
})
export class AddProduitComponent implements OnInit {
  newProduit = new Produit();
  categories!: Categorie[];
  newIdCat!: number;
  uploadedImage!: File;
  imagePath: any;

  constructor(private produitService: ProduitService, private router: Router) {}

  ngOnInit(): void {
    this.produitService.listeCategories().subscribe(cats => {
      this.categories = cats._embedded.categories;
      console.log(cats);
    });
  }

  onImageUpload(event: any) {
    this.uploadedImage = event.target.files[0];
    var reader = new FileReader();
    reader.readAsDataURL(this.uploadedImage);
    reader.onload = (_event) => {
      this.imagePath = reader.result;
    }
  }

  addProduit() {
    this.newProduit.categorie = this.categories.find(cat => cat.idCat == this.newIdCat)!;

    if (this.uploadedImage) {
      // Upload avec image
      this.produitService
        .uploadImage(this.uploadedImage, this.uploadedImage.name)
        .subscribe((img: Image) => {
          this.newProduit.image = img;
          this.produitService
            .ajouterProduit(this.newProduit)
            .subscribe(() => {
              this.router.navigate(['produits']);
            });
        });
    } else {
      // Upload sans image
      this.produitService
        .ajouterProduit(this.newProduit)
        .subscribe(() => {
          this.router.navigate(['produits']);
        });
    }
  }
}
