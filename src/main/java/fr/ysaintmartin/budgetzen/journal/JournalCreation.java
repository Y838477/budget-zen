package fr.ysaintmartin.budgetzen.journal;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.ysaintmartin.budgetzen.utils.annotation.EnumValue;
import jakarta.validation.constraints.Size;

import static fr.ysaintmartin.budgetzen.utils.constants.JournalErrorMessages.JOURNAL_TYPE_IS_NOT_VALID;
import static fr.ysaintmartin.budgetzen.utils.constants.JournalErrorMessages.TITLE_IS_TOO_LONG;

public record JournalCreation(@JsonProperty("journal_title")
                              @Size(max = 50, message = TITLE_IS_TOO_LONG) String title,
                              @JsonProperty("journal_type")
                              @EnumValue(enumType = JournalType.class, message = JOURNAL_TYPE_IS_NOT_VALID) String type,
                              @JsonProperty("initial_balance") double initialBalance) {
}
