package ug.co.absa.collections.service.mapper;

import org.mapstruct.*;
import ug.co.absa.collections.domain.NotifyTransaction;
import ug.co.absa.collections.service.dto.NotifyTransactionDTO;

/**
 * Mapper for the entity {@link NotifyTransaction} and its DTO {@link NotifyTransactionDTO}.
 */
@Mapper(componentModel = "spring")
public interface NotifyTransactionMapper extends EntityMapper<NotifyTransactionDTO, NotifyTransaction> {}
