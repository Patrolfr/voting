package mar.fraczek.voting.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Voter {

    @Id
    @PESEL
    private String pesel;

    private String firstName;

    private String lastName;

}
