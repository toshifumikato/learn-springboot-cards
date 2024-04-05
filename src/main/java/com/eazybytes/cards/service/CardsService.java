package com.eazybytes.cards.service;

import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.entity.Cards;
import com.eazybytes.cards.exception.CardsAlreadyExistsException;
import com.eazybytes.cards.mapper.CardsMapper;
import com.eazybytes.cards.repository.CardsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardsService implements ICardService {
    private CardsRepository cardsRepository;

    public CardsService(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    @Override
    public void createCards(CardsDto dto) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(dto.getMobileNumber());
        if (optionalCards.isPresent()) {
            throw new CardsAlreadyExistsException("Card already exist with this phone number - " + dto.getMobileNumber());
        }
        Cards cards = new Cards();
        CardsMapper.mapToCards(dto, cards);
        cardsRepository.save(cards);
    }

    @Override
    public CardsDto getCardsById(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        CardsDto dto = null;
        if (optionalCards.isPresent()) {
            dto = new CardsDto();
            CardsMapper.mapToCardsDto(optionalCards.get(), dto);
        }
        return dto;
    }
}
