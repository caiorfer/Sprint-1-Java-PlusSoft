package Controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import PrincipaisElementos.Preferencia;
import Repositorios.PreferenciaRepository;

@RestController
@RequestMapping("/api/preferencias")
public class ControlePreferencia {

    @Autowired
    private PreferenciaRepository preferenciaRepository; 

    // Recupera todas as preferências
    @GetMapping
    public ResponseEntity<List<Preferencia>> getAllPreferencias() {
        List<Preferencia> preferencias = preferenciaRepository.findAll();
        return ResponseEntity.ok(preferencias);
    }

    // Recupera uma preferência por ID
    @GetMapping("/{id}")
    public ResponseEntity<Preferencia> getPreferenciaById(@PathVariable Long id) {
        Optional<Preferencia> preferencia = preferenciaRepository.findById(id);
        return preferencia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Cria uma nova preferência
    @PostMapping
    public ResponseEntity<Preferencia> createPreferencia(@RequestBody Preferencia preferencia) {
        Preferencia savedPreferencia = preferenciaRepository.save(preferencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPreferencia);
    }

    // Atualiza uma preferência existente
    @PutMapping("/{id}")
    public ResponseEntity<Preferencia> updatePreferencia(@PathVariable Long id, @RequestBody Preferencia preferencia) {
        if (!preferenciaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        preferencia.setId(id);
        Preferencia updatedPreferencia = preferenciaRepository.save(preferencia);
        return ResponseEntity.ok(updatedPreferencia);
    }

    // Exclui uma preferência
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePreferencia(@PathVariable Long id) {
        if (!preferenciaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        preferenciaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
