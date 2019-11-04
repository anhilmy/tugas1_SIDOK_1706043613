package tugas1_sidok_1706043613.sidok.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tugas1_sidok_1706043613.sidok.Models.SpesialisasiModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpesialiasasiDb extends JpaRepository<SpesialisasiModel, Long> {
    Optional<SpesialisasiModel> findById(Long id);
    List<SpesialisasiModel> findAll();
}