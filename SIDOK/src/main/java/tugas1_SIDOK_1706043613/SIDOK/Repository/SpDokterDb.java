package tugas1_sidok_1706043613.sidok.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tugas1_sidok_1706043613.sidok.Models.Spesialisasi_DokterModel;

import java.util.List;

@Repository
public interface SpDokterDb extends JpaRepository<Spesialisasi_DokterModel, Long> {
    List<Spesialisasi_DokterModel> findBySpesialisasiModelId(Long long1);
    List<Spesialisasi_DokterModel> findByDokterModelId(Long dokterId);
}