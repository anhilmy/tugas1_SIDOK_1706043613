package tugas1_sidok_1706043613.sidok.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tugas1_sidok_1706043613.sidok.Models.PoliModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoliDb extends JpaRepository<PoliModel, Long> {
    Optional<PoliModel> findById(Long id);
    List<PoliModel> findAll();
}