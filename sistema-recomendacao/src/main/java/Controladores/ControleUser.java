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

import PrincipaisElementos.Usuario;
import Repositorios.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")

public class ControleUser {
	
	 @Autowired
	    private UsuarioRepository usuarioRepository;

	    //recuperar todos os usuários
	    @GetMapping
	    public ResponseEntity<List<Usuario>> getAllUsuarios() {
	        List<Usuario> usuarios = usuarioRepository.findAll();
	        return ResponseEntity.ok(usuarios);
	    }

	    //recuperar um usuário por ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
			Optional<Usuario> usuario = usuarioRepository.findById(id);
	        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    //criar um novo usuário
	    @PostMapping
	    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
	        Usuario savedUsuario = usuarioRepository.save(usuario);
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
	    }

	    //atualizar um usuário existente
	    @PutMapping("/{id}")
	    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
	        if (!usuarioRepository.existsById(id)) {
	            return ResponseEntity.notFound().build();
	        }
	        usuario.setId(id);
	        Usuario updatedUsuario = usuarioRepository.save(usuario);
	        return ResponseEntity.ok(updatedUsuario);
	    }

	    //excluir um usuário
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
	        if (!usuarioRepository.existsById(id)) {
	            return ResponseEntity.notFound().build();
	        }
	        usuarioRepository.deleteById(id);
	        return ResponseEntity.noContent().build();
	    }
	}