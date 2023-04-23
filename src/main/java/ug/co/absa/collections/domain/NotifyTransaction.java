package ug.co.absa.collections.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import ug.co.absa.collections.domain.enumeration.ProccesingStatus;
import ug.co.absa.collections.domain.enumeration.TransactionStatus;

/**
 * A NotifyTransaction.
 */
@Entity
@Table(name = "notify_transaction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NotifyTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)

    private Long id;

    @NotNull
    @Type(type = "uuid-char")
    @Column(name = "record_unique_identifier", length = 36, nullable = false, unique = true)
 // jhipster-needle-entity-add-field - JHipster will add fields here
    private UUID recordUniqueIdentifier;

    @NotNull
    @Column(name = "payment_id", nullable = false, unique = true)
    private Integer paymentId;

    @NotNull
    @Column(name = "customer_payment_code", nullable = false)
    private String customerPaymentCode;

    @NotNull
    @Column(name = "service_code", nullable = false)
    private Integer serviceCode;

    @NotNull
    @Column(name = "transaction_amount", nullable = false)
    private Integer transactionAmount;

    @Column(name = "charges")
    private Integer charges;

    @NotNull
    @Column(name = "partner_code", nullable = false)
    private String partnerCode;

    @NotNull
    @Column(name = "timestamp", nullable = false)
    private LocalDate timestamp;

    @NotNull
    @Column(name = "narration", nullable = false)
    private String narration;

    @NotNull
    @Column(name = "currency", nullable = false)
    private Integer currency;

    @NotNull
    @Column(name = "debit_account", nullable = true)
    private Integer debitAccount;

    @NotNull
    @Column(name = "credit_account", nullable = true)
    private Integer creditAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "proccessing_status", nullable=true)
    private ProccesingStatus proccessingStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "fcr_transaction_status", nullable=true)
    private TransactionStatus fcrTransactionStatus;

    @Column(name = "fcr_transaction_id", nullable=true)
    private String fcrTransactionId;

    @Column(name = "fcr_transaction_reference",nullable=true)
    private String fcrTransactionReference;

    @Column(name = "free_field_1",nullable=true)
    private String freeField1;

    @Column(name = "free_field_2",nullable=true)
    private String freeField2;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public NotifyTransaction id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getRecordUniqueIdentifier() {
        return this.recordUniqueIdentifier;
    }

    public NotifyTransaction recordUniqueIdentifier(UUID recordUniqueIdentifier) {
        this.setRecordUniqueIdentifier(recordUniqueIdentifier);
        return this;
    }

    public void setRecordUniqueIdentifier(UUID recordUniqueIdentifier) {
        this.recordUniqueIdentifier = recordUniqueIdentifier;
    }

    public Integer getPaymentId() {
        return this.paymentId;
    }

    public NotifyTransaction paymentId(Integer paymentId) {
        this.setPaymentId(paymentId);
        return this;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getCustomerPaymentCode() {
        return this.customerPaymentCode;
    }

    public NotifyTransaction customerPaymentCode(String customerPaymentCode) {
        this.setCustomerPaymentCode(customerPaymentCode);
        return this;
    }

    public void setCustomerPaymentCode(String customerPaymentCode) {
        this.customerPaymentCode = customerPaymentCode;
    }

    public Integer getServiceCode() {
        return this.serviceCode;
    }

    public NotifyTransaction serviceCode(Integer serviceCode) {
        this.setServiceCode(serviceCode);
        return this;
    }

    public void setServiceCode(Integer serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Integer getTransactionAmount() {
        return this.transactionAmount;
    }

    public NotifyTransaction transactionAmount(Integer transactionAmount) {
        this.setTransactionAmount(transactionAmount);
        return this;
    }

    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Integer getCharges() {
        return this.charges;
    }

    public NotifyTransaction charges(Integer charges) {
        this.setCharges(charges);
        return this;
    }

    public void setCharges(Integer charges) {
        this.charges = charges;
    }

    public String getPartnerCode() {
        return this.partnerCode;
    }

    public NotifyTransaction partnerCode(String partnerCode) {
        this.setPartnerCode(partnerCode);
        return this;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public LocalDate getTimestamp() {
        return this.timestamp;
    }

    public NotifyTransaction timestamp(LocalDate timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public String getNarration() {
        return this.narration;
    }

    public NotifyTransaction narration(String narration) {
        this.setNarration(narration);
        return this;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Integer getCurrency() {
        return this.currency;
    }

    public NotifyTransaction currency(Integer currency) {
        this.setCurrency(currency);
        return this;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public Integer getDebitAccount() {
        return this.debitAccount;
    }

    public NotifyTransaction debitAccount(Integer debitAccount) {
        this.setDebitAccount(debitAccount);
        return this;
    }

    public void setDebitAccount(Integer debitAccount) {
        this.debitAccount = debitAccount;
    }

    public Integer getCreditAccount() {
        return this.creditAccount;
    }

    public NotifyTransaction creditAccount(Integer creditAccount) {
        this.setCreditAccount(creditAccount);
        return this;
    }

    public void setCreditAccount(Integer creditAccount) {
        this.creditAccount = creditAccount;
    }

    public ProccesingStatus getProccessingStatus() {
        return this.proccessingStatus;
    }

    public NotifyTransaction proccessingStatus(ProccesingStatus proccessingStatus) {
        this.setProccessingStatus(proccessingStatus);
        return this;
    }

    public void setProccessingStatus(ProccesingStatus proccessingStatus) {
        this.proccessingStatus = proccessingStatus;
    }

    public TransactionStatus getFcrTransactionStatus() {
        return this.fcrTransactionStatus;
    }

    public NotifyTransaction fcrTransactionStatus(TransactionStatus fcrTransactionStatus) {
        this.setFcrTransactionStatus(fcrTransactionStatus);
        return this;
    }

    public void setFcrTransactionStatus(TransactionStatus fcrTransactionStatus) {
        this.fcrTransactionStatus = fcrTransactionStatus;
    }

    public String getFcrTransactionId() {
        return this.fcrTransactionId;
    }

    public NotifyTransaction fcrTransactionId(String fcrTransactionId) {
        this.setFcrTransactionId(fcrTransactionId);
        return this;
    }

    public void setFcrTransactionId(String fcrTransactionId) {
        this.fcrTransactionId = fcrTransactionId;
    }

    public String getFcrTransactionReference() {
        return this.fcrTransactionReference;
    }

    public NotifyTransaction fcrTransactionReference(String fcrTransactionReference) {
        this.setFcrTransactionReference(fcrTransactionReference);
        return this;
    }

    public void setFcrTransactionReference(String fcrTransactionReference) {
        this.fcrTransactionReference = fcrTransactionReference;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public NotifyTransaction freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public NotifyTransaction freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotifyTransaction)) {
            return false;
        }
        return id != null && id.equals(((NotifyTransaction) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotifyTransaction{" +
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
