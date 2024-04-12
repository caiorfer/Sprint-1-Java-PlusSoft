package Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import PrincipaisElementos.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
   
}
