package ru.otus.bank.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.otus.bank.dao.AgreementDao;
import ru.otus.bank.entity.Agreement;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AgreementServiceImplTest {

    private AgreementDao dao = Mockito.mock(AgreementDao.class);

    AgreementServiceImpl agreementServiceImpl;

    @BeforeEach
    public void init() {
        agreementServiceImpl = new AgreementServiceImpl(dao);
    }

    @Test
    public void testFindByName() {
        String name = "test";
        Agreement agreement = new Agreement();
        agreement.setId(10L);
        agreement.setName(name);

        Mockito.when(dao.findByName(name)).thenReturn(
                Optional.of(agreement));

        Optional<Agreement> result = agreementServiceImpl.findByName(name);

        Assertions.assertTrue(result.isPresent());
        assertEquals(10, agreement.getId());
    }

    @Test
    public void testFindByNameWithCaptor() {
        String name = "test";
        Agreement agreement = new Agreement();
        agreement.setId(10L);
        agreement.setName(name);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        Mockito.when(dao.findByName(captor.capture())).thenReturn(
                Optional.of(agreement));

        Optional<Agreement> result = agreementServiceImpl.findByName(name);

        assertEquals("test", captor.getValue());
        Assertions.assertTrue(result.isPresent());
        assertEquals(10, agreement.getId());
    }
    @Test
    public void addAgreementTest(){
        String name = "test";
        Agreement agreement = new Agreement();
        agreement.setId(1L);
        agreement.setName(name);

        Mockito.when(dao.save(any(Agreement.class))).thenReturn(agreement);

        Agreement result = agreementServiceImpl.addAgreement(name);

        assertNotNull(result);
        assertEquals("test", result.getName());
        assertEquals(agreement, result);
        verify(dao, times(1)).save(any(Agreement.class));
    }
}
