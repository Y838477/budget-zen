package fr.ysaintmartin.budgetzen.journal;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journals")
public class JournalController {

    private final JournalService journalService;

    @Autowired
    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionJournalCreated>> getTransactionJournals() {
        return ResponseEntity.ok(journalService.getAllTransactionJournals());
    }

    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionJournalCreated> getTransactionJournalByUuid(@PathVariable String uuid) {
        return ResponseEntity.of(journalService.getTransactionJournalByUuid(uuid));
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionJournalCreated> createJournal(@Valid @RequestBody JournalCreation journalRequest) {
        return new ResponseEntity<>(journalService.createTransactionJournal(journalRequest), HttpStatus.CREATED);
    }
}
