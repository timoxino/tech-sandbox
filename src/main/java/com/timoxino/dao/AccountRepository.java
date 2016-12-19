package com.timoxino.dao;

import com.timoxino.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Class represents DAO to work with {@link Account} entity.
 *
 * @author timoxino.
 */
public interface AccountRepository extends JpaRepository<Account, Long>
{
    Optional<Account> findByUsername(String username);
}
