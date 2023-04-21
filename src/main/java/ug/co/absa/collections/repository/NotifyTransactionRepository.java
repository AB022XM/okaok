package ug.co.absa.collections.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ug.co.absa.collections.domain.NotifyTransaction;

/**
 * Spring Data JPA repository for the NotifyTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotifyTransactionRepository extends JpaRepository<NotifyTransaction, Long> {}
