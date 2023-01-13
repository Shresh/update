package com.example.assignment.service.impl;

import com.example.assignment.config.resources.CustomMessageSource;
import com.example.assignment.model.Battery;
import com.example.assignment.pojo.BatteryFilterPojo;
import com.example.assignment.pojo.BatteryPojo;
import com.example.assignment.repository.BatteryRepo;
import com.example.assignment.service.BatteryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BatteryServiceImpl implements BatteryService {
    private final BatteryRepo batteryRepo;

    private final CustomMessageSource customMessageSource;

    @Override
    @Transactional
    public BatteryPojo save(BatteryPojo batteryPojo) throws Exception {
        Battery battery;
        if (batteryPojo.getId() != null) {
            battery = checkIt(batteryPojo.getId());
        } else {
            battery = new Battery();
        }
        battery.setName(batteryPojo.getName());
        battery.setPostcode(batteryPojo.getPostcode());
        battery.setCapacity(batteryPojo.getCapacity());

        Battery battery1 = batteryRepo.save(battery);
        batteryPojo.setId(battery1.getId());
        return batteryPojo;
    }

    @Override
    public Battery checkIt(String id) throws Exception {
        Optional<Battery> battery = batteryRepo.findById(id);
        if(!battery.isPresent()){
            throw new Exception(customMessageSource.get("not.found",customMessageSource.get("battery")));
        }
        return battery.get();
    }

    @Override
    public Map<String, Object> getBatteriesBetweenPostCodeRange(BatteryFilterPojo batteryFilterPojo) {
        List<Map<String, Object>> getRageList = batteryRepo.getBatteriesBetweenPostCodeRange(batteryFilterPojo.getFrom(), batteryFilterPojo.getTo());
        Map<String, Object> map = new HashMap<>();
        List<String> batteryList = getRageList.stream().map(m -> {
            return m.get("name").toString();
        }).sorted().collect(Collectors.toList());
        map.put("batteryList", batteryList);
        IntSummaryStatistics statistics = getRageList.stream().mapToInt(m -> {
            return (Integer) m.get("capacity");
        }).summaryStatistics();
        map.put("totalCapacity", statistics.getSum());
        map.put("averageCapacity", statistics.getAverage());
        map.put("minCapacity", statistics.getMin());
        map.put("maxCapacity", statistics.getMax());
        map.put("countCapacity", statistics.getCount());
        return map;
    }

}
