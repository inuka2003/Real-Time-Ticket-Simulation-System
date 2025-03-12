package com.coursework.w2052208.service;

import com.coursework.w2052208.entity.Log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {
    private final List<Log> logs = new ArrayList<>();

    public List<Log> getLogs() {
        return logs;
    }

    public void addLog(Log log) {
        logs.add(log);
    }
}
