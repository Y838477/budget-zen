package fr.ysaintmartin.budgetzen.journal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static fr.ysaintmartin.budgetzen.utils.constants.JournalErrorMessages.JOURNAL_TYPE_IS_NOT_VALID;
import static fr.ysaintmartin.budgetzen.utils.constants.JournalErrorMessages.TITLE_IS_TOO_LONG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = JournalController.class)
class JournalControllerTest {

    @Autowired
    MockMvc mvcRequest;

    @Test
    void createJournal_returns_HttpCreatedAndJsonBody() throws Exception {
        String jsonRequest = """
                {
                    "journal_title": "compte courant",
                    "journal_type": "CURRENT_ACCOUNT",
                    "initial_balance": 913.00
                }
                """;
        mvcRequest.perform(post("/journals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonRequest));
    }

    @Test
    void createJournal_returns_InvalidJournalTypeError() throws Exception {
        String jsonRequest = """
                {
                    "journal_title": "compte joint",
                    "journal_type": "NEW_ACCOUNT",
                    "initial_balance": 753.00
                }
                """;
        MvcResult response = mvcRequest.perform(post("/journals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertThat(response.getResponse().getContentAsString())
                .isEqualTo(String.format(JOURNAL_TYPE_IS_NOT_VALID, "NEW_ACCOUNT", "type"));
    }

    @Test
    void createJournal_returns_InvalidJournalNameError() throws Exception {
        String jsonRequest = """
                {
                    "journal_title": "compte joint / compte joint compte / joint compte joint",
                    "journal_type": "JOINT_ACCOUNT",
                    "initial_balance": 591.00
                }
                """;
        MvcResult response = mvcRequest.perform(post("/journals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertThat(response.getResponse().getContentAsString())
                .isEqualTo(TITLE_IS_TOO_LONG, "compte joint / compte joint compte / joint compte joint", "title");
    }
}
