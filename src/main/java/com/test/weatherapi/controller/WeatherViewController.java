package com.test.weatherapi.controller;

import com.test.weatherapi.model.Weather;
import com.test.weatherapi.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherViewController {

    private final WeatherService weatherService;

    public WeatherViewController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // Web UI: Render weather list
    @GetMapping("/weather/view")
    public String viewWeatherList(Model model) {
        model.addAttribute("weatherList", weatherService.getAllWeather());
        model.addAttribute("newWeather", new Weather());
        return "weather"; // 렌더링할 Thymeleaf 템플릿 이름
    }

    // Web UI: Add new weather
    @PostMapping("/weather/view")
    public String addWeather(@ModelAttribute("newWeather") Weather newWeather) {
        weatherService.createWeather(newWeather);
        return "redirect:/weather/view";
    }
    
    @PostMapping("/weather/view/update/{id}")
    public String updateWeather(
        @PathVariable Long id,
        @RequestParam("date") String date,
        @RequestParam("region") String region,
        @RequestParam("weather") String weather,
        @RequestParam("temperature") Double temperature
    ) {
        Weather weatherDetails = new Weather();
        weatherDetails.setDate(date);
        weatherDetails.setRegion(region);
        weatherDetails.setWeather(weather);
        weatherDetails.setTemperature(temperature);

        weatherService.updateWeather(id, weatherDetails);
        return "redirect:/weather/view";
    }

    // Web UI: Delete weather
    @PostMapping("/weather/view/delete/{id}")
    public String deleteWeather(@PathVariable Long id) {
        weatherService.deleteWeather(id);
        return "redirect:/weather/view";
    }
}
