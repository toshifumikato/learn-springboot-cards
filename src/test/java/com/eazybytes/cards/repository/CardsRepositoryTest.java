package com.eazybytes.cards.repository;

import com.eazybytes.cards.CardsApplication;
import com.eazybytes.cards.entity.Cards;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CardsApplication.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CardsRepositoryTest {

    @Autowired
    CardsRepository classUnderTest;

    Cards card;
    Cards card2;

    @BeforeEach
    public void setUp() {
        this.card = new Cards(
                123456789L,
                "0123456789",
                "987654321",
                "Debit",
                50000L,
                0L,
                50000L
        );
        card2 = new Cards(
                123456789L,
                "0908888333",
                "987654321",
                "Credit",
                30000L,
                0L,
                30000L
        );
    }
    @Test
    @Order(1)
    public void testCreate() {
        Assertions.assertEquals(0, classUnderTest.findAll().size());
        Cards result = classUnderTest.save(card);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("0123456789" , result.getMobileNumber());
        Assertions.assertNotNull(result.getCreatedAt());
        Assertions.assertNotNull(result.getCreatedBy());
        Assertions.assertNotNull(result.getUpdatedAt());
        Assertions.assertNotNull(result.getUpdatedBy());
        Assertions.assertEquals(1, classUnderTest.count());
    }

    @Test
    @Order(2)
    public void testFindAll() {
        classUnderTest.save(card2);
        List<Cards> result = classUnderTest.findAll();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    @Order(3)
    public void testFindByMobileNumber() {
        Optional<Cards> result = classUnderTest.findByMobileNumber(card.getMobileNumber());

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(card.getCardType(), result.get().getCardType());
    }

    @Test
    @Order(4)
    public void testUpdate() {
        Optional<Cards> current = classUnderTest.findByMobileNumber(card.getMobileNumber());
        Assertions.assertTrue(current.isPresent());
        current.get().setCardType("UPDATED!");
        Cards result = classUnderTest.save(current.get());

        Assertions.assertEquals("UPDATED!", result.getCardType());
        Assertions.assertEquals(2, classUnderTest.count());
    }

    @Test
    @Order(5)
    public void testDelete() {
        Optional<Cards> current = classUnderTest.findByMobileNumber(card.getMobileNumber());
        Assertions.assertTrue(current.isPresent());
        classUnderTest.delete(current.get());
        List<Cards> list = classUnderTest.findAll();
        Assertions.assertNotNull(list);
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(card2.getMobileNumber(), list.get(0).getMobileNumber());
    }

}
