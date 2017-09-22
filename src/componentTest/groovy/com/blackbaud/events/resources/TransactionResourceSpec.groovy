package com.blackbaud.events.resources

import com.blackbaud.boot.exception.BadRequestException
import com.blackbaud.events.ComponentTest
import com.blackbaud.events.api.Ticket
import com.blackbaud.events.api.Transaction
import com.blackbaud.events.client.TicketClient
import com.blackbaud.events.client.TransactionClient
import com.blackbaud.events.core.domain.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

import static com.blackbaud.events.core.CoreARandom.aRandom

@ComponentTest
class TransactionResourceSpec extends Specification {

    @Autowired
    private TransactionRepository transactionRepository

    @Autowired
    private TransactionClient transactionClient

    @Autowired
    private TicketClient ticketClient

    def "Basic crud operations"() {
        given:
        Ticket ticket = ticketClient.create(aRandom.ticket().build())
        Transaction transaction = aRandom.transaction().id(null).ticketId(ticket.id).build()

        when:
        Transaction savedTransaction = transactionClient.create(transaction)

        then:
        savedTransaction.id > 0

        when:
        transactionClient.delete(savedTransaction.id)

        then:
        transactionRepository.findAll().size() == 0
    }

    def "should not be able to buy more tickets than remaining capacity"() {
        given:
        Ticket ticket = ticketClient.create(aRandom.ticket().capacity(100).build())
        def transaction = aRandom.transaction().id(null).ticketId(ticket.id).quantity(20).build()
        transactionClient.create(transaction)

        when:
        Transaction newTransaction = aRandom.transaction().ticketId(ticket.id).quantity(81).build()
        transactionClient.create(newTransaction)

        then:
        thrown(BadRequestException)
    }
}
