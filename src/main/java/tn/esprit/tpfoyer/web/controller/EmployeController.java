
package tn.esprit.tpfoyer.web.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.domain.Employe;
import tn.esprit.tpfoyer.repository.IEmployeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {

    private final IEmployeRepository employeRepository;

    public EmployeController(IEmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @GetMapping
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Employe> getEmployeById(@PathVariable Long id) {
        return employeRepository.findById(id);
    }

    @PostMapping
    public Employe addEmploye(@RequestBody Employe employe) {
        return employeRepository.save(employe);
    }

    @PutMapping("/{id}")
    public Employe updateEmploye(
            @PathVariable Long id,
            @RequestBody Employe employe) {
        employe.setIdEmploye(id);
        return employeRepository.save(employe);
    }

    @DeleteMapping("/{id}")
    public void deleteEmploye(@PathVariable Long id) {
        employeRepository.deleteById(id);
    }
}
