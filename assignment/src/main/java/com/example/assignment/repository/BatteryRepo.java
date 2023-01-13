package com.example.assignment.repository;

import com.example.assignment.model.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BatteryRepo extends JpaRepository<Battery, String> {
    @Query(value = "select * from battery b where cast(b.postcode as int) between ?1 and ?2", nativeQuery = true)
    List<Map<String, Object>> getBatteriesBetweenPostCodeRange(int from, int to);

    Optional<Battery> findByName(String name);
}
