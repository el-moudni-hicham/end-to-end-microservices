package dev.moudni.accountservice.repository;

import dev.moudni.accountservice.entites.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
