package com.yukenautomation.currencyexchangeservice.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment env;

    @Autowired
    private CurrencyExchangeRepo currencyExchangeRepo;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getExchange(@PathVariable String from, @PathVariable String to) {
        Optional<CurrencyExchange> datum = currencyExchangeRepo.findByFromAndTo(from, to);
        if (datum.isEmpty())
            throw new RuntimeException("Resource not found");

        datum.get().setEnvironment(
                env.getProperty("local.server.port") + " "
                        + "V12" + " "
                        + env.getProperty("HOSTNAME"));

        return datum.get();
    }
}
