package fr.ysaintmartin.budgetzen.journal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransactionJournalCreated(@JsonProperty("journal_uuid") String uuid,
                                        @JsonProperty("journal_title") String title,
                                        @JsonProperty("journal_type") String type,
                                        @JsonProperty("journal_balance") double balance) {
}
