package org.wj.prajumsook.repository;

import java.util.Optional;

public interface Repository<Account, String> {

    Iterable<Account> findAll();
    Optional<Account> findById(String id);
    Optional<Account> save(Account account);
    Optional<Account> delete (Account account);

}
