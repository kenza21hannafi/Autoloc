# Repositories Spring Data JPA

## 1. Choix de l'interface

Les neuf repositories étendent `JpaRepository<Entité, Long>`. Cette interface fournit les opérations CRUD, notamment `save`, `findById`, `findAll` et `deleteById`. Elle permet également d'utiliser `saveAndFlush`.

Le type de clé `Long` correspond aux identifiants des entités du projet.

| Repository | Interface étendue | Justification |
|---|---|---|
| IAgenceRepository | JpaRepository<Agence, Long> | CRUD complet |
| IEmployeRepository | JpaRepository<Employe, Long> | CRUD complet |
| IVehiculeRepository | JpaRepository<Vehicule, Long> | CRUD complet |
| IEquipementRepository | JpaRepository<Equipement, Long> | CRUD complet |
| IClientRepository | JpaRepository<Client, Long> | CRUD complet |
| IReservationRepository | JpaRepository<Reservation, Long> | CRUD complet |
| IContratRepository | JpaRepository<Contrat, Long> | CRUD complet, findAll renvoie une List |
| IPaiementRepository | JpaRepository<Paiement, Long> | Lecture et gestion des paiements |
| IMaintenanceRepository | JpaRepository<Maintenance, Long> | CRUD complet |

#Analyse SonarQube for IDE

## Anomalies détectées et corrigées

| Anomalie | Règle Sonar | Correction apportée |
|---|---|---|
| Controller retournant directement une entité JPA | java:S4684 (selon l'avertissement affiché dans IntelliJ) | Création de DTO et adaptation des méthodes du controller |
| Import inutilisé | À relever dans SonarQube for IDE | Suppression de l'import inutilisé, si détecté |
| Annotation Lombok `@Data` sur une entité JPA | À relever dans SonarQube for IDE | Remplacement par des annotations Lombok ciblées, si nécessaire |



## 3. Conclusion

Les repositories séparent l'accès aux données de la logique des controllers. Spring Data JPA génère automatiquement leurs implémentations à l'exécution. Aucun code d'implémentation manuel n'est nécessaire.