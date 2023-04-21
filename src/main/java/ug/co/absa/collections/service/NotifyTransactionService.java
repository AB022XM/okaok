package ug.co.absa.collections.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ug.co.absa.collections.domain.NotifyTransaction;
import ug.co.absa.collections.repository.NotifyTransactionRepository;
import ug.co.absa.collections.service.dto.NotifyTransactionDTO;
import ug.co.absa.collections.service.mapper.NotifyTransactionMapper;

/**
 * Service Implementation for managing {@link NotifyTransaction}.
 */
@Service
@Transactional
public class NotifyTransactionService {

    private final Logger log = LoggerFactory.getLogger(NotifyTransactionService.class);

    private final NotifyTransactionRepository notifyTransactionRepository;

    private final NotifyTransactionMapper notifyTransactionMapper;

    public NotifyTransactionService(
        NotifyTransactionRepository notifyTransactionRepository,
        NotifyTransactionMapper notifyTransactionMapper
    ) {
        this.notifyTransactionRepository = notifyTransactionRepository;
        this.notifyTransactionMapper = notifyTransactionMapper;
    }

    /**
     * Save a notifyTransaction.
     *
     * @param notifyTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    public NotifyTransactionDTO save(NotifyTransactionDTO notifyTransactionDTO) {
        log.debug("Request to save NotifyTransaction : {}", notifyTransactionDTO);
        NotifyTransaction notifyTransaction = notifyTransactionMapper.toEntity(notifyTransactionDTO);
        notifyTransaction = notifyTransactionRepository.save(notifyTransaction);
        return notifyTransactionMapper.toDto(notifyTransaction);
    }

    /**
     * Update a notifyTransaction.
     *
     * @param notifyTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    public NotifyTransactionDTO update(NotifyTransactionDTO notifyTransactionDTO) {
        log.debug("Request to update NotifyTransaction : {}", notifyTransactionDTO);
        NotifyTransaction notifyTransaction = notifyTransactionMapper.toEntity(notifyTransactionDTO);
        notifyTransaction = notifyTransactionRepository.save(notifyTransaction);
        return notifyTransactionMapper.toDto(notifyTransaction);
    }

    /**
     * Partially update a notifyTransaction.
     *
     * @param notifyTransactionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NotifyTransactionDTO> partialUpdate(NotifyTransactionDTO notifyTransactionDTO) {
        log.debug("Request to partially update NotifyTransaction : {}", notifyTransactionDTO);

        return notifyTransactionRepository
            .findById(notifyTransactionDTO.getId())
            .map(existingNotifyTransaction -> {
                notifyTransactionMapper.partialUpdate(existingNotifyTransaction, notifyTransactionDTO);

                return existingNotifyTransaction;
            })
            .map(notifyTransactionRepository::save)
            .map(notifyTransactionMapper::toDto);
    }

    /**
     * Get all the notifyTransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<NotifyTransactionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NotifyTransactions");
        return notifyTransactionRepository.findAll(pageable).map(notifyTransactionMapper::toDto);
    }

    /**
     * Get one notifyTransaction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NotifyTransactionDTO> findOne(Long id) {
        log.debug("Request to get NotifyTransaction : {}", id);
        return notifyTransactionRepository.findById(id).map(notifyTransactionMapper::toDto);
    }

    /**
     * Delete the notifyTransaction by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NotifyTransaction : {}", id);
        notifyTransactionRepository.deleteById(id);
    }
}
