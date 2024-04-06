package com.eazybytes.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.PositiveOrZero;

public class CardsDto {
    private Long cardId;
    private String mobileNumber;
    private String cardNumber;
    private String cardType;
    @PositiveOrZero(message = "Total limit must be zero or greater than zero")
    @Schema(
            description = "Total limit amount", example = "500000"
    )
    private Long totalLimit;
    @PositiveOrZero
    private Long amountUsed;
    @PositiveOrZero
    private Long availableAmount;

    public CardsDto() {
    }

    public CardsDto(Long cardId, String mobileNumber, String cardNumber, String cardType, Long totalLimit, Long amountUsed, Long availableAmount) {
        this.cardId = cardId;
        this.mobileNumber = mobileNumber;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.totalLimit = totalLimit;
        this.amountUsed = amountUsed;
        this.availableAmount = availableAmount;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Long getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(Long totalLimit) {
        this.totalLimit = totalLimit;
    }

    public Long getAmountUsed() {
        return amountUsed;
    }

    public void setAmountUsed(Long amountUsed) {
        this.amountUsed = amountUsed;
    }

    public Long getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Long availableAmount) {
        this.availableAmount = availableAmount;
    }
}
