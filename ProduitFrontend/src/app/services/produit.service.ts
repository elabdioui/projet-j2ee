import { Injectable } from '@angular/core';
import { Produit } from '../model/produit.model';
import { Categorie } from '../model/categorie.model';
import { Image } from '../model/image.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ProduitService {
  apiURL: string = 'http://localhost:8080/produits/api';
  apiURLCat: string = 'http://localhost:8080/produits/cat';

  constructor(private http: HttpClient, private authService: AuthService) {}

  listeProduit(): Observable<Produit[]> {
    let jwt = this.authService.getToken();
    jwt = "Bearer " + jwt;
    let httpHeaders = new HttpHeaders({"Authorization": jwt});
    return this.http.get<Produit[]>(this.apiURL + "/all", {headers: httpHeaders});
  }

  ajouterProduit(prod: Produit): Observable<Produit> {
    let jwt = this.authService.getToken();
    jwt = "Bearer " + jwt;
    let httpHeaders = new HttpHeaders({"Authorization": jwt});
    return this.http.post<Produit>(this.apiURL, prod, {headers: httpHeaders});
  }

  supprimerProduit(id: number) {
    const url = `${this.apiURL}/${id}`;
    let jwt = this.authService.getToken();
    jwt = "Bearer " + jwt;
    let httpHeaders = new HttpHeaders({"Authorization": jwt});
    return this.http.delete(url, {headers: httpHeaders});
  }

  consulterProduit(id: number): Observable<Produit> {
    const url = `${this.apiURL}/${id}`;
    let jwt = this.authService.getToken();
    jwt = "Bearer " + jwt;
    let httpHeaders = new HttpHeaders({"Authorization": jwt});
    return this.http.get<Produit>(url, {headers: httpHeaders});
  }

  updateProduit(prod: Produit): Observable<Produit> {
    let jwt = this.authService.getToken();
    jwt = "Bearer " + jwt;
    let httpHeaders = new HttpHeaders({"Authorization": jwt});
    return this.http.put<Produit>(this.apiURL, prod, {headers: httpHeaders});
  }

  listeCategories(): Observable<any> {
    return this.http.get<any>(this.apiURLCat);
  }

  // Méthodes pour la gestion des images
  uploadImage(file: File, filename: string): Observable<Image> {
    const imageFormData = new FormData();
    imageFormData.append('image', file, filename);
    const url = `${this.apiURL}/image/upload`;
    return this.http.post<Image>(url, imageFormData);
  }

  loadImage(id: number): Observable<Image> {
    const url = `${this.apiURL}/image/get/info/${id}`;
    return this.http.get<Image>(url);
  }

  uploadImageProd(file: File, filename: string, idProd: number): Observable<any> {
    const imageFormData = new FormData();
    imageFormData.append('image', file, filename);
    const url = `${this.apiURL}/image/uplaodImageProd/${idProd}`;
    return this.http.post(url, imageFormData);
  }

  supprimerImage(id: number) {
    const url = `${this.apiURL}/image/delete/${id}`;
    return this.http.delete(url, httpOptions);
  }

  uploadImageFS(file: File, filename: string, idProd: number): Observable<any> {
    const imageFormData = new FormData();
    imageFormData.append('image', file, filename);
    const url = `${this.apiURL}/image/uploadFS/${idProd}`;
    return this.http.post(url, imageFormData);
  }

  trierProduits() {
    // Implémentation du tri côté client si nécessaire
  }
}
