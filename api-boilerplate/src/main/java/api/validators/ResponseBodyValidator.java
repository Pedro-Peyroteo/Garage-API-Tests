package api.validators;
import retrofit2.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Class to facilitate response body validation
 */
public class ResponseBodyValidator {
    /**
     * Checks if the response body has content
     * @param response API response
     */
    public static void assertBodyNotNull(Response response) {
        assertThat("Response body should not be null", response.body(), notNullValue());
    }

    /**
     * Checks if response body is null
     * Used for negative tests
     * @param response API response
     */
    public static void assertBodyNull(Response response) {
        assertThat("Response body should be null", response.body(), nullValue());
    }

    public static void assertId(Integer clientId, Integer exceptedId){
        assertThat("Id is not the expected", clientId, is(exceptedId));
    }
}
