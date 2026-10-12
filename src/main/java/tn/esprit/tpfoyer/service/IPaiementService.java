
package tn.esprit.tpfoyer.service;

import java.util.List;
import tn.esprit.tpfoyer.domain.Paiement;

public interface IPaiementService {
    Paiement findById(Long id);
    List<Paiement> findAll();
}
