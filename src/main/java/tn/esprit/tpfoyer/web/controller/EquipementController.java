
package tn.esprit.tpfoyer.web.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.domain.Equipement;
import tn.esprit.tpfoyer.repository.IEquipementRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipements")
public class EquipementController {

    private final IEquipementRepository equipementRepository;

    public EquipementController(IEquipementRepository equipementRepository) {
        this.equipementRepository = equipementRepository;
    }

    @GetMapping
    public List<Equipement> getAllEquipements() {
        return equipementRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Equipement> getEquipementById(@PathVariable Long id) {
        return equipementRepository.findById(id);
    }

    @PostMapping
    public Equipement addEquipement(@RequestBody Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    @PutMapping("/{id}")
    public Equipement updateEquipement(
            @PathVariable Long id,
            @RequestBody Equipement equipement) {
        equipement.setIdEquipement(id);
        return equipementRepository.save(equipement);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipement(@PathVariable Long id) {
        equipementRepository.deleteById(id);
    }
}
