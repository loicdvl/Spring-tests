package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.*;

@Tag("model")
public interface ModelTests {

    @DisplayName("")
    @BeforeEach
    default void beforeEach(TestInfo testInfo) {
        System.out.println("Running test - " + testInfo.getDisplayName());
    }
}
