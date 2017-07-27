package com.blackbaud.events.resources

import com.blackbaud.events.ComponentTest
import com.blackbaud.events.api.Transaction
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

    def "Basic crud operations"() {
        given:
        Transaction transaction = aRandom.transaction().id(null).build()

        when:
        Transaction savedTransaction = transactionClient.create(transaction)

        then:
        savedTransaction.id > 0

        when:
        transactionClient.delete(savedTransaction.id)

        then:
        transactionRepository.findAll().size() == 0
    }
}
