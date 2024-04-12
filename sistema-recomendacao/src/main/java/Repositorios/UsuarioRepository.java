package Repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import PrincipaisElementos.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}

