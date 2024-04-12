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

import PrincipaisElementos.ItemRecomendado;
import Repositorios.ItemRecomendadoRepository;

@RestController
@RequestMapping("/api/items-recomendados")
public class ControleItem {

    @Autowired
    private ItemRecomendadoRepository itemRecomendadoRepository;

    // Recuperar todos os itens recomendados
    @GetMapping
    public ResponseEntity<List<ItemRecomendado>> getAllItemsRecomendados() {
        List<ItemRecomendado> itemsRecomendados = itemRecomendadoRepository.findAll();
        return ResponseEntity.ok(itemsRecomendados);
    }

    // Recuperar um item recomendado por ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemRecomendado> getItemRecomendadoById(@PathVariable Long id) {
        Optional<ItemRecomendado> itemRecomendado = itemRecomendadoRepository.findById(id);
        return itemRecomendado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Criar um novo item recomendado
    @PostMapping
    public ResponseEntity<ItemRecomendado> createItemRecomendado(@RequestBody ItemRecomendado itemRecomendado) {
        ItemRecomendado savedItemRecomendado = itemRecomendadoRepository.save(itemRecomendado);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItemRecomendado);
    }

    // Atualizar um item recomendado existente
    @PutMapping("/{id}")
    public ResponseEntity<ItemRecomendado> updateItemRecomendado(@PathVariable Long id, @RequestBody ItemRecomendado itemRecomendado) {
        if (!itemRecomendadoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        itemRecomendado.setId(id);
        ItemRecomendado updatedItemRecomendado = itemRecomendadoRepository.save(itemRecomendado);
        return ResponseEntity.ok(updatedItemRecomendado);
    }

    // Excluir um item recomendado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemRecomendado(@PathVariable Long id) {
        if (!itemRecomendadoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        itemRecomendadoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
