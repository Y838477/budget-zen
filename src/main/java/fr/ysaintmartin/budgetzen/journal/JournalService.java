package fr.ysaintmartin.budgetzen.journal;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JournalService {

    List<TransactionJournalCreated> transactionJournals = new ArrayList<>();

    public JournalService() {
        transactionJournals.add(new TransactionJournalCreated("uuid", "compte courant", "CURRENT_ACCOUNT", 513.00));
        transactionJournals.add(new TransactionJournalCreated("uuid", "compte épargne", "SAVING_ACCOUNT", 315.00));
    }

    public TransactionJournalCreated createTransactionJournal(JournalCreation creationInformation) {
        return save(creationInformation);
    }

    private TransactionJournalCreated save(JournalCreation creationInformation) {
        TransactionJournalCreated transactionJournalCreated = new TransactionJournalCreated(UUID.randomUUID().toString(),
                creationInformation.title(),
                creationInformation.type(),
                creationInformation.initialBalance());
        transactionJournals.add(transactionJournalCreated);
        return transactionJournalCreated;
    }

    public List<TransactionJournalCreated> getAllTransactionJournals() {
        return transactionJournals;
    }

    public Optional<TransactionJournalCreated> getTransactionJournalByUuid(String uuid) {
        return transactionJournals.stream()
                .filter(txJournal -> txJournal.uuid().equals(uuid))
                .findFirst();
    }
}
