package fr.ysaintmartin.budgetzen.journal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static fr.ysaintmartin.budgetzen.utils.constants.JournalErrorMessages.JOURNAL_TYPE_IS_NOT_VALID;
import static fr.ysaintmartin.budgetzen.utils.constants.JournalErrorMessages.TITLE_IS_TOO_LONG;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = JournalController.class)
class JournalControllerTest {

    @Autowired
    MockMvc mvcRequest;

    @MockitoBean
    JournalService journalService;

    private static JsonNode requestsNodes;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void init() throws IOException {
        File jsonRequests = new File("/Users/ystm/Projets/java/spring/budget-zen/src/test/resources/fr/ysaintmartin/budgetzen/journal/journalRequestsDictionnary.json");
        requestsNodes = new ObjectMapper().readTree(jsonRequests);
    }

    @Test
    void createJournal_returns_HttpCreatedAndJsonBody() throws Exception {
        String validJsonRequest = String.valueOf(requestsNodes.get("creationIsValid"));
        String jsonResponse = """
                {"journal_uuid":"uuid","journal_title":"compte courant","journal_type":"CURRENT_ACCOUNT","journal_balance":913.00}
                """;

        when(journalService.createTransactionJournal(objectMapper.readValue(validJsonRequest, JournalCreation.class)))
                .thenReturn(new TransactionJournalCreated("uuid", "compte courant", "CURRENT_ACCOUNT", 913.00));

        mvcRequest.perform(post("/journals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validJsonRequest))
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonResponse));
    }

    @Test
    void createJournal_returns_InvalidJournalTypeError() throws Exception {
        String jsonRequest = String.valueOf(requestsNodes.get("creationJournalTypeError"));

        mvcRequest.perform(post("/journals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error_message").value(String.format(JOURNAL_TYPE_IS_NOT_VALID, "NEW_ACCOUNT", "type")));
    }

    @Test
    void createJournal_returns_InvalidJournalNameError() throws Exception {
        String jsonRequest = String.valueOf(requestsNodes.get("creationTitleIsTooLongError"));

        mvcRequest.perform(post("/journals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error_message").value(String.format(TITLE_IS_TOO_LONG, "compte courant numero 00112233221100", "title")));

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
        UUID uuidCurrentAccount = UUID.randomUUID();
        UUID uuidSavingAccount = UUID.randomUUID();
        String jsonResponse = """
                    [
                        {
                            "journal_uuid": "%s",
                            "journal_title": "compte courant",
                            "journal_type": "CURRENT_ACCOUNT",
                            "journal_balance": 379.00
                        },
                        {
                            "journal_uuid": "%s",
                            "journal_title": "compte épargne",
                            "journal_type": "SAVING_ACCOUNT",
                            "journal_balance": 973.00
                        }
                    ]
                """.formatted(uuidCurrentAccount, uuidSavingAccount);

        when(journalService.getAllTransactionJournals())
                .thenReturn(List.of(new TransactionJournalCreated(uuidCurrentAccount.toString(), "compte courant", "CURRENT_ACCOUNT", 379.00),
                        new TransactionJournalCreated(uuidSavingAccount.toString(), "compte épargne", "SAVING_ACCOUNT", 973.00)));

        mvcRequest.perform(get("/journals"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));
    }

    @Test
    void getTransactionJournalByUuid_returns_JournalTransactionCreatedInfo() throws Exception {
        String jsonResponse = """
                {
                    "journal_uuid": "uuid",
                    "journal_title": "compte enregistré",
                    "journal_type": "CURRENT_ACCOUNT",
                    "journal_balance": 357.55
                }
                """;

        when(journalService.getTransactionJournalByUuid("uuid"))
                .thenReturn(Optional.of(new TransactionJournalCreated("uuid", "compte enregistré", "CURRENT_ACCOUNT", 357.55)));

        mvcRequest.perform(get("/journals/uuid"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));
    }

    @Test
    void getTransactionJournalByUuid_throws_ObjectNotFoundException() throws Exception {
        mvcRequest.perform(get("/journals/id"))
                .andExpect(status().isNotFound());
    }
}
