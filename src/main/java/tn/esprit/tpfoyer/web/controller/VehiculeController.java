
package tn.esprit.tpfoyer.web.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.domain.Vehicule;
import tn.esprit.tpfoyer.repository.IVehiculeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {

    private final IVehiculeRepository vehiculeRepository;

    public VehiculeController(IVehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    @GetMapping
    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Vehicule> getVehiculeById(@PathVariable Long id) {
        return vehiculeRepository.findById(id);
    }

    @PostMapping
    public Vehicule addVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @PutMapping("/{id}")
    public Vehicule updateVehicule(
            @PathVariable Long id,
            @RequestBody Vehicule vehicule) {

        vehicule.setIdVehicule(id);
        return vehiculeRepository.save(vehicule);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicule(@PathVariable Long id) {
        vehiculeRepository.deleteById(id);
    }
}
