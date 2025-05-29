package fr.ysaintmartin.budgetzen.utils.constants;

public class JournalErrorMessages {

    public static final String REQUEST_BODY_ERROR = "Request body error: value '%s' for field '%s'";
    public static final String TITLE_IS_TOO_LONG = REQUEST_BODY_ERROR + " " + "is too long";
    public static final String JOURNAL_TYPE_IS_NOT_VALID = REQUEST_BODY_ERROR + " " + "is not valid";
    private JournalErrorMessages() {
    }
}
