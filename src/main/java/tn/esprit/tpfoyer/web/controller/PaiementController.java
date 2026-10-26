
package tn.esprit.tpfoyer.web.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.domain.Paiement;
import tn.esprit.tpfoyer.repository.IPaiementRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    private final IPaiementRepository paiementRepository;

    public PaiementController(IPaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    @GetMapping
    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Paiement> getPaiementById(@PathVariable Long id) {
        return paiementRepository.findById(id);
    }

    @PostMapping
    public Paiement addPaiement(@RequestBody Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @PutMapping("/{id}")
    public Paiement updatePaiement(
            @PathVariable Long id,
            @RequestBody Paiement paiement) {
        paiement.setIdPaiement(id);
        return paiementRepository.save(paiement);
    }

    @DeleteMapping("/{id}")
    public void deletePaiement(@PathVariable Long id) {
        paiementRepository.deleteById(id);
    }
}
