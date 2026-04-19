# QuickCare - Urgent Medical Response Application

## Description
QuickCare est une application Android pour localiser rapidement l'hôpital le plus proche capable de traiter une urgence médicale (accidents, crises cardiaques, urgences d'accouchement).

## Fonctionnalités
- ✅ Sélection du type d'urgence médicale
- ✅ Géolocalisation automatique de l'utilisateur
- ✅ Recherche de l'hôpital le plus proche disposant du service approprié
- ✅ Affichage des hôpitals recommandés avec distance
- ✅ Navigation vers l'hôpital via Google Maps
- ✅ Interface intuitive et rapide

## Architecture
- **Models**: Définition des entités (Hopital, TypeUrgence, Urgence, Position)
- **Services**: Logique métier (recherche d'hôpitaux, fournisseur de données, géolocalisation)
- **UI**: Activités (MainActivity, ResultatActivity)
- **Resources**: Layouts, drawables, strings, colors, dimens

## Configuration Requise
- Android API 24+
- Kotlin 1.9.10+
- Gradle 8.2+
- Google Play Services 21.0.1+

## Installation et Compilation
```bash
# Clone le projet
git clone <repo-url>
cd QuickCare

# Compiler le projet
./gradlew build

# Installer sur un appareil
./gradlew installDebug

# Lancer l'application
./gradlew installDebug

# Exécuter les tests
./gradlew test
```

## Permissions Requises
- `ACCESS_FINE_LOCATION` - Pour obtenir la géolocalisation GPS
- `INTERNET` - Pour accéder à Google Maps

## Structure du Projet
```
QuickCare/
├── app/
│   ├── src/main/
│   │   ├── java/com/quickcare/
│   │   │   ├── models/          # Modèles de données
│   │   │   ├── services/        # Logique métier
│   │   │   └── ui/              # Activities
│   │   ├── res/
│   │   │   ├── layout/          # Fichiers XML des layouts
│   │   │   ├── drawable/        # Icônes et images
│   │   │   └── values/          # Strings, colors, dimens, styles
│   │   └── AndroidManifest.xml
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── build.gradle.kts             # Configuration Gradle racine
├── settings.gradle.kts          # Configuration des modules
├── gradle.properties            # Propriétés Gradle
├── gradlew                       # Wrapper Gradle (Linux/Mac)
├── gradlew.bat                  # Wrapper Gradle (Windows)
└── README.md
```

## Fichiers de Base de Données
Les hôpitaux sont actuellement stockés dans `HopitalDataProvider.kt`. Pour une utilisation en production, il faudrait :
- Intégrer une vraie base de données (Room, Firebase, etc.)
- Récupérer les hôpitaux depuis une API

## Dépendances Principales
- `androidx.appcompat:appcompat` - Support AppCompat
- `com.google.android.material:material` - Material Design
- `com.google.android.gms:play-services-location` - Géolocalisation
- `androidx.cardview:cardview` - CardView
- `androidx.constraintlayout:constraintlayout` - ConstraintLayout

## Améliorations Futures
- [ ] Intégration avec une vraie base de données
- [ ] Synchronisation des données hôpitaux depuis le serveur
- [ ] Filtre par type de service médical
- [ ] Historique des urgences
- [ ] Partage d'urgence avec les contacts de secours
- [ ] Notifications en temps réel
- [ ] Support multi-langue

## Auteur
QuickCare Development Team

## License
MIT License
# QuickCare
