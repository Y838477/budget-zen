package fr.ysaintmartin.budgetzen.journal;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record InvalidTransactionJournalRequest(@JsonProperty("received_at") Instant dateTime,
                                               @JsonProperty("http_code") int httpCode,
                                               @JsonProperty("http_status") String httpStatus,
                                               @JsonProperty("error_message") String errorMessage) {
}
