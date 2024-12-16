package com.example.demo.controller;

import com.example.demo.HeartbeatSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartbeatController {

    @Autowired
    private HeartbeatSensor heartbeat;

    @GetMapping("/heartbeat")
    public int getHeartbeat() {
        return  heartbeat.get();
    }

}
