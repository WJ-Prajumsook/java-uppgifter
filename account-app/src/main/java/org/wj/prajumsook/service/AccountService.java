package org.wj.prajumsook.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.wj.prajumsook.model.Account;
import org.wj.prajumsook.repository.AccountRepository;
import javax.enterprise.context.ApplicationScoped;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
@Slf4j
public class AccountService implements AccountRepository {

    List<Account> accounts = readAccountJson();

    @Override
    public Iterable<Account> findAll() {
        return accounts;
    }

    @Override
    public Optional<Account> findById(String id) {
        List<Account> accounts = this.accounts;
        Account account = accounts.stream().filter(a -> a.getId().equalsIgnoreCase(id))
                .findAny()
                .orElse(new Account());
        log.info("Getting account by id " + id);
        log.info("Result account " + account);
        return Optional.of(account);
    }

    @Override
    public Optional<Account> save(Account account) {
        accounts.add(account);
        log.info("Account successfully created.");
        log.info(String.valueOf(account));
        return Optional.of(account);
    }

    @Override
    public Optional<Account> delete(Account account) {
        Optional<Account> accountOptional = accounts.stream().filter(a -> a.getId().equalsIgnoreCase(account.getId())).findAny();
        if(accountOptional.isPresent()) {
            accounts.remove(accountOptional.get());
        } else {
            log.error("Error deleting account with id " + account.getId());
        }

        return accountOptional;
    }

    private List<Account> readAccountJson() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("account.json");
            Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines();
            String json = lines.collect(Collectors.joining("\n"));
            ObjectMapper objectMapper = new ObjectMapper();

            List<Account> accounts = objectMapper.readValue(json, new TypeReference<List<Account>>() {});
            log.info("Success retrieving accounts");
            log.info(String.valueOf(accounts));
            return accounts;
        } catch (Exception e) {
            log.error(e.getMessage());
            return Collections.emptyList();
        }
    }
}
