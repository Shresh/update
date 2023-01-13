package com.example.assignment.service;

import com.example.assignment.model.Battery;
import com.example.assignment.pojo.BatteryFilterPojo;
import com.example.assignment.pojo.BatteryPojo;

import java.util.List;
import java.util.Map;

public interface BatteryService {
    BatteryPojo save(BatteryPojo batteryPojo) throws Exception;

    Battery checkIt(String id) throws Exception;

    Map<String, Object> getBatteriesBetweenPostCodeRange(BatteryFilterPojo batteryFilterPojo);

}
