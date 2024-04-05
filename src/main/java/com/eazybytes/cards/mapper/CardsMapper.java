package com.eazybytes.cards.mapper;

import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.entity.Cards;
import org.springframework.stereotype.Component;

@Component
public class CardsMapper {
    public static Cards mapToCards(CardsDto dto, Cards cards) {
        cards.setCardNumber(dto.getCardNumber());
        cards.setCardType(dto.getCardType());
        cards.setMobileNumber(dto.getMobileNumber());
        cards.setAmountUsed(dto.getAmountUsed());
        cards.setTotalLimit(dto.getTotalLimit());
        cards.setAvailableAmount(dto.getAvailableAmount());
        return cards;
    }

    public static CardsDto mapToCardsDto(Cards cards, CardsDto dto) {
        dto.setCardNumber(cards.getCardNumber());
        dto.setCardType(cards.getCardType());
        dto.setAmountUsed(cards.getAmountUsed());
        dto.setAvailableAmount(cards.getAvailableAmount());
        dto.setMobileNumber(cards.getMobileNumber());
        dto.setTotalLimit(cards.getTotalLimit());
        return dto;
    }
}
