package fr.ysaintmartin.budgetzen.journal;

import fr.ysaintmartin.budgetzen.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JournalServiceTest {

    final JournalService journalService = new JournalService();

    @Test
    void createTransactionJournal_returnsTransactionJournalCreated() {

        JournalCreation journalCreation = new JournalCreation("compte courant", "CURRENT_ACCOUNT", 719.00);
        assertThat(journalService.createTransactionJournal(journalCreation))
                .extracting(TransactionJournalCreated::uuid)
                .isNotNull();
    }

    @Test
    void getAllTransactionJournals_returnsListOfTransactionJournalCreated() {
        assertThat(journalService.getAllTransactionJournals())
                .isNotNull();
    }

    @Test
    void getTransactionJournalByUuid_returnsTransactionJournalCreated() throws ObjectNotFoundException {
        assertThat(journalService.getTransactionJournalByUuid("uuid"))
                .isNotNull();
    }

    @Test
    void getTransactionJournalByUuid_throwsObjectNotFoundException() {
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> journalService.getTransactionJournalByUuid("id"));

        assertThat(exception)
                .isInstanceOf(ObjectNotFoundException.class);
    }

}
