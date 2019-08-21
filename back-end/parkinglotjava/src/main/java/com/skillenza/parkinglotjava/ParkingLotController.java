package com.skillenza.parkinglotjava;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ParkingLotController {

	@Autowired
	ParkingLotRepository parkingLotRepository;

	@PostMapping("/parkings")
	public ResponseEntity<ParkingLot> saveParkingLot(@RequestBody ParkingLot parkingLot) {
		parkingLot.setCreatedAt(new Date());
		parkingLot.setUpdatedAt(new Date());
		parkingLotRepository.save(parkingLot);
		return new ResponseEntity<>(parkingLot, HttpStatus.CREATED);
	}

	@GetMapping("/parkings")
	public List<ParkingLot> getParkingList() {
		List<ParkingLot> parkinglist = parkingLotRepository.findAll();
		return parkinglist;

	}
	
	@GetMapping("/parkings/{parkingId}")
	public Optional<ParkingLot> getPakring(@PathVariable("parkingId") Long id) {
		Optional<ParkingLot> parkingObj = parkingLotRepository.findById(id);
		return parkingObj;

	}
}
