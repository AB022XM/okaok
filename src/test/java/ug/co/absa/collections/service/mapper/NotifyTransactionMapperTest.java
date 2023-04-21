package ug.co.absa.collections.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NotifyTransactionMapperTest {

    private NotifyTransactionMapper notifyTransactionMapper;

    @BeforeEach
    public void setUp() {
        notifyTransactionMapper = new NotifyTransactionMapperImpl();
    }
}
