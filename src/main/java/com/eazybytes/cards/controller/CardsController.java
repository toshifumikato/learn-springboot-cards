package com.eazybytes.cards.controller;

import com.eazybytes.cards.constants.CardsConstants;
import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.dto.ResponseDto;
import com.eazybytes.cards.service.ICardService;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class CardsController {
    ICardService cardService;

    public CardsController(ICardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(path = "/cards", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CardsDto> getCardById(@RequestParam
                                                    @Pattern(regexp = "[0-9]{10}", message = "Mobile number must be 10 digits") String mobileNumber) {
        CardsDto dto = cardService.getCardsById(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping(path = "/cards")
    public ResponseEntity<ResponseDto> createCards(@RequestBody CardsDto dto) {
        cardService.createCards(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }
}
