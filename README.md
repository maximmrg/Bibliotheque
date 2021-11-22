# Bibliotheque
Cette application permet la gestion des activités principales d'une bibliothèque par un bibliothécaire.
Elle permet de gérer des utilisateurs, des oeuvres, des exemplaire.
Elle permet aussi de gérer les emprunts et réservations effectués par les utilisateurs.

## Technologies
Cette application a été créée en Spring boot.
Les dépendances utilisées sont :
- jpa
- thymeleaf
- spring boot web
- devtools
- h2
- lombok
- hateaos
- postgresql

## Packages
### Component
Ce package contient les Repository des différents objets de notre application. C'est dans ces classes que sont définies les méthodes pour effectuer des requêtes en base de données.

### Controller
Ce package contient les controleurs de l'application. Ce sont eux qui vont effectuer la vérification et le traitement des données envoyées par les formulaires dans les vues.

### Entity
Ce package contient les objets métier de notre application, c'est à dire les entités qui seront stockées dans la base de données.

### static
C'est dans ce package que les ressources statiques du site doivent être définies, comme les fichiers css, javaScript ou les images.

### templates
Enfin, ce dernier package va contenir les pages du site web
