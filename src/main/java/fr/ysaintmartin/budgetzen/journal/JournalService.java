package fr.ysaintmartin.budgetzen.journal;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JournalService {

    public TransactionJournalCreated createTransactionJournal(JournalCreation creationInformation) {
        return new TransactionJournalCreated(
                "uuid",
                creationInformation.title(),
                creationInformation.type(),
                creationInformation.initialBalance());
    }

    public List<TransactionJournalCreated> getAllTransactionJournals() {
        List<TransactionJournalCreated> transactionJournalCreatedList = new ArrayList<>();
        transactionJournalCreatedList.add(new TransactionJournalCreated("uuid", "compte courant", "CURRENT_ACCOUNT", 513.00));
        transactionJournalCreatedList.add(new TransactionJournalCreated("uuid", "compte épargne", "SAVING_ACCOUNT", 315.00));
        return transactionJournalCreatedList;
    }
}
