package com.whiteslave.whiteslaveApp.reportSync;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ReportSyncServiceTest {

    @BeforeAll
    static void init() {
        log.info("Starting test suite for class" + ReportSyncService.class.getName());
    }

    @BeforeEach
    void setUp(TestInfo info) {
        log.info("starting test => " + info.getDisplayName());
    }

    @Test
    @DisplayName("Synchronize checkReport -> save to pdf and save to data base ")
    void syncToPDFAndSaveCheckReport() {
        //given
        String xx = new String("00xx");

        //when

        //then
        assertNotNull(xx);
    }

    static Stream<Arguments> getArgumentsForTests() {
        return Stream.of(
                Arguments.of("PL", 1, 2),
                Arguments.of("DE", 1, 1),
                Arguments.of("FR", 1, 3));
    }
}