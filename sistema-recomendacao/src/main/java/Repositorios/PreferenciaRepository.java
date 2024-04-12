package Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import PrincipaisElementos.Preferencia;

@Repository
public interface PreferenciaRepository extends JpaRepository<Preferencia, Long> {

}
