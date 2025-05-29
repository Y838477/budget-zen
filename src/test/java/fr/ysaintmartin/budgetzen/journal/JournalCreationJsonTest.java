package fr.ysaintmartin.budgetzen.journal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest()
class JournalCreationJsonTest {

    @Autowired
    public JacksonTester<JournalCreation> json;

    @Test
    void serialisationIsValid() throws IOException {
        JournalCreation journalRequest = new JournalCreation("compte principal", "JOINT_ACCOUNT", 539.00);
        JsonContent<JournalCreation> jsonRequest = json.write(journalRequest);
        assertThat(jsonRequest).isStrictlyEqualToJson("journalRequest.json");
        assertThat(jsonRequest).extractingJsonPathValue("@.journal_type").isNotEqualTo(JournalType.CURRENT_ACCOUNT.name());
    }

    @Test
    void deserializationIsValid() throws IOException {
        String expected = """
                    {
                        "journal_title": "economies",
                        "journal_type": "SAVING_ACCOUNT",
                        "initial_balance": 935.00
                    }
                """;
        assertThat(json.parseObject(expected)).isEqualTo(new JournalCreation("economies", "SAVING_ACCOUNT", 935.00));
    }
}
