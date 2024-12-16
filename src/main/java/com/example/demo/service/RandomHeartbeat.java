package com.example.demo.service;

import com.example.demo.HeartbeatSensor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomHeartbeat implements HeartbeatSensor {

    Random random = new Random();

    @Override
    public int get() {
        return random.nextInt(40,230);
    }
}
