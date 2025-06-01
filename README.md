# Système de Gestion de Produits

## 📋 Table des Matières
- [Description](#description)
- [Architecture](#architecture)
- [Technologies Utilisées](#technologies-utilisées)
- [Fonctionnalités](#fonctionnalités)
- [Prérequis](#prérequis)
- [Installation et Configuration](#installation-et-configuration)
- [Utilisation](#utilisation)
- [Structure du Projet](#structure-du-projet)
- [API Documentation](#api-documentation)
- [Sécurité](#sécurité)
- [Gestion des Images](#gestion-des-images)
- [Tests](#tests)
- [Contribution](#contribution)

## 📖 Description

Ce projet est un système complet de gestion de produits développé avec **Spring Boot 3** pour le backend et **Angular 18** pour le frontend. Il offre une interface moderne pour gérer un catalogue de produits avec des fonctionnalités avancées comme l'authentification JWT, la gestion des rôles, et l'upload d'images.

## 🏗️ Architecture

### Architecture Globale
```
┌─────────────────┐    HTTP/REST    ┌─────────────────┐
│   Frontend      │ ────────────── │    Backend       │
│   Angular 18    │                 │   Spring Boot 3  │
│   Port: 4200    │                 │   Port: 8080     │
└─────────────────┘                 └─────────────────┘
                                              │
                                              │ JPA/Hibernate
                                              ▼
                                    ┌─────────────────┐
                                    │   Base de       │
                                    │   Données       │
                                    │   MySQL         │
                                    └─────────────────┘
```

### Architecture Backend (Spring Boot)
- **Controller Layer**: REST Controllers pour les API
- **Service Layer**: Logique métier
- **Repository Layer**: Accès aux données avec Spring Data JPA
- **Security Layer**: Authentification JWT et autorisation
- **DTO Layer**: Objets de transfert de données

### Architecture Frontend (Angular)
- **Components**: Composants réutilisables
- **Services**: Services pour les appels API
- **Guards**: Protection des routes
- **Models**: Modèles de données TypeScript

## 🛠️ Technologies Utilisées

### Backend
- **Framework**: Spring Boot 3.5.0
- **Java**: 17
- **Base de données**: MySQL
- **ORM**: Hibernate/JPA
- **Sécurité**: Spring Security + JWT
- **Documentation**: Spring Data REST
- **Utilitaires**: 
  - Lombok (réduction du boilerplate)
  - ModelMapper (mapping DTO/Entity)
  - Auth0 JWT (gestion des tokens)

### Frontend
- **Framework**: Angular 18.2.0
- **UI Framework**: Bootstrap
- **HTTP Client**: Angular HttpClient
- **Authentification**: @auth0/angular-jwt
- **Styles**: CSS3 + Bootstrap
- **TypeScript**: 5.5.2

### Outils de Développement
- **Build Backend**: Maven
- **Build Frontend**: Angular CLI
- **IDE Recommandé**: VS Code, IntelliJ IDEA

## ⚡ Fonctionnalités

### Gestion des Produits
- ✅ Créer, lire, modifier, supprimer des produits (CRUD)
- ✅ Associer des produits à des catégories
- ✅ Recherche par nom, prix, catégorie
- ✅ Tri par différents critères
- ✅ Pagination des résultats

### Gestion des Catégories
- ✅ CRUD complet des catégories
- ✅ Association one-to-many avec les produits

### Authentification et Autorisation
- ✅ Connexion/Déconnexion avec JWT
- ✅ Gestion des rôles (ADMIN, USER)
- ✅ Protection des routes frontend
- ✅ Sécurisation des API backend
- ✅ Tokens avec expiration

### Gestion des Images
- ✅ Upload d'images pour les produits
- ✅ Stockage en base64 (base de données)
- ✅ Stockage sur le système de fichiers
- ✅ Support d'images multiples par produit
- ✅ Prévisualisation avant upload
- ✅ Suppression d'images

### Interface Utilisateur
- ✅ Interface responsive avec Bootstrap
- ✅ Navigation intuitive
- ✅ Feedback utilisateur (messages d'erreur/succès)
- ✅ Protection contre les accès non autorisés

## 📋 Prérequis

- **Java 17** ou supérieur
- **Node.js 18** ou supérieur
- **MySQL 8.0** ou supérieur
- **Maven 3.8** ou supérieur
- **Angular CLI 18** ou supérieur

## 🚀 Installation et Configuration

### 1. Clone du Repository
```bash
git clone https://github.com/elabdioui/projet-j2ee
cd projet-j2ee
```

### 2. Configuration de la Base de Données

#### Créer la base de données MySQL
```sql
CREATE DATABASE spring_DB;
```

#### Configuration Backend (application.properties)
```properties
# Configuration dans ProduitBackend/src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/spring_DB?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
server.servlet.context-path=/produits
```

### 3. Installation du Backend

```bash
cd ProduitBackend
mvn clean install
mvn spring-boot:run
```

Le backend sera accessible sur `http://localhost:8080/produits`

### 4. Installation du Frontend

```bash
cd ProduitFrontend
npm install
npm install bootstrap jquery
ng serve
```

Le frontend sera accessible sur `http://localhost:4200`

### 5. Données Initiales

Le système crée automatiquement :
- **Utilisateur Admin**: username: `admin`, password: `123`
- **Utilisateur Standard**: username: `user1`, password: `123`
- **Rôles**: ADMIN et USER

## 💻 Utilisation

### Accès à l'Application
1. Ouvrir `http://localhost:4200`
2. Se connecter avec un compte (admin/123 ou user1/123)
3. Naviguer dans l'interface

### Fonctionnalités par Rôle

#### Utilisateur ADMIN
- Accès complet à toutes les fonctionnalités
- Création, modification, suppression de produits
- Gestion des images
- Accès aux API d'administration

#### Utilisateur USER
- Visualisation des produits
- Recherche et filtrage
- Pas d'accès aux fonctions de modification

### Navigation
- **Home**: Liste des produits
- **Produits > Ajouter**: Formulaire d'ajout (ADMIN uniquement)
- **Produits > Lister**: Liste complète des produits
- **Login/Logout**: Gestion de la session

## 📁 Structure du Projet

### Backend (ProduitBackend/)
```
src/main/java/
├── com/produit/produitbackend/
│   └── ProduitBackendApplication.java    # Point d'entrée
├── entities/                             # Entités JPA
│   ├── Produit.java
│   ├── Categorie.java
│   ├── Image.java
│   ├── User.java
│   └── Role.java
├── repos/                                # Repositories
│   ├── ProduitRepository.java
│   ├── CategorieRepository.java
│   ├── ImageRepository.java
│   ├── UserRepository.java
│   └── RoleRepository.java
├── service/                              # Services
│   ├── ProduitService.java
│   ├── ProduitServiceImpl.java
│   ├── CategorieService.java
│   ├── ImageService.java
│   └── UserDetailsServiceImpl.java
├── RestController/                       # Controllers REST
│   ├── ProduitRESTController.java
│   ├── CategorieRESTController.java
│   ├── ImageRestController.java
│   └── AuthController.java
├── DTO/                                  # Data Transfer Objects
│   └── ProduitDTO.java
├── security/                             # Configuration sécurité
│   ├── SecurityConfig.java
│   └── JWTAuthenticationFilter.java
└── util/                                 # Utilitaires
    └── JWTUtil.java
```

### Frontend (ProduitFrontend/)
```
src/app/
├── components/
│   ├── produits/                         # Liste des produits
│   ├── add-produit/                      # Ajout de produit
│   ├── update-produit/                   # Modification de produit
│   ├── login/                            # Connexion
│   └── forbidden/                        # Page d'accès interdit
├── services/                             # Services Angular
│   ├── produit.service.ts
│   └── auth.service.ts
├── model/                                # Modèles TypeScript
│   ├── produit.model.ts
│   ├── categorie.model.ts
│   ├── image.model.ts
│   └── user.model.ts
├── guards/                               # Protection des routes
│   └── produit.guard.ts
└── app-routing.module.ts                 # Configuration des routes
```

## 📚 API Documentation

### Endpoints Produits
| Méthode | Endpoint | Description | Autorisation |
|---------|----------|-------------|--------------|
| GET | `/api/all` | Liste tous les produits | USER, ADMIN |
| GET | `/api/{id}` | Produit par ID | USER, ADMIN |
| POST | `/api` | Créer un produit | ADMIN |
| PUT | `/api` | Modifier un produit | ADMIN |
| DELETE | `/api/{id}` | Supprimer un produit | ADMIN |
| GET | `/api/prodscat/{idCat}` | Produits par catégorie | USER, ADMIN |

### Endpoints Images
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/image/upload` | Upload image simple |
| GET | `/api/image/get/info/{id}` | Informations image |
| GET | `/api/image/load/{id}` | Charger image |
| DELETE | `/api/image/delete/{id}` | Supprimer image |
| POST | `/api/image/uplaodImageProd/{idProd}` | Upload multiple images |
| POST | `/api/image/uploadFS/{id}` | Upload vers filesystem |

### Endpoints Authentification
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| POST | `/users/login` | Connexion utilisateur |
| POST | `/users/register` | Inscription utilisateur |
| POST | `/users/refresh-token` | Renouvellement token |

## 🔒 Sécurité

### JWT (JSON Web Tokens)
- **Algorithme**: HMAC256
- **Durée de vie Access Token**: 10 minutes
- **Durée de vie Refresh Token**: 30 minutes
- **Headers requis**: `Authorization: Bearer <token>`

### Protection des Routes
- Routes publiques: `/login`, `/rest/**`
- Routes protégées: toutes les API `/api/**`
- Contrôle d'accès basé sur les rôles (RBAC)

### Validation
- Validation côté client (Angular)
- Validation côté serveur (Spring Boot)
- Sanitisation des entrées utilisateur

## 🖼️ Gestion des Images

### Méthodes de Stockage
1. **Base de données (Base64)**
   - Images stockées directement en BDD
   - Facilité de déploiement
   - Performance moindre pour grandes images

2. **Système de fichiers**
   - Images stockées dans `~/images/`
   - Meilleures performances
   - Gestion des chemins relatifs

### Formats Supportés
- JPEG, PNG, GIF
- Taille maximale: 4MB (configurable)
- Prévisualisation avant upload

## 🧪 Tests

### Backend
```bash
cd ProduitBackend
mvn test
```

### Frontend
```bash
cd ProduitFrontend
ng test
```

### Tests Inclus
- Tests unitaires des services
- Tests d'intégration des repositories
- Tests des contrôleurs REST
- Tests des composants Angular



### Workflow de Développement
1. Fork le repository
2. Créer une branche feature (`git checkout -b feature/nouvelle-fonctionnalite`)
3. Commit les changements (`git commit -am 'Ajout nouvelle fonctionnalité'`)
4. Push vers la branche (`git push origin feature/nouvelle-fonctionnalite`)
5. Créer une Pull Request

### Standards de Code
- Respecter les conventions Java et TypeScript
- Documenter les nouvelles fonctionnalités
- Ajouter des tests pour le nouveau code
- Utiliser des messages de commit descriptifs

## 📝 Changelog

### Version 1.0.0
- ✅ CRUD complet des produits et catégories
- ✅ Authentification JWT
- ✅ Gestion des rôles utilisateur
- ✅ Upload et gestion d'images
- ✅ Interface utilisateur responsive
- ✅ API REST complète
- ✅ Protection des routes et APIs




**Développé avec ❤️ en utilisant Spring Boot et Angular**
