
package tn.esprit.tpfoyer.web.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.domain.Maintenance;
import tn.esprit.tpfoyer.repository.IMaintenanceRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {

    private final IMaintenanceRepository maintenanceRepository;

    public MaintenanceController(IMaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @GetMapping
    public List<Maintenance> getAllMaintenances() {
        return maintenanceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Maintenance> getMaintenanceById(@PathVariable Long id) {
        return maintenanceRepository.findById(id);
    }

    @PostMapping
    public Maintenance addMaintenance(@RequestBody Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    @PutMapping("/{id}")
    public Maintenance updateMaintenance(
            @PathVariable Long id,
            @RequestBody Maintenance maintenance) {
        maintenance.setIdMaintenance(id);
        return maintenanceRepository.save(maintenance);
    }

    @DeleteMapping("/{id}")
    public void deleteMaintenance(@PathVariable Long id) {
        maintenanceRepository.deleteById(id);
    }
}
