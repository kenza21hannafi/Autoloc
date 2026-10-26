
package tn.esprit.tpfoyer.web.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.domain.Agence;
import tn.esprit.tpfoyer.repository.IAgenceRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agences")
public class AgenceController {

    private final IAgenceRepository agenceRepository;

    public AgenceController(IAgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }

    @GetMapping
    public List<Agence> getAllAgences() {
        return agenceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Agence> getAgenceById(@PathVariable Long id) {
        return agenceRepository.findById(id);
    }

    @PostMapping
    public Agence addAgence(@RequestBody Agence agence) {
        return agenceRepository.save(agence);
    }

    @PutMapping("/{id}")
    public Agence updateAgence(
            @PathVariable Long id,
            @RequestBody Agence agence) {
        agence.setIdAgence(id);
        return agenceRepository.save(agence);
    }

    @DeleteMapping("/{id}")
    public void deleteAgence(@PathVariable Long id) {
        agenceRepository.deleteById(id);
    }
}
