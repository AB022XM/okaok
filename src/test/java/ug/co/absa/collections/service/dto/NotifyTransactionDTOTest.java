package ug.co.absa.collections.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.collections.web.rest.TestUtil;

class NotifyTransactionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotifyTransactionDTO.class);
        NotifyTransactionDTO notifyTransactionDTO1 = new NotifyTransactionDTO();
        notifyTransactionDTO1.setId(1L);
        NotifyTransactionDTO notifyTransactionDTO2 = new NotifyTransactionDTO();
        assertThat(notifyTransactionDTO1).isNotEqualTo(notifyTransactionDTO2);
        notifyTransactionDTO2.setId(notifyTransactionDTO1.getId());
        assertThat(notifyTransactionDTO1).isEqualTo(notifyTransactionDTO2);
        notifyTransactionDTO2.setId(2L);
        assertThat(notifyTransactionDTO1).isNotEqualTo(notifyTransactionDTO2);
        notifyTransactionDTO1.setId(null);
        assertThat(notifyTransactionDTO1).isNotEqualTo(notifyTransactionDTO2);
    }
}
