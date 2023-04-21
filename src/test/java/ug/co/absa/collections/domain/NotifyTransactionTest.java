package ug.co.absa.collections.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.collections.web.rest.TestUtil;

class NotifyTransactionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotifyTransaction.class);
        NotifyTransaction notifyTransaction1 = new NotifyTransaction();
        notifyTransaction1.setId(1L);
        NotifyTransaction notifyTransaction2 = new NotifyTransaction();
        notifyTransaction2.setId(notifyTransaction1.getId());
        assertThat(notifyTransaction1).isEqualTo(notifyTransaction2);
        notifyTransaction2.setId(2L);
        assertThat(notifyTransaction1).isNotEqualTo(notifyTransaction2);
        notifyTransaction1.setId(null);
        assertThat(notifyTransaction1).isNotEqualTo(notifyTransaction2);
    }
}
