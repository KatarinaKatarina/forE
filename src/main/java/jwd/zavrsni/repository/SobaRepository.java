package jwd.zavrsni.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.zavrsni.model.Soba;

@Repository
public interface SobaRepository extends JpaRepository<Soba, Integer> {


}
