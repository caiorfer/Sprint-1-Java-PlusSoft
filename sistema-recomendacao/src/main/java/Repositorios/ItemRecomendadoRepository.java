package Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import PrincipaisElementos.ItemRecomendado;

@Repository
public interface ItemRecomendadoRepository extends JpaRepository<ItemRecomendado, Long> {
    
}
