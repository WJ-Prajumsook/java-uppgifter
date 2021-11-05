package org.wj.prajumsook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.wj.prajumsook.model.Account;
import org.wj.prajumsook.resource.AccountResource;
import org.wj.prajumsook.service.AccountService;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
public class AccountApplicationTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountResource accountResource;

    @Test
    public void testGetAllAccount() {
        List<Account> accountList = readJsonFile();
        Mockito.when(accountService.findAll())
                .thenReturn(accountList);

        Response response = accountResource.getAccounts();
        List<Account> accounts = response.readEntity(new GenericType<List<Account>>() {});

        assertNotNull(accounts.get(0));
        assertNotNull(accounts.get(1).getAccountName());

        //accounts.forEach(System.out::println);
    }

    @Test
    public void testGetAccountById() {
        String accountId = "test_account_01";
        List<Account> accountList = readJsonFile();
        Mockito.when(accountService.findById(accountId))
                .thenReturn(
                        accountList.stream().filter(a -> a.getId().equalsIgnoreCase(accountId))
                        .findAny()
                );
        Response response = accountResource.getAccount(accountId);
        Optional<Account> accountOptional = response.readEntity(new GenericType<Optional<Account>>() {});

        assertNotNull(accountOptional.get());
        assertEquals(accountOptional.get().getBalance(), "310.000");
        //System.out.println(accountOptional.get());
    }

    @Test
    public void testCreateAccount() {
        List<Account> accountList = readJsonFile();
        Account account = new Account().setId("mockito_account")
                .setAccountName("Mockito Account")
                .setBalance("1212.1212");
        accountList.add(account);
        Mockito.when(accountService.save(account))
                .thenReturn(Optional.of(account));

        Response response = accountResource.createAccount(account);
        Optional<Account> accountOptional = response.readEntity(new GenericType<Optional<Account>>() {});

        assertNotNull(accountOptional.get());
        assertEquals(accountOptional.get().getAccountName(), "Mockito Account");
        //System.out.println(accountOptional.get());
    }

    private List<Account> readJsonFile() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("account.json");
            Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines();
            String json = lines.collect(Collectors.joining("\n"));
            ObjectMapper objectMapper = new ObjectMapper();

            List<Account> accounts = objectMapper.readValue(json, new TypeReference<List<Account>>() {});
            //System.out.println(accounts);
            return accounts;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }
}
