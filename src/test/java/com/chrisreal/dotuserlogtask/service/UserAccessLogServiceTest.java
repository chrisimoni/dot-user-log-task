package com.chrisreal.dotuserlogtask.service;

import com.chrisreal.dotuserlogtask.model.UserAccessLog;
import com.chrisreal.dotuserlogtask.repository.BlockedIpRepository;
import com.chrisreal.dotuserlogtask.repository.UserAccessLogRepository;
import com.chrisreal.dotuserlogtask.service.impl.UserAccessLogServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserAccessLogServiceTest {
    @Mock
    UserAccessLogRepository userAccessLogRepository;

    @Mock
    BlockedIpRepository blockedIpRepository;
    @InjectMocks
    UserAccessLogServiceImpl userAccessLogService;

    Path filePath = null;

    @BeforeEach
    void setUp() throws Exception {
        filePath = Paths.get(getClass().getClassLoader().getResource("user_access_test.txt").toURI());
    }

    @Test
    void testExtractFileContents() throws FileNotFoundException {
        List<UserAccessLog> extractedContents = userAccessLogService.extractFileContents(filePath);
        assertThat(extractedContents.size()).isGreaterThan(0);
    }

    @Test
    void testLoadFileToDb() throws FileNotFoundException {
        List<UserAccessLog> extractedContents = userAccessLogService.extractFileContents(filePath);

        when(userAccessLogRepository.saveAll(extractedContents)).thenReturn(extractedContents);
        boolean result = userAccessLogService.loadFileToDb(filePath);

        assertThat(extractedContents.size()).isGreaterThan(0);

    }

    @Test
    void testcheckLimitAndBlockIPs() {
        String start = "2022-01-01.00:00:11";
        String duration = "hourly";
        int limit = 3;

        List<String> listOfIPS = new ArrayList<>();
        listOfIPS.add("192.168.1.2");
        listOfIPS.add("192.168.90.158");
        listOfIPS.add("192.168.170.93");

        when(userAccessLogRepository.getAllIpByDateRange(any(LocalDateTime.class), any(LocalDateTime.class), anyInt()))
                .thenReturn(listOfIPS);
        List<String> result = userAccessLogService.checkLimitAndBlockIPs(start, limit, duration);

        assertThat(result.size()).isEqualTo(listOfIPS.size());
    }
}
