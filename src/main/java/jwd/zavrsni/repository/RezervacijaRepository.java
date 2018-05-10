package jwd.zavrsni.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.zavrsni.model.Rezervacija;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Integer> {

	@Query("SELECT s FROM Rezervacija s WHERE " + "((:datumUlaska < s.datumUlaska) AND (:datumIzlaska > s.datumUlaska))"
			+ "OR ((:datumUlaska < s.datumIzlaska))")
	List<Rezervacija> pronadji(@Param("datumUlaska") Date datumUlaska, @Param("datumIzlaska") Date datumIzlaska);

	@Query("SELECT s FROM Rezervacija s WHERE " + "(s.imeGosta like :imeP ) AND (s.placeno = false) AND " + "(s.prezimeGosta like :prezimeP)")
	List<Rezervacija> pronadji(@Param("imeP") String imeP, @Param("prezimeP") String prezimeP);

	@Query("SELECT s FROM Rezervacija s WHERE " + "(s.imeGosta like :imeP ) AND " + "(s.prezimeGosta like :prezimeP)")
	List<Rezervacija> pronadjiStatistika(@Param("imeP") String imeP, @Param("prezimeP") String prezimeP);

	//(s.placeno = false),     a ne s.placeno like false
}
