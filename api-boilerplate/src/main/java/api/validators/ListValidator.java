package api.validators;

import org.hamcrest.Matcher;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListValidator {
    public static void assertListNotEmpty(List<?> list){
        assertThat("List shouldn't be empty", list, not(empty()));
        assertThat("List shouldn't be null", list, notNullValue());
    }

    public static void assertListHasSize(List<?> list, Integer size){
        assertThat("List shouldn't be null", list, notNullValue());
        assertThat("List size different than excpected", list, hasSize(size));
    }

    public static void assertListHasSize(List<?> list, Matcher<? super Integer> matcher){
        assertThat("List shouldn't be null", list, notNullValue());
        assertThat("List size different than excpected", list, hasSize(matcher));
    }
}
