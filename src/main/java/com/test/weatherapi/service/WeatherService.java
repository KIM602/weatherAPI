package com.test.weatherapi.service;

import com.test.weatherapi.model.Weather;
import com.test.weatherapi.repository.WeatherRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    // 모든 날씨 데이터 조회
    public List<Weather> getAllWeather() {
        return weatherRepository.findAll();
    }

    // ID로 날씨 데이터 조회
    public Optional<Weather> getWeatherById(Long id) {
        return weatherRepository.findById(id);
    }

    // 새 날씨 데이터 생성
    public Weather createWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    // 기존 날씨 데이터 업데이트
    public Weather updateWeather(Long id, Weather weatherDetails) {
        return weatherRepository.findById(id)
                .map(weather -> {
                    weather.setDate(weatherDetails.getDate());
                    weather.setRegion(weatherDetails.getRegion());
                    weather.setWeather(weatherDetails.getWeather());
                    weather.setTemperature(weatherDetails.getTemperature());
                    return weatherRepository.save(weather);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Weather data not found for ID: " + id));
    }
    
    // ID로 날씨 데이터 삭제
    public boolean deleteWeather(Long id) {
        if (weatherRepository.existsById(id)) {
            weatherRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
