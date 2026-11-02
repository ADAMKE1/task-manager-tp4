# Task Manager

Application **Spring Boot** de gestion de tâches, produite avec une équipe d'agents IA (TP 3).

## Fonctionnalités

- GET `/api/tasks` : Lister / rechercher (?title=…&status=…)
- GET `/api/tasks/{id}` : Consulter une tâche
- POST `/api/tasks` : Créer une tâche
- PUT `/api/tasks/{id}` : Modifier une tâche
- DELETE `/api/tasks/{id}` : Supprimer une tâche

## Architecture

```text
Controller → Service → Repository → Base de données (H2)
```

Couches générées : config, entity, dto, repository, service, controller, exception.

## Prérequis

- Java 21
- Maven 3.9+
- Docker (optionnel)

## Lancer l'application

```bash
mvn spring-boot:run
```

L'API répond sur http://localhost:8080/api/tasks

## Exécuter les tests

```bash
mvn clean test
```

Dernier résultat (simulé) : ✅ succès : 0 échec(s).

## Construire et lancer avec Docker

```bash
mvn clean package
docker build -t task-manager .
docker run -p 8080:8080 task-manager
```

Un pipeline GitHub Actions (`.github/workflows/ci.yml`) construit et teste le projet à chaque push.

## Qualité du code

Revue automatique : 0 critique(s), 0 haute(s), 0 moyenne(s), 0 basse(s).
