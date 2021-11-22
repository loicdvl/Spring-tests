package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest implements ModelTests {

    @Test
    void groupedAssertion() {
        // given
        Person person = new Person(1L, "Joe", "Buck");

        // then
        assertAll("Test Props set",
                () -> assertEquals("Joe", person.getFirstName(), "First Name failed"),
                () -> assertEquals("Buck", person.getLastName(), "Last Name failed"));
    }

    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} / {totalRepetitions}")
    @DisplayName("My repeated test")
    void myRepeatedTest() {
        // TODO impl
    }

    @RepeatedTest(5)
    void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + ": " + repetitionInfo.getCurrentRepetition());
    }

    @RepeatedTest(value = 5, name = "{displayName} : {currentRepetition} | {totalRepetitions}")
    @DisplayName("My Assignment repeated test")
    void myAssignmentRepeated() {
        // TODO impl
    }
}