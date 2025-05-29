package fr.ysaintmartin.budgetzen.journal;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.ysaintmartin.budgetzen.utils.annotation.EnumValue;
import jakarta.validation.constraints.Size;

public record JournalCreation(@JsonProperty("journal_title") @Size(max = 50) String title,
                              @JsonProperty("journal_type")
                              @EnumValue(enumType = JournalType.class,
                                      message = "Journal type is not valid") String type,
                              @JsonProperty("initial_balance") double initialBalance) {
}
