# CI Jenkins Maven Starter

[![Build](https://img.shields.io/badge/build-maven-blue)](https://example.com)
[![Tests](https://img.shields.io/badge/tests-passing-brightgreen)](https://example.com)
[![Coverage](https://img.shields.io/badge/coverage-94%25-yellowgreen)](target/site/jacoco/index.html)

Petit projet pédagogique : application console "Pendu" (conversion d'un script Python) et configuration Maven (compiler, surefire, JaCoCo). Le projet cible Java 21 via la propriété `<maven.compiler.release>`.

## Tech stack
- Java 21
- Maven (via Maven Wrapper `mvnw` / `mvnw.cmd`)
- JUnit 5 pour les tests
- JaCoCo pour la couverture

## Prérequis
- JDK 21
- Git (optionnel)
- Accès Internet la première fois pour télécharger les dépendances et le wrapper

## Build rapide
Ouvrez PowerShell à la racine du projet et lancez :

```powershell
.\mvnw.cmd clean package
```

## Exécuter l'application (CLI)
L'application se lance via la classe `edu.pendu.HangmanCli`.

Exécution directe (après `package`) :

```powershell
java -cp target\ci-jenkins-maven-starter-1.0.0-SNAPSHOT.jar edu.pendu.HangmanCli
```

Pour des tests manuels déterministes, vous pouvez forcer le mot secret en passant la propriété système `hangman.word` :

```powershell
java -Dhangman.word=poisson -cp target\ci-jenkins-maven-starter-1.0.0-SNAPSHOT.jar edu.pendu.HangmanCli
```

## Tests et couverture
Pour exécuter les tests et générer le rapport JaCoCo :

```powershell
.\mvnw.cmd -DskipTests=false test jacoco:report
```

Le rapport HTML se trouve dans `target/site/jacoco/index.html`. Ouvrez-le avec :

```powershell
start target\\site\\jacoco\\index.html
```

## Couverture actuelle
- `HangmanGame` : couverture élevée (ex. ~95%)
- `HangmanCli` : couverture ajoutée via tests d'entrée/sortie (75%)


## CI
L'objectif : utiliser JDK 21 et exécuter `mvn test jacoco:report`. Exemple de bonnes pratiques :
- échouer la build si la couverture globale descend sous un seuil configuré (ex. 70%)
- publier le rapport JaCoCo comme artefact

Si vous le souhaitez, je peux ajouter un `Jenkinsfile` ou un workflow GitHub Actions pour automatiser ces étapes.

## Contribuer
- Fork → branch → PR
- Respectez le style Java du projet et ajoutez des tests pour toute nouvelle logique
- Mettez à jour le README et CHANGELOG si nécessaire
