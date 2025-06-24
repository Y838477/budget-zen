package fr.ysaintmartin.budgetzen.journal;

import fr.ysaintmartin.budgetzen.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JournalService {

    List<TransactionJournalCreated> transactionJournals = new ArrayList<>();

    public JournalService() {
        transactionJournals.add(new TransactionJournalCreated("uuid", "compte courant", "CURRENT_ACCOUNT", 513.00));
        transactionJournals.add(new TransactionJournalCreated("uuid", "compte épargne", "SAVING_ACCOUNT", 315.00));
    }

    public TransactionJournalCreated createTransactionJournal(JournalCreation creationInformation) {
        return new TransactionJournalCreated(
                "uuid",
                creationInformation.title(),
                creationInformation.type(),
                creationInformation.initialBalance());
    }

    public List<TransactionJournalCreated> getAllTransactionJournals() {
        return transactionJournals;
    }

    public TransactionJournalCreated getTransactionJournalByUuid(String uuid) throws ObjectNotFoundException {
        return transactionJournals.stream()
                .filter(txJournal -> txJournal.uuid().equals(uuid))
                .findFirst()
                .orElseThrow(ObjectNotFoundException::new);
    }
}
