package fr.ysaintmartin.budgetzen.journal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static fr.ysaintmartin.budgetzen.utils.constants.JournalErrorMessages.JOURNAL_TYPE_IS_NOT_VALID;
import static fr.ysaintmartin.budgetzen.utils.constants.JournalErrorMessages.TITLE_IS_TOO_LONG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = JournalController.class)
class JournalControllerTest {

    @Autowired
    MockMvc mvcRequest;

    @MockitoBean
    JournalService journalService;

    @Test
    void createJournal_returns_HttpCreatedAndJsonBody() throws Exception {
        String jsonRequest = """
                {
                    "journal_title": "compte courant",
                    "journal_type": "CURRENT_ACCOUNT",
                    "initial_balance": 913.00
                }
                """;
        String jsonResponse = """
                {
                    "journal_uuid": "uuid",
                    "journal_title": "compte courant",
                    "journal_type": "CURRENT_ACCOUNT",
                    "journal_balance": 913.00
                }
                """;

        when(journalService.createTransactionJournal(new JournalCreation("compte courant", "CURRENT_ACCOUNT", 913.00)))
                .thenReturn(new TransactionJournalCreated("uuid", "compte courant", "CURRENT_ACCOUNT", 913.00));

        mvcRequest.perform(post("/journals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(result -> result.getResponse().setContentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonResponse));
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

    @Test
    void createJournal_returns_CreatedWhenInitialBalanceIsString() throws Exception {
        String jsonRequest = """
                {
                    "journal_title": "compte joint",
                    "journal_type": "JOINT_ACCOUNT",
                    "initial_balance": "591.00"
                }
                """;
        mvcRequest.perform(post("/journals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());
    }

    @Test
    void getTransactionJournals_returns_ListOfTransactionJournalCreated() throws Exception {
        String jsonResponse = """
                    [
                        {
                            "journal_uuid": "uuid",
                            "journal_title": "compte courant",
                            "journal_type": "CURRENT_ACCOUNT",
                            "journal_balance": 379.00
                        },
                        {
                            "journal_uuid": "uuid",
                            "journal_title": "compte épargne",
                            "journal_type": "SAVING_ACCOUNT",
                            "journal_balance": 973.00
                        }
                    ]
                """;

        when(journalService.getAllTransactionJournals())
                .thenReturn(List.of(new TransactionJournalCreated("uuid", "compte courant", "CURRENT_ACCOUNT", 379.00),
                        new TransactionJournalCreated("uuid", "compte épargne", "SAVING_ACCOUNT", 973.00)));

        mvcRequest.perform(get("/journals"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));
    }
}
