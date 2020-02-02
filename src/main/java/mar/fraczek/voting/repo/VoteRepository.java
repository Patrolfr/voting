package mar.fraczek.voting.repo;

import mar.fraczek.voting.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    boolean existsByVoter(String voter);
}
