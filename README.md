# HOPE — Projet Java Full Stack

Ce **README** a été rédigé dans le cadre de notre cours de Développement Full Stack Java. **HOPE** est une application de gestion des utilisateurs, illustrant notre maîtrise des technologies **back-end** et **front-end** en Java.

---

## 1. Présentation du projet

- **Nom du projet** : HOPE  
- **Objectif principal** : Développer une application Java complète pour la gestion des utilisateurs, incluant des fonctionnalités d'authentification et de gestion des rôles.  
- **Fonctionnalités clés** :  
  - **Gestion des utilisateurs** : Création, authentification, modification et suppression des comptes utilisateurs.  
  - **Interface utilisateur** : Interface conviviale développée avec JavaFX pour le client lourd et Spring Boot pour les services web.  
  - **Gestion des rôles** : Attribution de rôles spécifiques aux utilisateurs pour contrôler l'accès aux différentes fonctionnalités.  
  - **Persistance des données** : Stockage sécurisé des informations dans une base de données relationnelle.  

---

## 2. Technologies utilisées

- **Langage** : Java (JDK 11)  
- **Frameworks** :  
  - **Back-end** : Spring Boot pour la création de services RESTful.  
  - **Front-end** : JavaFX pour l'interface utilisateur.  
- **Base de données** : MySQL pour l'environnement de production et H2 pour les tests et le développement.  
- **Gestion des dépendances et build** : Maven pour la compilation et la gestion des bibliothèques.  
- **Sécurité** : Spring Security pour la gestion de l'authentification et des autorisations.  
- **ORM** : Hibernate (via JPA) pour la gestion de la persistance des données.  

---

## 3. Installation et exécution

1. **Prérequis** :  
   - Java Development Kit (JDK) 11 installé.  
   - Maven installé.  
   - MySQL installé et configuré.  

2. **Cloner le dépôt** :  
   ```bash
   git clone https://github.com/Gaoul62/Projet-Java-HOPE.git
   cd Projet-Java-HOPE
### Configurer la base de données

1. **Créer une base de données** :  
   Créez une base de données nommée `hope_db` dans MySQL.

2. **Modifier les paramètres de connexion** :  
   Mettez à jour le fichier `src/main/resources/application.properties` avec vos informations de connexion MySQL :

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/hope_db
   spring.datasource.username=VOTRE_NOM_UTILISATEUR
   spring.datasource.password=VOTRE_MOT_DE_PASSE


## 4. Accéder à l’application

- **Pour l'interface web** :  
  Ouvrez votre navigateur et accédez à [http://localhost:8080](http://localhost:8080).

- **Pour l'interface JavaFX** :  
  Exécutez la classe principale `HopeApplication` depuis votre IDE.


## 5. Utilisation

1. **Démarrez l'application** en suivant les instructions ci-dessus.  
2. **Ouvrez le navigateur** et accédez à l'URL indiquée (par défaut : [http://localhost:8080](http://localhost:8080)).  
3. **Créez un compte utilisateur** ou connectez-vous si un système d’authentification est implémenté.  
4. **Naviguez** dans les différentes sections de l'application pour explorer les fonctionnalités (ajout, modification, suppression d’éléments).

## 6. Structure du projet

Le projet est organisé en plusieurs couches pour assurer une séparation claire des responsabilités :

- **Controller** : Gère les requêtes HTTP et redirige vers les services appropriés.  
  - *Exemple* : `UserController` pour les opérations liées aux utilisateurs.

- **Service** : Contient la logique métier.  
  - *Exemple* : `UserService` pour la gestion des utilisateurs.

- **Repository** : Responsable des interactions avec la base de données via JPA.  
  - *Exemple* : `UserRepository` pour les opérations CRUD sur les utilisateurs.

- **Model** : Définit les entités Java mappées à la base de données.  
  - *Exemple* : `User` représentant la table des utilisateurs.

## 7. Endpoints principaux

### **/api/users**

- `GET` : Récupère la liste de tous les utilisateurs.  
- `POST` : Crée un nouvel utilisateur.  

### **/api/users/{id}**

- `GET` : Récupère les détails d'un utilisateur spécifique.  
- `PUT` : Met à jour les informations d'un utilisateur.  
- `DELETE` : Supprime un utilisateur.  

### **/api/auth/login**

- `POST` : Authentifie un utilisateur et renvoie un token JWT.  


## 8. Auteurs

Ce projet a été réalisé par notre équipe composée de :  

- **Issa KANE**  
- **Mahdi ESSETNI**  
- **Ryan PENTE PENTE**  
- **Rayan CAPALDI BENAMOU**
- **Kévin TRINH**
  

