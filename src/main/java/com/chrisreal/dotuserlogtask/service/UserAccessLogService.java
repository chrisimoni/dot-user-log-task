package com.chrisreal.dotuserlogtask.service;

import com.chrisreal.dotuserlogtask.model.UserAccessLog;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;

public interface UserAccessLogService {
    void execute(String[] args) throws FileNotFoundException;
    boolean loadFileToDb(Path filePath) throws FileNotFoundException;

    List<UserAccessLog> extractFileContents(Path filePath) throws FileNotFoundException;

    List<String> checkLimitAndBlockIPs(String start, int limit, String duration);
}
