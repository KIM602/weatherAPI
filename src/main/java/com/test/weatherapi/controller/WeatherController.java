package com.test.weatherapi.controller;

import com.test.weatherapi.model.Weather;
import com.test.weatherapi.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/weather")
@Validated // 유효성 검증 활성화
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public List<Weather> getAllWeather() {
        return weatherService.getAllWeather();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Weather> getWeatherById(@PathVariable("id") Long id) {
        return weatherService.getWeatherById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Weather data not found for ID: " + id));
    }

    @PostMapping
    public ResponseEntity<Weather> createWeather(@Valid @RequestBody Weather weather) {
        Weather createdWeather = weatherService.createWeather(weather);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWeather);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Weather> updateWeather(@PathVariable("id") Long id, @Valid @RequestBody Weather weatherDetails) {
        return ResponseEntity.of(Optional.ofNullable(weatherService.updateWeather(id, weatherDetails)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeather(@PathVariable("id") Long id) {
        if (weatherService.deleteWeather(id)) {
            return ResponseEntity.noContent().build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Weather data not found for ID: " + id);
        }
    }
}
