package mar.fraczek.voting.resource;

import lombok.RequiredArgsConstructor;
import mar.fraczek.voting.domain.Vote;
import mar.fraczek.voting.domain.Voter;
import mar.fraczek.voting.domain.dto.VotingPayload;
import mar.fraczek.voting.repo.VoterRepository;
import mar.fraczek.voting.service.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VotingController {

    private final VoteService voteService;

    private final VoterRepository voterRepository;


    @PostMapping("/votes")
    public ResponseEntity vote(@RequestBody final VotingPayload votingPayload) {

        Vote vote = voteService.vote(votingPayload);

        if (vote!=null)
            return ResponseEntity.ok(vote);
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/v2/votes")
    public ResponseEntity voteWithCacHE(@RequestBody final VotingPayload votingPayload) {

        Vote vote = voteService.voteWithCache(votingPayload);

        if (vote!=null)
            return ResponseEntity.ok(vote);
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/voters")
    public ResponseEntity addVoter(@RequestBody final Voter voter){
        Voter newVoter = voterRepository.save(voter);
        return ResponseEntity.ok(voter);
    }

}
