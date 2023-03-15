package com.chrisreal.dotuserlogtask.config;

import com.chrisreal.dotuserlogtask.service.UserAccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final UserAccessLogService userAccessLogService;

    @Override
    public void run(String... args) throws Exception {
        userAccessLogService.execute(args);

    }
}
