package ug.co.absa.collections.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;
import ug.co.absa.collections.domain.enumeration.ProccesingStatus;
import ug.co.absa.collections.domain.enumeration.TransactionStatus;

/**
 * A DTO for the {@link ug.co.absa.collections.domain.NotifyTransaction} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NotifyTransactionDTO implements Serializable {

    private Long id;

    @NotNull
    private UUID recordUniqueIdentifier;

    @NotNull
    private Integer paymentId;

    @NotNull
    private String customerPaymentCode;

    @NotNull
    private Integer serviceCode;

    @NotNull
    private Integer transactionAmount;

    private Integer charges;

    @NotNull
    private String partnerCode;

    @NotNull
    private LocalDate timestamp;

    @NotNull
    private String narration;

    @NotNull
    private Integer currency;

    @NotNull
    private Integer debitAccount;

    @NotNull
    private Integer creditAccount;

    private ProccesingStatus proccessingStatus;

    private TransactionStatus fcrTransactionStatus;

    private String fcrTransactionId;

    private String fcrTransactionReference;

    private String freeField1;

    private String freeField2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getRecordUniqueIdentifier() {
        return recordUniqueIdentifier;
    }

    public void setRecordUniqueIdentifier(UUID recordUniqueIdentifier) {
        this.recordUniqueIdentifier = recordUniqueIdentifier;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getCustomerPaymentCode() {
        return customerPaymentCode;
    }

    public void setCustomerPaymentCode(String customerPaymentCode) {
        this.customerPaymentCode = customerPaymentCode;
    }

    public Integer getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(Integer serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Integer getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Integer getCharges() {
        return charges;
    }

    public void setCharges(Integer charges) {
        this.charges = charges;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public Integer getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(Integer debitAccount) {
        this.debitAccount = debitAccount;
    }

    public Integer getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(Integer creditAccount) {
        this.creditAccount = creditAccount;
    }

    public ProccesingStatus getProccessingStatus() {
        return proccessingStatus;
    }

    public void setProccessingStatus(ProccesingStatus proccessingStatus) {
        this.proccessingStatus = proccessingStatus;
    }

    public TransactionStatus getFcrTransactionStatus() {
        return fcrTransactionStatus;
    }

    public void setFcrTransactionStatus(TransactionStatus fcrTransactionStatus) {
        this.fcrTransactionStatus = fcrTransactionStatus;
    }

    public String getFcrTransactionId() {
        return fcrTransactionId;
    }

    public void setFcrTransactionId(String fcrTransactionId) {
        this.fcrTransactionId = fcrTransactionId;
    }

    public String getFcrTransactionReference() {
        return fcrTransactionReference;
    }

    public void setFcrTransactionReference(String fcrTransactionReference) {
        this.fcrTransactionReference = fcrTransactionReference;
    }

    public String getFreeField1() {
        return freeField1;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return freeField2;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotifyTransactionDTO)) {
            return false;
        }

        NotifyTransactionDTO notifyTransactionDTO = (NotifyTransactionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, notifyTransactionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotifyTransactionDTO{" +
            "id=" + getId() +
            ", recordUniqueIdentifier='" + getRecordUniqueIdentifier() + "'" +
            ", paymentId=" + getPaymentId() +
            ", customerPaymentCode='" + getCustomerPaymentCode() + "'" +
            ", serviceCode=" + getServiceCode() +
            ", transactionAmount=" + getTransactionAmount() +
            ", charges=" + getCharges() +
            ", partnerCode='" + getPartnerCode() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", narration='" + getNarration() + "'" +
            ", currency=" + getCurrency() +
            ", debitAccount=" + getDebitAccount() +
            ", creditAccount=" + getCreditAccount() +
            ", proccessingStatus='" + getProccessingStatus() + "'" +
            ", fcrTransactionStatus='" + getFcrTransactionStatus() + "'" +
            ", fcrTransactionId='" + getFcrTransactionId() + "'" +
            ", fcrTransactionReference='" + getFcrTransactionReference() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            "}";
    }
}
