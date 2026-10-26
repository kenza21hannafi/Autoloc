
package tn.esprit.tpfoyer.web.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.domain.Contrat;
import tn.esprit.tpfoyer.repository.IContratRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contrats")
public class ContratController {

    private final IContratRepository contratRepository;

    public ContratController(IContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    @GetMapping
    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Contrat> getContratById(@PathVariable Long id) {
        return contratRepository.findById(id);
    }

    @PostMapping
    public Contrat addContrat(@RequestBody Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @PutMapping("/{id}")
    public Contrat updateContrat(
            @PathVariable Long id,
            @RequestBody Contrat contrat) {
        contrat.setIdContrat(id);
        return contratRepository.save(contrat);
    }

    @DeleteMapping("/{id}")
    public void deleteContrat(@PathVariable Long id) {
        contratRepository.deleteById(id);
    }
}
