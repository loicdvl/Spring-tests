package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.CustomArgsProvider;
import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class OwnerTest implements ModelTests {

    @Test
    void dependantAssertions() {
        Owner owner = new Owner(1L, "Joe", "Bucks");
        owner.setCity("Key West");
        owner.setTelephone("1231231234");

        assertAll("Properties set",
                () -> assertAll("Person Properties",
                        () -> assertEquals("Joe", owner.getFirstName(), "First Name did not match"),
                        () -> assertEquals("Bucks", owner.getLastName(), "Last Name did not match")),
                () -> assertAll("Owner Properties",
                        () -> assertEquals("Key West", owner.getCity(), "City did not match"),
                        () -> assertEquals("1231231234", owner.getTelephone(), "Phone number did not match")
                ));

        // Hamcrest assertions
        assertThat(owner.getCity(), is("Key West"));
    }

    @DisplayName("ValueSource Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {argumentsWithNames}")
    @ValueSource(strings = {"Spring", "Framework", "Guru"})
    void testValueSource(String val) {
        System.out.println(val);
    }

    @ParameterizedTest(name = "{displayName} - [{index}] {argumentsWithNames}")
    @DisplayName("Enum Source Test")
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType ownerType) {
        System.out.println(ownerType);
    }

    @ParameterizedTest(name = "{displayName} - [{index}] {argumentsWithNames}")
    @DisplayName("CSV Input Test")
    @CsvSource({
            "FL, 1, 1",
            "OH, 2, 2",
            "MI, 3, 3",
    })
    void csvInputTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " = " + val1 + ":" + val2);
    }

    @ParameterizedTest(name = "{displayName} - [{index}] {argumentsWithNames}")
    @DisplayName("CSV File Input Test")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    void csvFileInputTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " = " + val1 + ":" + val2);
    }

    @ParameterizedTest(name = "{displayName} - [{index}] {argumentsWithNames}")
    @DisplayName("CSV File Input Test")
    @MethodSource("getArgs")
    void fromMethodTest(String stateName, int val1, int val2){
        System.out.println(stateName + " = " + val1 + ":" + val2);
    }

    static Stream<Arguments> getArgs() {
        return Stream.of(
                Arguments.of("FL", 1, 1),
                Arguments.of("OH", 2, 1),
                Arguments.of("MI", 1, 3));
    }

    @ParameterizedTest(name = "{displayName} - [{index}] {argumentsWithNames}")
    @DisplayName("CSV File Input Test")
    @ArgumentsSource(CustomArgsProvider.class)
    void fromCustomProviderTest(String stateName, int val1, int val2){
        System.out.println(stateName + " = " + val1 + ":" + val2);
    }
}