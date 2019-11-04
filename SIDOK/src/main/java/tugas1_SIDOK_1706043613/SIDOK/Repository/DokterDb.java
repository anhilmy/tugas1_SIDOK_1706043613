package tugas1_sidok_1706043613.sidok.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tugas1_sidok_1706043613.sidok.Models.DokterModel;
import java.util.List;
import java.util.Optional;

@Repository
public interface DokterDb extends JpaRepository<DokterModel, Long> {
    Optional<DokterModel> findByNik(String nik);
    List<DokterModel> findAll();
	Optional<DokterModel> findByNip(String nip);
    
    @Query(value = "SELECT COUNT(*), dokter_id FROM jadwal_jaga where poli_id = :poli_id GROUP BY dokter_id", nativeQuery = true)
    List<List<Long>> dokterTersibukDariPoli(@Param("poli_id") Long idPoli);
}