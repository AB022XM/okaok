package ug.co.absa.collections.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import ug.co.absa.collections.repository.NotifyTransactionRepository;
import ug.co.absa.collections.service.NotifyTransactionService;
import ug.co.absa.collections.service.dto.NotifyTransactionDTO;
import ug.co.absa.collections.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.collections.domain.NotifyTransaction}.
 */
@RestController
@RequestMapping("/api")
public class NotifyTransactionResource {

    private final Logger log = LoggerFactory.getLogger(NotifyTransactionResource.class);

    private static final String ENTITY_NAME = "notifyTransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NotifyTransactionService notifyTransactionService;

    private final NotifyTransactionRepository notifyTransactionRepository;

    public NotifyTransactionResource(
        NotifyTransactionService notifyTransactionService,
        NotifyTransactionRepository notifyTransactionRepository
    ) {
        this.notifyTransactionService = notifyTransactionService;
        this.notifyTransactionRepository = notifyTransactionRepository;
    }

    /**
     * {@code POST  /notify-transactions} : Create a new notifyTransaction.
     *
     * @param notifyTransactionDTO the notifyTransactionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new notifyTransactionDTO, or with status {@code 400 (Bad Request)} if the notifyTransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/notify-transactions")
    public ResponseEntity<NotifyTransactionDTO> createNotifyTransaction(@Valid @RequestBody NotifyTransactionDTO notifyTransactionDTO)
        throws URISyntaxException {
        log.debug("REST request to save NotifyTransaction : {}", notifyTransactionDTO);
        if (notifyTransactionDTO.getId() != null) {
            throw new BadRequestAlertException("A new notifyTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotifyTransactionDTO result = notifyTransactionService.save(notifyTransactionDTO);
        return ResponseEntity
            .created(new URI("/api/notify-transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /notify-transactions/:id} : Updates an existing notifyTransaction.
     *
     * @param id the id of the notifyTransactionDTO to save.
     * @param notifyTransactionDTO the notifyTransactionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notifyTransactionDTO,
     * or with status {@code 400 (Bad Request)} if the notifyTransactionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the notifyTransactionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/notify-transactions/{id}")
    public ResponseEntity<NotifyTransactionDTO> updateNotifyTransaction(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody NotifyTransactionDTO notifyTransactionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update NotifyTransaction : {}, {}", id, notifyTransactionDTO);
        if (notifyTransactionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, notifyTransactionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!notifyTransactionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NotifyTransactionDTO result = notifyTransactionService.update(notifyTransactionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, notifyTransactionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /notify-transactions/:id} : Partial updates given fields of an existing notifyTransaction, field will ignore if it is null
     *
     * @param id the id of the notifyTransactionDTO to save.
     * @param notifyTransactionDTO the notifyTransactionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notifyTransactionDTO,
     * or with status {@code 400 (Bad Request)} if the notifyTransactionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the notifyTransactionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the notifyTransactionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/notify-transactions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NotifyTransactionDTO> partialUpdateNotifyTransaction(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody NotifyTransactionDTO notifyTransactionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update NotifyTransaction partially : {}, {}", id, notifyTransactionDTO);
        if (notifyTransactionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, notifyTransactionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!notifyTransactionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NotifyTransactionDTO> result = notifyTransactionService.partialUpdate(notifyTransactionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, notifyTransactionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /notify-transactions} : get all the notifyTransactions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notifyTransactions in body.
     */
    @GetMapping("/notify-transactions")
    public ResponseEntity<List<NotifyTransactionDTO>> getAllNotifyTransactions(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of NotifyTransactions");
        Page<NotifyTransactionDTO> page = notifyTransactionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /notify-transactions/:id} : get the "id" notifyTransaction.
     *
     * @param id the id of the notifyTransactionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notifyTransactionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notify-transactions/{id}")
    public ResponseEntity<NotifyTransactionDTO> getNotifyTransaction(@PathVariable Long id) {
        log.debug("REST request to get NotifyTransaction : {}", id);
        Optional<NotifyTransactionDTO> notifyTransactionDTO = notifyTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notifyTransactionDTO);
    }

    /**
     * {@code DELETE  /notify-transactions/:id} : delete the "id" notifyTransaction.
     *
     * @param id the id of the notifyTransactionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/notify-transactions/{id}")
    public ResponseEntity<Void> deleteNotifyTransaction(@PathVariable Long id) {
        log.debug("REST request to delete NotifyTransaction : {}", id);
        notifyTransactionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
