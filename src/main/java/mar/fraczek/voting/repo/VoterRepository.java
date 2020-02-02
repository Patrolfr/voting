package mar.fraczek.voting.repo;

import mar.fraczek.voting.domain.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoterRepository extends JpaRepository<Voter, String> {

    Optional<Voter> findByPesel(String pesel);

}
