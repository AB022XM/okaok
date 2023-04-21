package ug.co.absa.collections.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ug.co.absa.collections.IntegrationTest;
import ug.co.absa.collections.domain.NotifyTransaction;
import ug.co.absa.collections.domain.enumeration.ProccesingStatus;
import ug.co.absa.collections.domain.enumeration.TransactionStatus;
import ug.co.absa.collections.repository.NotifyTransactionRepository;
import ug.co.absa.collections.service.dto.NotifyTransactionDTO;
import ug.co.absa.collections.service.mapper.NotifyTransactionMapper;

/**
 * Integration tests for the {@link NotifyTransactionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NotifyTransactionResourceIT {

    private static final UUID DEFAULT_RECORD_UNIQUE_IDENTIFIER = UUID.randomUUID();
    private static final UUID UPDATED_RECORD_UNIQUE_IDENTIFIER = UUID.randomUUID();

    private static final Integer DEFAULT_PAYMENT_ID = 1;
    private static final Integer UPDATED_PAYMENT_ID = 2;

    private static final String DEFAULT_CUSTOMER_PAYMENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_PAYMENT_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_SERVICE_CODE = 1;
    private static final Integer UPDATED_SERVICE_CODE = 2;

    private static final Integer DEFAULT_TRANSACTION_AMOUNT = 1;
    private static final Integer UPDATED_TRANSACTION_AMOUNT = 2;

    private static final Integer DEFAULT_CHARGES = 1;
    private static final Integer UPDATED_CHARGES = 2;

    private static final String DEFAULT_PARTNER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_CODE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TIMESTAMP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TIMESTAMP = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NARRATION = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION = "BBBBBBBBBB";

    private static final Integer DEFAULT_CURRENCY = 1;
    private static final Integer UPDATED_CURRENCY = 2;

    private static final Integer DEFAULT_DEBIT_ACCOUNT = 1;
    private static final Integer UPDATED_DEBIT_ACCOUNT = 2;

    private static final Integer DEFAULT_CREDIT_ACCOUNT = 1;
    private static final Integer UPDATED_CREDIT_ACCOUNT = 2;

    private static final ProccesingStatus DEFAULT_PROCCESSING_STATUS = ProccesingStatus.INITIAL;
    private static final ProccesingStatus UPDATED_PROCCESSING_STATUS = ProccesingStatus.LOGGED;

    private static final TransactionStatus DEFAULT_FCR_TRANSACTION_STATUS = TransactionStatus.SUCCESS;
    private static final TransactionStatus UPDATED_FCR_TRANSACTION_STATUS = TransactionStatus.FAILED;

    private static final String DEFAULT_FCR_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_FCR_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FCR_TRANSACTION_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_FCR_TRANSACTION_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_2 = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/notify-transactions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NotifyTransactionRepository notifyTransactionRepository;

    @Autowired
    private NotifyTransactionMapper notifyTransactionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNotifyTransactionMockMvc;

    private NotifyTransaction notifyTransaction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotifyTransaction createEntity(EntityManager em) {
        NotifyTransaction notifyTransaction = new NotifyTransaction()
            .recordUniqueIdentifier(DEFAULT_RECORD_UNIQUE_IDENTIFIER)
            .paymentId(DEFAULT_PAYMENT_ID)
            .customerPaymentCode(DEFAULT_CUSTOMER_PAYMENT_CODE)
            .serviceCode(DEFAULT_SERVICE_CODE)
            .transactionAmount(DEFAULT_TRANSACTION_AMOUNT)
            .charges(DEFAULT_CHARGES)
            .partnerCode(DEFAULT_PARTNER_CODE)
            .timestamp(DEFAULT_TIMESTAMP)
            .narration(DEFAULT_NARRATION)
            .currency(DEFAULT_CURRENCY)
            .debitAccount(DEFAULT_DEBIT_ACCOUNT)
            .creditAccount(DEFAULT_CREDIT_ACCOUNT)
            .proccessingStatus(DEFAULT_PROCCESSING_STATUS)
            .fcrTransactionStatus(DEFAULT_FCR_TRANSACTION_STATUS)
            .fcrTransactionId(DEFAULT_FCR_TRANSACTION_ID)
            .fcrTransactionReference(DEFAULT_FCR_TRANSACTION_REFERENCE)
            .freeField1(DEFAULT_FREE_FIELD_1)
            .freeField2(DEFAULT_FREE_FIELD_2);
        return notifyTransaction;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotifyTransaction createUpdatedEntity(EntityManager em) {
        NotifyTransaction notifyTransaction = new NotifyTransaction()
            .recordUniqueIdentifier(UPDATED_RECORD_UNIQUE_IDENTIFIER)
            .paymentId(UPDATED_PAYMENT_ID)
            .customerPaymentCode(UPDATED_CUSTOMER_PAYMENT_CODE)
            .serviceCode(UPDATED_SERVICE_CODE)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .charges(UPDATED_CHARGES)
            .partnerCode(UPDATED_PARTNER_CODE)
            .timestamp(UPDATED_TIMESTAMP)
            .narration(UPDATED_NARRATION)
            .currency(UPDATED_CURRENCY)
            .debitAccount(UPDATED_DEBIT_ACCOUNT)
            .creditAccount(UPDATED_CREDIT_ACCOUNT)
            .proccessingStatus(UPDATED_PROCCESSING_STATUS)
            .fcrTransactionStatus(UPDATED_FCR_TRANSACTION_STATUS)
            .fcrTransactionId(UPDATED_FCR_TRANSACTION_ID)
            .fcrTransactionReference(UPDATED_FCR_TRANSACTION_REFERENCE)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2);
        return notifyTransaction;
    }

    @BeforeEach
    public void initTest() {
        notifyTransaction = createEntity(em);
    }

    @Test
    @Transactional
    void createNotifyTransaction() throws Exception {
        int databaseSizeBeforeCreate = notifyTransactionRepository.findAll().size();
        // Create the NotifyTransaction
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(notifyTransaction);
        restNotifyTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the NotifyTransaction in the database
        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeCreate + 1);
        NotifyTransaction testNotifyTransaction = notifyTransactionList.get(notifyTransactionList.size() - 1);
        assertThat(testNotifyTransaction.getRecordUniqueIdentifier()).isEqualTo(DEFAULT_RECORD_UNIQUE_IDENTIFIER);
        assertThat(testNotifyTransaction.getPaymentId()).isEqualTo(DEFAULT_PAYMENT_ID);
        assertThat(testNotifyTransaction.getCustomerPaymentCode()).isEqualTo(DEFAULT_CUSTOMER_PAYMENT_CODE);
        assertThat(testNotifyTransaction.getServiceCode()).isEqualTo(DEFAULT_SERVICE_CODE);
        assertThat(testNotifyTransaction.getTransactionAmount()).isEqualTo(DEFAULT_TRANSACTION_AMOUNT);
        assertThat(testNotifyTransaction.getCharges()).isEqualTo(DEFAULT_CHARGES);
        assertThat(testNotifyTransaction.getPartnerCode()).isEqualTo(DEFAULT_PARTNER_CODE);
        assertThat(testNotifyTransaction.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testNotifyTransaction.getNarration()).isEqualTo(DEFAULT_NARRATION);
        assertThat(testNotifyTransaction.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testNotifyTransaction.getDebitAccount()).isEqualTo(DEFAULT_DEBIT_ACCOUNT);
        assertThat(testNotifyTransaction.getCreditAccount()).isEqualTo(DEFAULT_CREDIT_ACCOUNT);
        assertThat(testNotifyTransaction.getProccessingStatus()).isEqualTo(DEFAULT_PROCCESSING_STATUS);
        assertThat(testNotifyTransaction.getFcrTransactionStatus()).isEqualTo(DEFAULT_FCR_TRANSACTION_STATUS);
        assertThat(testNotifyTransaction.getFcrTransactionId()).isEqualTo(DEFAULT_FCR_TRANSACTION_ID);
        assertThat(testNotifyTransaction.getFcrTransactionReference()).isEqualTo(DEFAULT_FCR_TRANSACTION_REFERENCE);
        assertThat(testNotifyTransaction.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testNotifyTransaction.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void createNotifyTransactionWithExistingId() throws Exception {
        // Create the NotifyTransaction with an existing ID
        notifyTransaction.setId(1L);
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(notifyTransaction);

        int databaseSizeBeforeCreate = notifyTransactionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotifyTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifyTransaction in the database
        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkRecordUniqueIdentifierIsRequired() throws Exception {
        int databaseSizeBeforeTest = notifyTransactionRepository.findAll().size();
        // set the field null
        notifyTransaction.setRecordUniqueIdentifier(null);

        // Create the NotifyTransaction, which fails.
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(notifyTransaction);

        restNotifyTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPaymentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = notifyTransactionRepository.findAll().size();
        // set the field null
        notifyTransaction.setPaymentId(null);

        // Create the NotifyTransaction, which fails.
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(notifyTransaction);

        restNotifyTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCustomerPaymentCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = notifyTransactionRepository.findAll().size();
        // set the field null
        notifyTransaction.setCustomerPaymentCode(null);

        // Create the NotifyTransaction, which fails.
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(notifyTransaction);

        restNotifyTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkServiceCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = notifyTransactionRepository.findAll().size();
        // set the field null
        notifyTransaction.setServiceCode(null);

        // Create the NotifyTransaction, which fails.
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(notifyTransaction);

        restNotifyTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTransactionAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = notifyTransactionRepository.findAll().size();
        // set the field null
        notifyTransaction.setTransactionAmount(null);

        // Create the NotifyTransaction, which fails.
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(notifyTransaction);

        restNotifyTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPartnerCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = notifyTransactionRepository.findAll().size();
        // set the field null
        notifyTransaction.setPartnerCode(null);

        // Create the NotifyTransaction, which fails.
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(notifyTransaction);

        restNotifyTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTimestampIsRequired() throws Exception {
        int databaseSizeBeforeTest = notifyTransactionRepository.findAll().size();
        // set the field null
        notifyTransaction.setTimestamp(null);

        // Create the NotifyTransaction, which fails.
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(notifyTransaction);

        restNotifyTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNarrationIsRequired() throws Exception {
        int databaseSizeBeforeTest = notifyTransactionRepository.findAll().size();
        // set the field null
        notifyTransaction.setNarration(null);

        // Create the NotifyTransaction, which fails.
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(notifyTransaction);

        restNotifyTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCurrencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = notifyTransactionRepository.findAll().size();
        // set the field null
        notifyTransaction.setCurrency(null);

        // Create the NotifyTransaction, which fails.
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(notifyTransaction);

        restNotifyTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDebitAccountIsRequired() throws Exception {
        int databaseSizeBeforeTest = notifyTransactionRepository.findAll().size();
        // set the field null
        notifyTransaction.setDebitAccount(null);

        // Create the NotifyTransaction, which fails.
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(notifyTransaction);

        restNotifyTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreditAccountIsRequired() throws Exception {
        int databaseSizeBeforeTest = notifyTransactionRepository.findAll().size();
        // set the field null
        notifyTransaction.setCreditAccount(null);

        // Create the NotifyTransaction, which fails.
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(notifyTransaction);

        restNotifyTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllNotifyTransactions() throws Exception {
        // Initialize the database
        notifyTransactionRepository.saveAndFlush(notifyTransaction);

        // Get all the notifyTransactionList
        restNotifyTransactionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notifyTransaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].recordUniqueIdentifier").value(hasItem(DEFAULT_RECORD_UNIQUE_IDENTIFIER.toString())))
            .andExpect(jsonPath("$.[*].paymentId").value(hasItem(DEFAULT_PAYMENT_ID)))
            .andExpect(jsonPath("$.[*].customerPaymentCode").value(hasItem(DEFAULT_CUSTOMER_PAYMENT_CODE)))
            .andExpect(jsonPath("$.[*].serviceCode").value(hasItem(DEFAULT_SERVICE_CODE)))
            .andExpect(jsonPath("$.[*].transactionAmount").value(hasItem(DEFAULT_TRANSACTION_AMOUNT)))
            .andExpect(jsonPath("$.[*].charges").value(hasItem(DEFAULT_CHARGES)))
            .andExpect(jsonPath("$.[*].partnerCode").value(hasItem(DEFAULT_PARTNER_CODE)))
            .andExpect(jsonPath("$.[*].timestamp").value(hasItem(DEFAULT_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].narration").value(hasItem(DEFAULT_NARRATION)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY)))
            .andExpect(jsonPath("$.[*].debitAccount").value(hasItem(DEFAULT_DEBIT_ACCOUNT)))
            .andExpect(jsonPath("$.[*].creditAccount").value(hasItem(DEFAULT_CREDIT_ACCOUNT)))
            .andExpect(jsonPath("$.[*].proccessingStatus").value(hasItem(DEFAULT_PROCCESSING_STATUS.toString())))
            .andExpect(jsonPath("$.[*].fcrTransactionStatus").value(hasItem(DEFAULT_FCR_TRANSACTION_STATUS.toString())))
            .andExpect(jsonPath("$.[*].fcrTransactionId").value(hasItem(DEFAULT_FCR_TRANSACTION_ID)))
            .andExpect(jsonPath("$.[*].fcrTransactionReference").value(hasItem(DEFAULT_FCR_TRANSACTION_REFERENCE)))
            .andExpect(jsonPath("$.[*].freeField1").value(hasItem(DEFAULT_FREE_FIELD_1)))
            .andExpect(jsonPath("$.[*].freeField2").value(hasItem(DEFAULT_FREE_FIELD_2)));
    }

    @Test
    @Transactional
    void getNotifyTransaction() throws Exception {
        // Initialize the database
        notifyTransactionRepository.saveAndFlush(notifyTransaction);

        // Get the notifyTransaction
        restNotifyTransactionMockMvc
            .perform(get(ENTITY_API_URL_ID, notifyTransaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(notifyTransaction.getId().intValue()))
            .andExpect(jsonPath("$.recordUniqueIdentifier").value(DEFAULT_RECORD_UNIQUE_IDENTIFIER.toString()))
            .andExpect(jsonPath("$.paymentId").value(DEFAULT_PAYMENT_ID))
            .andExpect(jsonPath("$.customerPaymentCode").value(DEFAULT_CUSTOMER_PAYMENT_CODE))
            .andExpect(jsonPath("$.serviceCode").value(DEFAULT_SERVICE_CODE))
            .andExpect(jsonPath("$.transactionAmount").value(DEFAULT_TRANSACTION_AMOUNT))
            .andExpect(jsonPath("$.charges").value(DEFAULT_CHARGES))
            .andExpect(jsonPath("$.partnerCode").value(DEFAULT_PARTNER_CODE))
            .andExpect(jsonPath("$.timestamp").value(DEFAULT_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.narration").value(DEFAULT_NARRATION))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY))
            .andExpect(jsonPath("$.debitAccount").value(DEFAULT_DEBIT_ACCOUNT))
            .andExpect(jsonPath("$.creditAccount").value(DEFAULT_CREDIT_ACCOUNT))
            .andExpect(jsonPath("$.proccessingStatus").value(DEFAULT_PROCCESSING_STATUS.toString()))
            .andExpect(jsonPath("$.fcrTransactionStatus").value(DEFAULT_FCR_TRANSACTION_STATUS.toString()))
            .andExpect(jsonPath("$.fcrTransactionId").value(DEFAULT_FCR_TRANSACTION_ID))
            .andExpect(jsonPath("$.fcrTransactionReference").value(DEFAULT_FCR_TRANSACTION_REFERENCE))
            .andExpect(jsonPath("$.freeField1").value(DEFAULT_FREE_FIELD_1))
            .andExpect(jsonPath("$.freeField2").value(DEFAULT_FREE_FIELD_2));
    }

    @Test
    @Transactional
    void getNonExistingNotifyTransaction() throws Exception {
        // Get the notifyTransaction
        restNotifyTransactionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingNotifyTransaction() throws Exception {
        // Initialize the database
        notifyTransactionRepository.saveAndFlush(notifyTransaction);

        int databaseSizeBeforeUpdate = notifyTransactionRepository.findAll().size();

        // Update the notifyTransaction
        NotifyTransaction updatedNotifyTransaction = notifyTransactionRepository.findById(notifyTransaction.getId()).get();
        // Disconnect from session so that the updates on updatedNotifyTransaction are not directly saved in db
        em.detach(updatedNotifyTransaction);
        updatedNotifyTransaction
            .recordUniqueIdentifier(UPDATED_RECORD_UNIQUE_IDENTIFIER)
            .paymentId(UPDATED_PAYMENT_ID)
            .customerPaymentCode(UPDATED_CUSTOMER_PAYMENT_CODE)
            .serviceCode(UPDATED_SERVICE_CODE)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .charges(UPDATED_CHARGES)
            .partnerCode(UPDATED_PARTNER_CODE)
            .timestamp(UPDATED_TIMESTAMP)
            .narration(UPDATED_NARRATION)
            .currency(UPDATED_CURRENCY)
            .debitAccount(UPDATED_DEBIT_ACCOUNT)
            .creditAccount(UPDATED_CREDIT_ACCOUNT)
            .proccessingStatus(UPDATED_PROCCESSING_STATUS)
            .fcrTransactionStatus(UPDATED_FCR_TRANSACTION_STATUS)
            .fcrTransactionId(UPDATED_FCR_TRANSACTION_ID)
            .fcrTransactionReference(UPDATED_FCR_TRANSACTION_REFERENCE)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2);
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(updatedNotifyTransaction);

        restNotifyTransactionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, notifyTransactionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isOk());

        // Validate the NotifyTransaction in the database
        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeUpdate);
        NotifyTransaction testNotifyTransaction = notifyTransactionList.get(notifyTransactionList.size() - 1);
        assertThat(testNotifyTransaction.getRecordUniqueIdentifier()).isEqualTo(UPDATED_RECORD_UNIQUE_IDENTIFIER);
        assertThat(testNotifyTransaction.getPaymentId()).isEqualTo(UPDATED_PAYMENT_ID);
        assertThat(testNotifyTransaction.getCustomerPaymentCode()).isEqualTo(UPDATED_CUSTOMER_PAYMENT_CODE);
        assertThat(testNotifyTransaction.getServiceCode()).isEqualTo(UPDATED_SERVICE_CODE);
        assertThat(testNotifyTransaction.getTransactionAmount()).isEqualTo(UPDATED_TRANSACTION_AMOUNT);
        assertThat(testNotifyTransaction.getCharges()).isEqualTo(UPDATED_CHARGES);
        assertThat(testNotifyTransaction.getPartnerCode()).isEqualTo(UPDATED_PARTNER_CODE);
        assertThat(testNotifyTransaction.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testNotifyTransaction.getNarration()).isEqualTo(UPDATED_NARRATION);
        assertThat(testNotifyTransaction.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testNotifyTransaction.getDebitAccount()).isEqualTo(UPDATED_DEBIT_ACCOUNT);
        assertThat(testNotifyTransaction.getCreditAccount()).isEqualTo(UPDATED_CREDIT_ACCOUNT);
        assertThat(testNotifyTransaction.getProccessingStatus()).isEqualTo(UPDATED_PROCCESSING_STATUS);
        assertThat(testNotifyTransaction.getFcrTransactionStatus()).isEqualTo(UPDATED_FCR_TRANSACTION_STATUS);
        assertThat(testNotifyTransaction.getFcrTransactionId()).isEqualTo(UPDATED_FCR_TRANSACTION_ID);
        assertThat(testNotifyTransaction.getFcrTransactionReference()).isEqualTo(UPDATED_FCR_TRANSACTION_REFERENCE);
        assertThat(testNotifyTransaction.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testNotifyTransaction.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void putNonExistingNotifyTransaction() throws Exception {
        int databaseSizeBeforeUpdate = notifyTransactionRepository.findAll().size();
        notifyTransaction.setId(count.incrementAndGet());

        // Create the NotifyTransaction
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(notifyTransaction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotifyTransactionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, notifyTransactionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifyTransaction in the database
        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNotifyTransaction() throws Exception {
        int databaseSizeBeforeUpdate = notifyTransactionRepository.findAll().size();
        notifyTransaction.setId(count.incrementAndGet());

        // Create the NotifyTransaction
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(notifyTransaction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotifyTransactionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifyTransaction in the database
        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNotifyTransaction() throws Exception {
        int databaseSizeBeforeUpdate = notifyTransactionRepository.findAll().size();
        notifyTransaction.setId(count.incrementAndGet());

        // Create the NotifyTransaction
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(notifyTransaction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotifyTransactionMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NotifyTransaction in the database
        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNotifyTransactionWithPatch() throws Exception {
        // Initialize the database
        notifyTransactionRepository.saveAndFlush(notifyTransaction);

        int databaseSizeBeforeUpdate = notifyTransactionRepository.findAll().size();

        // Update the notifyTransaction using partial update
        NotifyTransaction partialUpdatedNotifyTransaction = new NotifyTransaction();
        partialUpdatedNotifyTransaction.setId(notifyTransaction.getId());

        partialUpdatedNotifyTransaction
            .recordUniqueIdentifier(UPDATED_RECORD_UNIQUE_IDENTIFIER)
            .paymentId(UPDATED_PAYMENT_ID)
            .customerPaymentCode(UPDATED_CUSTOMER_PAYMENT_CODE)
            .serviceCode(UPDATED_SERVICE_CODE)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .partnerCode(UPDATED_PARTNER_CODE)
            .timestamp(UPDATED_TIMESTAMP)
            .currency(UPDATED_CURRENCY)
            .fcrTransactionReference(UPDATED_FCR_TRANSACTION_REFERENCE)
            .freeField1(UPDATED_FREE_FIELD_1);

        restNotifyTransactionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNotifyTransaction.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNotifyTransaction))
            )
            .andExpect(status().isOk());

        // Validate the NotifyTransaction in the database
        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeUpdate);
        NotifyTransaction testNotifyTransaction = notifyTransactionList.get(notifyTransactionList.size() - 1);
        assertThat(testNotifyTransaction.getRecordUniqueIdentifier()).isEqualTo(UPDATED_RECORD_UNIQUE_IDENTIFIER);
        assertThat(testNotifyTransaction.getPaymentId()).isEqualTo(UPDATED_PAYMENT_ID);
        assertThat(testNotifyTransaction.getCustomerPaymentCode()).isEqualTo(UPDATED_CUSTOMER_PAYMENT_CODE);
        assertThat(testNotifyTransaction.getServiceCode()).isEqualTo(UPDATED_SERVICE_CODE);
        assertThat(testNotifyTransaction.getTransactionAmount()).isEqualTo(UPDATED_TRANSACTION_AMOUNT);
        assertThat(testNotifyTransaction.getCharges()).isEqualTo(DEFAULT_CHARGES);
        assertThat(testNotifyTransaction.getPartnerCode()).isEqualTo(UPDATED_PARTNER_CODE);
        assertThat(testNotifyTransaction.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testNotifyTransaction.getNarration()).isEqualTo(DEFAULT_NARRATION);
        assertThat(testNotifyTransaction.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testNotifyTransaction.getDebitAccount()).isEqualTo(DEFAULT_DEBIT_ACCOUNT);
        assertThat(testNotifyTransaction.getCreditAccount()).isEqualTo(DEFAULT_CREDIT_ACCOUNT);
        assertThat(testNotifyTransaction.getProccessingStatus()).isEqualTo(DEFAULT_PROCCESSING_STATUS);
        assertThat(testNotifyTransaction.getFcrTransactionStatus()).isEqualTo(DEFAULT_FCR_TRANSACTION_STATUS);
        assertThat(testNotifyTransaction.getFcrTransactionId()).isEqualTo(DEFAULT_FCR_TRANSACTION_ID);
        assertThat(testNotifyTransaction.getFcrTransactionReference()).isEqualTo(UPDATED_FCR_TRANSACTION_REFERENCE);
        assertThat(testNotifyTransaction.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testNotifyTransaction.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void fullUpdateNotifyTransactionWithPatch() throws Exception {
        // Initialize the database
        notifyTransactionRepository.saveAndFlush(notifyTransaction);

        int databaseSizeBeforeUpdate = notifyTransactionRepository.findAll().size();

        // Update the notifyTransaction using partial update
        NotifyTransaction partialUpdatedNotifyTransaction = new NotifyTransaction();
        partialUpdatedNotifyTransaction.setId(notifyTransaction.getId());

        partialUpdatedNotifyTransaction
            .recordUniqueIdentifier(UPDATED_RECORD_UNIQUE_IDENTIFIER)
            .paymentId(UPDATED_PAYMENT_ID)
            .customerPaymentCode(UPDATED_CUSTOMER_PAYMENT_CODE)
            .serviceCode(UPDATED_SERVICE_CODE)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .charges(UPDATED_CHARGES)
            .partnerCode(UPDATED_PARTNER_CODE)
            .timestamp(UPDATED_TIMESTAMP)
            .narration(UPDATED_NARRATION)
            .currency(UPDATED_CURRENCY)
            .debitAccount(UPDATED_DEBIT_ACCOUNT)
            .creditAccount(UPDATED_CREDIT_ACCOUNT)
            .proccessingStatus(UPDATED_PROCCESSING_STATUS)
            .fcrTransactionStatus(UPDATED_FCR_TRANSACTION_STATUS)
            .fcrTransactionId(UPDATED_FCR_TRANSACTION_ID)
            .fcrTransactionReference(UPDATED_FCR_TRANSACTION_REFERENCE)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2);

        restNotifyTransactionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNotifyTransaction.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNotifyTransaction))
            )
            .andExpect(status().isOk());

        // Validate the NotifyTransaction in the database
        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeUpdate);
        NotifyTransaction testNotifyTransaction = notifyTransactionList.get(notifyTransactionList.size() - 1);
        assertThat(testNotifyTransaction.getRecordUniqueIdentifier()).isEqualTo(UPDATED_RECORD_UNIQUE_IDENTIFIER);
        assertThat(testNotifyTransaction.getPaymentId()).isEqualTo(UPDATED_PAYMENT_ID);
        assertThat(testNotifyTransaction.getCustomerPaymentCode()).isEqualTo(UPDATED_CUSTOMER_PAYMENT_CODE);
        assertThat(testNotifyTransaction.getServiceCode()).isEqualTo(UPDATED_SERVICE_CODE);
        assertThat(testNotifyTransaction.getTransactionAmount()).isEqualTo(UPDATED_TRANSACTION_AMOUNT);
        assertThat(testNotifyTransaction.getCharges()).isEqualTo(UPDATED_CHARGES);
        assertThat(testNotifyTransaction.getPartnerCode()).isEqualTo(UPDATED_PARTNER_CODE);
        assertThat(testNotifyTransaction.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testNotifyTransaction.getNarration()).isEqualTo(UPDATED_NARRATION);
        assertThat(testNotifyTransaction.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testNotifyTransaction.getDebitAccount()).isEqualTo(UPDATED_DEBIT_ACCOUNT);
        assertThat(testNotifyTransaction.getCreditAccount()).isEqualTo(UPDATED_CREDIT_ACCOUNT);
        assertThat(testNotifyTransaction.getProccessingStatus()).isEqualTo(UPDATED_PROCCESSING_STATUS);
        assertThat(testNotifyTransaction.getFcrTransactionStatus()).isEqualTo(UPDATED_FCR_TRANSACTION_STATUS);
        assertThat(testNotifyTransaction.getFcrTransactionId()).isEqualTo(UPDATED_FCR_TRANSACTION_ID);
        assertThat(testNotifyTransaction.getFcrTransactionReference()).isEqualTo(UPDATED_FCR_TRANSACTION_REFERENCE);
        assertThat(testNotifyTransaction.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testNotifyTransaction.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void patchNonExistingNotifyTransaction() throws Exception {
        int databaseSizeBeforeUpdate = notifyTransactionRepository.findAll().size();
        notifyTransaction.setId(count.incrementAndGet());

        // Create the NotifyTransaction
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(notifyTransaction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotifyTransactionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, notifyTransactionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifyTransaction in the database
        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNotifyTransaction() throws Exception {
        int databaseSizeBeforeUpdate = notifyTransactionRepository.findAll().size();
        notifyTransaction.setId(count.incrementAndGet());

        // Create the NotifyTransaction
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(notifyTransaction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotifyTransactionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifyTransaction in the database
        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNotifyTransaction() throws Exception {
        int databaseSizeBeforeUpdate = notifyTransactionRepository.findAll().size();
        notifyTransaction.setId(count.incrementAndGet());

        // Create the NotifyTransaction
        NotifyTransactionDTO notifyTransactionDTO = notifyTransactionMapper.toDto(notifyTransaction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotifyTransactionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(notifyTransactionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NotifyTransaction in the database
        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNotifyTransaction() throws Exception {
        // Initialize the database
        notifyTransactionRepository.saveAndFlush(notifyTransaction);

        int databaseSizeBeforeDelete = notifyTransactionRepository.findAll().size();

        // Delete the notifyTransaction
        restNotifyTransactionMockMvc
            .perform(delete(ENTITY_API_URL_ID, notifyTransaction.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NotifyTransaction> notifyTransactionList = notifyTransactionRepository.findAll();
        assertThat(notifyTransactionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
