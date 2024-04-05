package com.eazybytes.cards.service;

import com.eazybytes.cards.dto.CardsDto;

public interface ICardService {
    void createCards(CardsDto dto);
    CardsDto getCardsById(String mobileNumber);
}
