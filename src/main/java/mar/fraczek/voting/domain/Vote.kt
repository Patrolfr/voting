package mar.fraczek.voting.domain

import mar.fraczek.voting.domain.dto.VotingPayload
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "vote_v2")
data class Vote (
        val answer1: String="",
        val answer2: String="",
        val answer3: String="",
        val voter: String="") {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
    @SequenceGenerator(name="author_generator", sequenceName = "vote_v2_id_seq", allocationSize = 1)
    var id: Long = 0

    @field:CreationTimestamp
    lateinit var createdAt: LocalDateTime

    companion object Factory {
        fun ofVotingPayloadAndVoter(payload: VotingPayload, voter: String): Vote {
            return Vote(
                    payload.answer1,
                    payload.answer2,
                    payload.answer3,
                    voter
            )
        }
    }

}