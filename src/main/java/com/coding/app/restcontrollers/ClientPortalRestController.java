package com.coding.app.restcontrollers;

import com.coding.app.dto.ReservationRequest;
import com.coding.app.services.ClientPortalService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coding.app.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ClientPortalRestController {

    private final ClientPortalService clientPortalService;

    @GetMapping("/cars")
    public HttpEntity<?> getCars() {
        return ResponseEntity.ok(clientPortalService.getAllCars());
    }

    @GetMapping("/cars/{carId}")
    public HttpEntity<?> getCarById(@PathVariable final Long carId) throws NotFoundException {
        return ResponseEntity.ok(clientPortalService.getCarById(carId));
    }

	@PostMapping("/reserve")
	public HttpEntity<?> reserve(@RequestBody ReservationRequest reservationRequest) throws NotFoundException {
        // Build reservation service
        return ResponseEntity.accepted().build();
	}
}
