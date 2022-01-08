package com.yukenautomation.currencyexchangeservice.exchange;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange, Long> {
    Optional<CurrencyExchange> findByFromAndTo(String from, String to);
}
