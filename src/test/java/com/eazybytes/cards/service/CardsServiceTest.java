package com.eazybytes.cards.service;

import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.entity.Cards;
import com.eazybytes.cards.exception.CardsAlreadyExistsException;
import com.eazybytes.cards.repository.CardsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class CardsServiceTest {

    CardsService classUnderTest;
    CardsDto dto1;
    Cards cards1;

    @BeforeEach
    public void setUp() {
        dto1 = new CardsDto(
                1234567890L,
                "0901234567",
                "1111222233334444",
                "DEBIT",
                1000000L,
                0L,
                1000000L
        );

        cards1 = new Cards(
                1234567890L,
                "0901234567",
                "1111222233334444",
                "DEBIT",
                1000000L,
                0L,
                1000000L
        );
    }

    @Test
    public void testCreateCards() {
        CardsRepository mockRepo = mock(CardsRepository.class);
        when(mockRepo.save(cards1)).thenReturn(cards1);
        classUnderTest = new CardsService(mockRepo);
        classUnderTest.createCards(dto1);
//        verify(mockRepo).save(cards1);
    }

    @Test
    public void testCreateCards_AlreadyExists() {
        CardsRepository mockRepo = mock(CardsRepository.class);
        when(mockRepo.findByMobileNumber(dto1.getMobileNumber())).thenReturn(Optional.empty());
        when(mockRepo.save(cards1)).thenReturn(cards1);
        classUnderTest = new CardsService(mockRepo);
        classUnderTest.createCards(dto1);

        when(mockRepo.findByMobileNumber(dto1.getMobileNumber())).thenReturn(Optional.of(cards1));
        try {
            classUnderTest.createCards(dto1);
            fail();
        } catch (CardsAlreadyExistsException e) {

        }
    }
}
