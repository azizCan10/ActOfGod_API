package edu.kocaeli.actofgod_api.controller;

import edu.kocaeli.actofgod_api.dto.LocationDto;
import edu.kocaeli.actofgod_api.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/location")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @PostMapping
    public LocationDto add(@RequestBody LocationDto locationDto) {
        return locationService.add(locationDto);
    }

    @GetMapping("/{id}")
    public LocationDto getById(@PathVariable Long id) {
        return locationService.getById(id);
    }

    @GetMapping
    public List<LocationDto> getAll() {
        return locationService.getAll();
    }

    @PutMapping("/{id}")
    public LocationDto update(@PathVariable Long id, @RequestBody LocationDto dto) {
        return locationService.update(id,dto);
    }
}
