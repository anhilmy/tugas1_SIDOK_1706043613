package tugas1_sidok_1706043613.sidok.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tugas1_sidok_1706043613.sidok.Models.Jadwal_JagaModel;
import tugas1_sidok_1706043613.sidok.Models.PoliModel;

import java.util.List;

@Repository
public interface JadwalDb extends JpaRepository<Jadwal_JagaModel, Long> {
    List<Jadwal_JagaModel> findByPoliModelId(Long long1);
    List<Jadwal_JagaModel> findByDokterModelId(Long id);
    List<Jadwal_JagaModel> findByPoliModel(PoliModel poli);
    
    // @Query(value="SELECT COUNT(*), id_dokter FROM jadwal_jaga WHERE id_poli = :idPoli GROUP BY id_dokter ORDER BY COUNT(*) DESC")
    // List<Object[]> findMostCountDokterByIdPoli(@Param("idPoli") Long idPoli);

}