package fr.ysaintmartin.budgetzen.journal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record JournalCreation(@JsonProperty("journal_title") String title,
                              @JsonProperty("journal_type") JournalType type,
                              @JsonProperty("initial_balance") double initialBalance) {
}
