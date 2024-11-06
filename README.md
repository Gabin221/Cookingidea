# Cooking-idea

## Résumé du projet
Application mobile en Kotlin qui suggère des plats à cuisiner en fonction de critères personnalisés: régime alimentaire, ingrédients spécifiques, etc.

---

## Présentation du projet : App de Suggestions de Plats en Kotlin

### Description
Cette application mobile en Kotlin aide les utilisateurs à trouver des idées de plats à cuisiner selon des critères de préférence notamment le régime alimentaire, les ingrédients à éviter ainsi que le nombre de repas (pour une semaine ou pour une journée). Elle utilise l'API [Spoonacular](https://spoonacular.com/food-api) pour récupérer des suggestions de plats en temps réel.

### Fonctionnalités
- **Suggestions de plats personnalisées** : Propose des recettes basées sur les préférences de l'utilisateur.
- **Ingrédients spécifiques** : Permet de forcer l'exclusion de certains ingrédients.
- **Interface unique** : Formulaire de recherche et liste de suggestions de recettes sur une seule page pour une expérience fluide.

### Aperçu
L’application comprend une page unique qui combine un formulaire en haut pour filtrer les suggestions et une liste en dessous pour afficher les recettes proposées.

---

## Installation

### Pré-requis
- Android Studio
- Clé API Spoonacular (obtenez-la [ici](https://spoonacular.com/food-api))

### Étapes d'installation

1. Clonez le dépôt :

```bash
git clone https://github.com/Gabin221/Cookingidea.git
cd Cookingidea
```

2. Ouvrez le projet dans Android Studio.

3. Dans `RecipeViewModel.kt`, ajoutez votre clé API Spoonacular à la fonction d’appel d'API :
   ```
   const val apiKey = "VOTRE_CLÉ_API"
   ```

4. Compilez et exécutez l'application sur un émulateur ou un appareil physique.

---

## Utilisation

1. Renseignez le formulaire de recherche en haut de la page.
2. Cliquez sur "Rechercher" pour obtenir des suggestions de recettes correspondant aux critères.
3. Faites défiler la liste des suggestions pour explorer les recettes.

---

## Technologies utilisées

- **Kotlin** : Langage principal pour le développement Android.
- **Spoonacular API** : Source des données de recettes.
