package mar.fraczek.voting.service;

import lombok.RequiredArgsConstructor;
import mar.fraczek.voting.domain.Vote;
import mar.fraczek.voting.domain.dto.VotingPayload;
import mar.fraczek.voting.repo.VoteRepository;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@RequiredArgsConstructor
public class VoteService {

    private static final String VOTERS_CACHE = "voters";

    private final VoteRepository voteRepository;

    @Resource(name = "redisTemplate")
    private SetOperations<String, String> setOperations;


    @Transactional(isolation = Isolation.REPEATABLE_READ)
//    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Vote vote(final VotingPayload votingPayload) {

        String voterPesel = getCurrentUserName();

        if (!voteRepository.existsByVoter(voterPesel)) {

            Vote vote = Vote.Factory.ofVotingPayloadAndVoter(votingPayload, voterPesel);
            return voteRepository.save(vote);
        }
        return null;
    }

    public Vote voteWithCache(final VotingPayload votingPayload) {

        String voterPesel = getCurrentUserName();
        Long logicResult = setOperations.add(VOTERS_CACHE, voterPesel);

        if (logicResult == 1L) {
            Vote vote = Vote.Factory.ofVotingPayloadAndVoter(votingPayload, voterPesel);
            return voteRepository.save(vote);
        }
        return null;
    }

    private String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
