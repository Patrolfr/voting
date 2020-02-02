package mar.fraczek.voting

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
open class VotingKt

fun main(args: Array<String>){
    SpringApplication.run(VotingKt::class.java, *args)
}
