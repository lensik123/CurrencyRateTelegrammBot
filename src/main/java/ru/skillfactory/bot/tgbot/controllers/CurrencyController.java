package ru.skillfactory.bot.tgbot.controllers;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillfactory.bot.tgbot.DTO.ValuteCursOnDate;
import ru.skillfactory.bot.tgbot.service.CentralRussianBankService;

@RestController
@RequiredArgsConstructor
public class CurrencyController {

  private final CentralRussianBankService centralRussianBankService;

  @GetMapping("/getCurrencies")
  public List<ValuteCursOnDate> getValuteCursOnDate() throws Exception {
    return centralRussianBankService.getCurrenciesFromCbr();
  }
}
