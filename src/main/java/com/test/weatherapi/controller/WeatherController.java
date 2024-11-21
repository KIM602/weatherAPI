package com.test.weatherapi.controller;

import com.test.weatherapi.model.Weather;
import com.test.weatherapi.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/weather")
@Validated // 유효성 검증 활성화
@Controller
public class WeatherController {

    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public List<Weather> getAllWeather() {
        logger.info("Fetching all weather data.");
        return weatherService.getAllWeather();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Weather> getWeatherById(@PathVariable("id") Long id) {
        logger.info("Fetching weather data for ID: {}", id);
        return weatherService.getWeatherById(id)
                .map(weather -> {
                    logger.info("Weather data found for ID: {}", id);
                    return ResponseEntity.ok(weather);
                })
                .orElseThrow(() -> {
                    logger.warn("Weather data not found for ID: {}", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Weather data not found for ID: " + id);
                });
    }

    @PostMapping
    public ResponseEntity<Weather> createWeather(@Valid @RequestBody Weather weather) {
        logger.info("Creating new weather data: {}", weather);
        Weather createdWeather = weatherService.createWeather(weather);
        logger.info("Weather data created successfully: {}", createdWeather);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWeather);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Weather> updateWeather(@PathVariable("id") Long id, @Valid @RequestBody Weather weatherDetails) {
        logger.info("Updating weather data for ID: {}", id);
        Weather updatedWeather = weatherService.updateWeather(id, weatherDetails);
        logger.info("Weather data updated successfully for ID: {}", id);
        return ResponseEntity.ok(updatedWeather);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeather(@PathVariable("id") Long id) {
        logger.info("Attempting to delete weather data for ID: {}", id);
        if (weatherService.deleteWeather(id)) {
            logger.info("Weather data deleted successfully for ID: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Weather data not found for ID: {}", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Weather data not found for ID: " + id);
        }
    }
}
