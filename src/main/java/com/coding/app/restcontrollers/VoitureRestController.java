package com.coding.app.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import com.coding.app.exceptions.NotFoundException;
import com.coding.app.models.Reservation;
import com.coding.app.models.User;
import com.coding.app.models.Voiture;
import com.coding.app.models.key.KeyReservation;
import com.coding.app.repository.ReservationRepository;
import com.coding.app.repository.VoitureRepository;

class VoitureRequest {
	private Long idVoiture;
	private Long delai;

	public Long getIdVoiture() {
		return idVoiture;
	}

	public void setIdVoiture(Long idVoiture) {
		this.idVoiture = idVoiture;
	}

	public Long getDelai() {
		return delai;
	}

	public void setDelai(Long delai) {
		this.delai = delai;
	}

}

@RestController
@RequestMapping(name = "/api/voitures", produces = MediaType.APPLICATION_JSON_VALUE, value = "/api/voitures")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VoitureRestController {

	@Autowired
	private VoitureRepository voitureRepository;

	@Autowired
	private ReservationRepository reservationRepository;
	
	@GetMapping
	public HttpEntity<?> getVoitures() {
		List<Voiture> voitures = voitureRepository.findAll();
		return ResponseEntity.ok(voitures);
	}

	@PostMapping
	public HttpEntity<?> addVoiture(@RequestBody VoitureRequest voitureRequest) throws NotFoundException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			System.out.println("Traitement");
			Voiture voiture = voitureRepository.findById(voitureRequest.getIdVoiture())
					.orElseThrow(() -> new NotFoundException("Voiture not found"));
			User user = (User) auth.getPrincipal();
			Reservation reservation = new Reservation();
			System.out.println(voitureRequest.getDelai());
			reservation.setDelai(voitureRequest.getDelai());
			reservation.setUser(user);
			reservation.setVoiture(voiture);
			reservation.setId(new KeyReservation(user.getId(), voiture.getId()));
			reservationRepository.save(reservation);
		}
		return ResponseEntity.accepted().build();
	}
}
