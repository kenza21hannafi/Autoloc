# Atelier 4 — Note sur les services, l'IoC et l'injection de dépendances

## A. Modes d'injection comparés

| Critère | Injection par constructeur | Injection par attribut (`@Autowired`) |
|---|---|---|
| Champ `final` possible ? | Oui, la dépendance peut être déclarée `private final`. | Non, le champ est renseigné après la construction de l'objet. |
| Dépendance visible ? | Oui, elle apparaît dans la signature du constructeur. | Non, elle est cachée dans la classe. |
| Utilisation avec `new` hors conteneur Spring | Possible si la dépendance est fournie explicitement au constructeur. | L'objet peut être créé, mais la dépendance reste `null` hors de Spring. |
| État de l'objet | Les dépendances obligatoires sont définies dès la construction. | L'objet peut exister sans ses dépendances. |
| SonarQube for IDE | Mode recommandé. | Peut déclencher la règle S6813 ; vérifier le diagnostic dans l'IDE. |
| Conclusion | Mode retenu pour les services. | À éviter pour les dépendances obligatoires. |

L'injection par constructeur rend les dépendances explicites et permet d'utiliser des champs `final`. Dans notre projet, nous utilisons `@Service`, `@RequiredArgsConstructor` et des dépendances déclarées `private final`.

## B. Services et dépendances injectées

| Service | Dépendance injectée | Mode | Justification |
|---|---|---|---|
| `AgenceServiceImpl` | `IAgenceRepository` | Constructeur | Accéder aux agences et appliquer les règles métier. |
| `ClientServiceImpl` | `IClientRepository` | Constructeur | Accéder aux clients et vérifier les données obligatoires. |
| `EmployeServiceImpl` | `IEmployeRepository` | Constructeur | Accéder aux employés et contrôler les informations obligatoires. |
| `VehiculeServiceImpl` | `IVehiculeRepository` | Constructeur | Accéder aux véhicules et valider les valeurs numériques. |
| `EquipementServiceImpl` | `IEquipementRepository` | Constructeur | Accéder aux équipements et vérifier les informations obligatoires. |
| `ReservationServiceImpl` | `IReservationRepository` | Constructeur | Accéder aux réservations et vérifier la cohérence des dates. |
| `ContratServiceImpl` | `IContratRepository` | Constructeur | Gérer les contrats, vérifier leur existence et appliquer les règles métier. |
| `PaiementServiceImpl` | `IPaiementRepository` | Constructeur | Consulter les paiements. Les opérations d'écriture passent par le contrat. |
| `MaintenanceServiceImpl` | `IMaintenanceRepository` | Constructeur | Gérer les maintenances et contrôler les données métier. |

Les services dépendent des interfaces des repositories et non de leurs implémentations concrètes. Spring détecte les services annotés avec `@Service` et leur fournit automatiquement leurs dépendances par injection de constructeur.



## C. Questions de compréhension

### 1. Où est le `new` de `ContratServiceImpl` et qui l'exécute ?

Il n'y a normalement aucun `new ContratServiceImpl(...)` écrit dans le code applicatif. Grâce à l'annotation `@Service` et au scan des composants, Spring détecte la classe, crée le bean au démarrage et lui fournit `IContratRepository` par son constructeur. C'est le principe de l'inversion de contrôle.

### 2. Pourquoi un contrôleur dépend-il de `IContratService` et non de `ContratServiceImpl` ?

Le contrôleur dépend de l'interface `IContratService`, qui représente le contrat du service, et non de son implémentation concrète. Cela réduit le couplage, facilite les tests et permet de remplacer l'implémentation sans modifier le contrôleur.

### 3. Pourquoi un service singleton doit-il rester sans état ? Donner un exemple de bug.

Un service Spring est singleton par défaut : la même instance peut être utilisée par plusieurs requêtes simultanément. Il ne faut donc pas stocker dans ses attributs des données modifiables propres à une requête.

Par exemple, si un service stocke un « contrat courant » dans un attribut, deux requêtes concurrentes peuvent modifier cette valeur et provoquer le traitement du mauvais contrat. Les données propres à une opération doivent rester dans des variables locales ou des objets métier adaptés.

### 4. Pourquoi `update` charge-t-il l'entité existante avant `save` ?

Charger l'entité existante permet de vérifier que l'identifiant existe et de modifier uniquement les champs prévus. Passer directement l'objet reçu à `save` risque d'écraser des champs ou des associations qui n'ont pas été fournis.

Dans le cas de `Contrat`, cette approche permet notamment de préserver les paiements existants et d'éviter des effets indésirables liés à `orphanRemoval`.

### 5. Quelle différence entre `@Component` et `@Bean` ? Entre `@Primary` et `@Qualifier` ? Pourquoi `IPaiementService` n'expose-t-il que la lecture ?

**`@Component` et `@Bean` :**

- `@Component` est placée sur une classe pour que Spring la détecte automatiquement pendant le scan des composants. `@Service` est une spécialisation de `@Component` destinée à la couche métier.
- `@Bean` est placée sur une méthode d'une classe `@Configuration`. Spring enregistre comme bean l'objet retourné par cette méthode.

**`@Primary` et `@Qualifier` :**

- `@Primary` indique le bean à privilégier par défaut lorsqu'il existe plusieurs candidats du même type.
- `@Qualifier` permet de sélectionner explicitement un bean particulier au point d'injection.

**Pourquoi `IPaiementService` est-il en lecture seule ?**

Le support prévoit que `IPaiementService` expose uniquement `findById` et `findAll`. La création, la modification et la suppression des paiements passent par le contrat afin de préserver la cohérence entre `Contrat` et `Paiement` et de respecter les règles de cascade définies dans le modèle.

---

