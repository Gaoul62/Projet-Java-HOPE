# HOPE — Projet Java Full Stack

Ce **README** a été rédigé dans le cadre de notre cours de Développement Full Stack Java. **HOPE** est une application qui démontre notre compréhension des principes de développement **back-end** et **front-end** en Java.

---

## 1. Présentation du projet

- **Nom du projet** : HOPE
- **Objectif principal** : Illustrer nos compétences en conception et développement d’une application Java complète.
- **Fonctionnalités clés** :
  - Gestion des utilisateurs (création, authentification, etc.).
  - Interface conviviale (ex. JavaFX, Spring Boot, Swing, selon le choix final).
  - Connexion à une base de données pour la persistance des données.

---

## 2. Technologies utilisées

- **Java (JDK X)**  
- **Framework** (exemple : Spring Boot, JavaFX, etc.)  
- **Base de données** : MySQL ou H2 (selon la configuration choisie)  
- **Maven ou Gradle** (gestion des dépendances et build)

---

## 3. Installation et exécution

1. **Cloner ce dépôt** :  
   ```bash
   git clone https://github.com/Gaoul62/Projet-Java-HOPE.git
   cd Projet-Java-HOPE
## 3. Installation et exécution

2. **Installer les dépendances** 
   ```bash
   mvn clean install
3. **Lancer l’application**
   ```bash
   mvn spring-boot:run


## 4. Utilisation

- **Étape 1** : Démarrez l'application en suivant les instructions ci-dessus.  
- **Étape 2** : Ouvrez le navigateur et accédez à l'URL indiquée (par défaut : [http://localhost:8080](http://localhost:8080)).  
- **Étape 3** : Créez un compte utilisateur ou connectez-vous si un système d’authentification est implémenté.  
- **Étape 4** : Naviguez dans les différentes sections de l'application pour explorer les fonctionnalités (ajout, modification, suppression d’éléments).  

## 5. Structure du projet

Le projet est organisé en plusieurs couches pour assurer une séparation claire des responsabilités :  

- **Controller** : Gère les requêtes HTTP et redirige vers les services appropriés.  
- **Service** : Contient la logique métier.  
- **Repository** : Responsable des interactions avec la base de données via JPA.  
- **Model** : Définit les entités Java mappées à la base de données.  
## 6. Auteurs

Ce projet a été réalisé par notre équipe composée de :  

- **Issa**  
- **Mahdi**  
- **Ryan**  
- **Rayan**  

