package fr.ysaintmartin.budgetzen.journal;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JournalServiceTest {

    final JournalService journalService = new JournalService();

    @Test
    void createTransactionJournal_returnsTransactionJournalCreated() {

        JournalCreation journalCreation = new JournalCreation("compte courant", "CURRENT_ACCOUNT", 719.00);
        assertThat(journalService.createTransactionJournal(journalCreation))
                .extracting(TransactionJournalCreated::uuid)
                .isNotNull();
    }

}
