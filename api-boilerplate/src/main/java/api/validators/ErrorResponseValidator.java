package api.validators;

import api.mappings.ErrorResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ErrorResponseValidator {
    public static void assertErrorResponse(ErrorResponse actual, ErrorResponse expected) {assertThat("Timestamp is not the expected", actual.getTimestamp(), is(lessThanOrEqualTo(expected.getTimestamp())));
        assertThat("Status is not the expected", actual.getStatus(), is(expected.getStatus()));
        assertThat("Error is not the expected", actual.getError(), is(expected.getError()));
        assertThat("Message is not the expected", actual.getMessage(), is(expected.getMessage()));
        assertThat("Path is not the expected", actual.getPath(), is(expected.getPath()));
    }
}
