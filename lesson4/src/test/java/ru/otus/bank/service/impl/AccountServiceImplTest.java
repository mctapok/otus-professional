package ru.otus.bank.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.bank.dao.AccountDao;
import ru.otus.bank.entity.Account;
import ru.otus.bank.entity.Agreement;
import ru.otus.bank.service.exception.AccountException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {
    @Mock
    AccountDao accountDao;

    @InjectMocks
    AccountServiceImpl accountServiceImpl;

    @Test
    public void testTransfer() {
        Account sourceAccount = new Account();
        sourceAccount.setAmount(new BigDecimal(100));

        Account destinationAccount = new Account();
        destinationAccount.setAmount(new BigDecimal(10));

        when(accountDao.findById(eq(1L))).thenReturn(Optional.of(sourceAccount));
        when(accountDao.findById(eq(2L))).thenReturn(Optional.of(destinationAccount));

        accountServiceImpl.makeTransfer(1L, 2L, new BigDecimal(10));

        assertEquals(new BigDecimal(90), sourceAccount.getAmount());
        assertEquals(new BigDecimal(20), destinationAccount.getAmount());
    }

    @Test
    public void testSourceNotFound() {
        when(accountDao.findById(any())).thenReturn(Optional.empty());

        AccountException result = assertThrows(AccountException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                accountServiceImpl.makeTransfer(1L, 2L, new BigDecimal(10));
            }
        });
        assertEquals("No source account", result.getLocalizedMessage());
    }


    @Test
    public void testTransferWithVerify() {
        Account sourceAccount = new Account();
        sourceAccount.setAmount(new BigDecimal(100));
        sourceAccount.setId(1L);

        Account destinationAccount = new Account();
        destinationAccount.setAmount(new BigDecimal(10));
        destinationAccount.setId(2L);

        when(accountDao.findById(eq(1L))).thenReturn(Optional.of(sourceAccount));
        when(accountDao.findById(eq(2L))).thenReturn(Optional.of(destinationAccount));

        ArgumentMatcher<Account> sourceMatcher =
                argument -> argument.getId().equals(1L) && argument.getAmount().equals(new BigDecimal(90));

        ArgumentMatcher<Account> destinationMatcher =
                argument -> argument.getId().equals(2L) && argument.getAmount().equals(new BigDecimal(20));

        accountServiceImpl.makeTransfer(1L, 2L, new BigDecimal(10));

        verify(accountDao).save(argThat(sourceMatcher));
        verify(accountDao).save(argThat(destinationMatcher));
    }

    @Test
    public void testAddAccount() {
        Agreement agreement = new Agreement();
        agreement.setId(1L);
        String accountNumber = "123";
        Integer type = 1;
        BigDecimal amount = new BigDecimal("1000.00");
        Account expectedAccount = new Account();

        expectedAccount.setAgreementId(agreement.getId());
        expectedAccount.setType(type);
        expectedAccount.setNumber(accountNumber);
        expectedAccount.setAmount(amount);

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);

//        when(accountDao.save(any(Account.class))).thenReturn(expectedAccount);
        when(accountDao.save(captor.capture())).thenReturn(expectedAccount);
        Account resultAcc = accountServiceImpl.addAccount(agreement, accountNumber, type, amount);
        assertEquals(expectedAccount.getNumber(), captor.getValue().getNumber());
    }

    @Test
    public void testGetAccounts() {
        List<Account> expectedAccountsList = new ArrayList<>();
        expectedAccountsList.add(new Account());

    }

    @Test
    public void chargeTest() {
        Account account = new Account();
        account.setId(1L);
        account.setAmount(new BigDecimal(100));

        when(accountDao.findById(eq(1L))).thenReturn(Optional.of(account));

        accountServiceImpl.charge(1L, new BigDecimal(10));
        assertEquals(new BigDecimal(90), account.getAmount());
    }

    @Test
    public void getAccountsViaAgreementTest() {
        Agreement agreement = new Agreement();
        agreement.setId(1L);
        List<Account> accountList = new ArrayList<>();
        accountList.add(accountServiceImpl.addAccount(agreement, "124", 1, new BigDecimal(100L)));
        accountList.add(accountServiceImpl.addAccount(agreement, "125", 1, new BigDecimal(200L)));
        accountList.add(accountServiceImpl.addAccount(agreement, "126", 1, new BigDecimal(300L)));

        when(accountDao.findByAgreementId(eq(1L))).thenReturn(accountList);

        List<Account> result = accountServiceImpl.getAccounts(agreement);
        assertEquals(accountList, result);
        verify(accountDao).findByAgreementId(eq(1L));
    }
    @Test
    public void getAccountsTest(){
        List<Account> accountList = new ArrayList<>();
        accountList.add(accountServiceImpl.addAccount(new Agreement(), "124", 1, new BigDecimal(100L)));
        accountList.add(accountServiceImpl.addAccount(new Agreement(), "125", 2, new BigDecimal(200L)));
        accountList.add(accountServiceImpl.addAccount(new Agreement(), "126", 3, new BigDecimal(300L)));

        when(accountDao.findAll()).thenReturn(accountList);

        List<Account> result = accountServiceImpl.getAccounts();

        assertEquals(accountList, result);
    }
}
