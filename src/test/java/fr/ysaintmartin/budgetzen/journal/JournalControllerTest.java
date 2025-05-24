package fr.ysaintmartin.budgetzen.journal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class JournalControllerTest {

    @Autowired
    MockMvc mvcRequest;

    @Test
    void createJournal_returnsHttpCreatedAndJsonBody() throws Exception {
        String jsonRequest = """
                {
                    "journal_title": "compte courant",
                    "journal_type": "CURRENT_ACCOUNT",
                    "initial_balance": 913
                }
                """;
        mvcRequest.perform(post("/journals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonRequest));
    }
}
