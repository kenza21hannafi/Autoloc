# Justification des stratégies Fetch et Cascade

## 1. Stratégie Fetch

La stratégie `FetchType.LAZY` est utilisée pour les associations afin de ne charger les données associées que lorsqu'elles sont nécessaires. Cela permet de limiter les chargements inutiles, de réduire les échanges avec la base de données et d'améliorer les performances.

Cette stratégie est appliquée aux associations entre les agences, les véhicules, les employés, les clients, les réservations, les contrats, les paiements, les équipements et les maintenances.

## 2. Stratégie Cascade

- **Contrat – Paiement :** `CascadeType.ALL` permet de propager les opérations sur les paiements depuis le contrat. `orphanRemoval = true` permet de supprimer un paiement retiré de la collection du contrat.
- **Client – Reservation :** `CascadeType.PERSIST` permet de persister les réservations lors de la persistance d'un client.
- **Reservation – Contrat :** `CascadeType.ALL` permet de propager les opérations du côté de la réservation vers le contrat associé.
- **Vehicule – Maintenance :** `CascadeType.PERSIST` permet de persister les maintenances lors de la persistance d'un véhicule.
- **Agence – Vehicule et Agence – Employe :** aucune cascade n'est configurée afin de conserver le contrôle des opérations sur les véhicules et les employés indépendamment de l'agence.
- **Vehicule – Equipement :** aucune cascade n'est configurée, car les équipements peuvent être partagés entre plusieurs véhicules.

## 3. Conclusion

Les stratégies Fetch et Cascade sont choisies selon les besoins fonctionnels et les dépendances entre les entités. Le chargement différé limite les chargements inutiles, tandis que les cascades sont réservées aux associations pour lesquelles la propagation des opérations est justifiée.