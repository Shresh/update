package com.example.assignment.api;

import com.example.assignment.config.resources.CustomMessageSource;
import com.example.assignment.pojo.BatteryFilterPojo;
import com.example.assignment.pojo.BatteryPojo;
import com.example.assignment.service.BatteryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/battery/")
@Api(tags = "Battery Related |")
public class BatteryController extends BaseController {
    private final BatteryService batteryService;
    private final CustomMessageSource customMessageSource;


    @PostMapping("save")
    @ApiOperation(value = "Saving Battery | PostBattery")
    public ResponseEntity<?> postBattery(@RequestBody BatteryPojo batteryPojo) {
        try {
            String msg = batteryPojo.getId() == null ? "success.save" : "success.update";
            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse(customMessageSource.get(msg, customMessageSource.get("battery")), batteryService.save(batteryPojo)));
        } catch (Exception e) {
            String msg = batteryPojo.getId() == null ? "error.save" : "error.update";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse(customMessageSource.get(msg, customMessageSource.get("battery")), e.getMessage()));
        }
    }


    @PostMapping("getbatteryfromrange")
    public ResponseEntity<?> getBatteryFromRange(@RequestBody BatteryFilterPojo batteryFilterPojo) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse(customMessageSource.get("fetched.list", customMessageSource.get("battery")), batteryService.getBatteriesBetweenPostCodeRange(batteryFilterPojo)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse(customMessageSource.get("error.fetched.list", customMessageSource.get("battery")), e.getMessage()));
        }
    }
}
