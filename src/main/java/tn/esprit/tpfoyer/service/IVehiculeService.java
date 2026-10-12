
package tn.esprit.tpfoyer.service;

import java.util.List;
import tn.esprit.tpfoyer.domain.Vehicule;

public interface IVehiculeService {
    Vehicule create(Vehicule vehicule);
    Vehicule findById(Long id);
    List<Vehicule> findAll();
    Vehicule update(Long id, Vehicule vehicule);
    void deleteById(Long id);
}
