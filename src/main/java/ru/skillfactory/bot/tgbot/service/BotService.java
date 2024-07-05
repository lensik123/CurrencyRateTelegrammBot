package ru.skillfactory.bot.tgbot.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.skillfactory.bot.tgbot.DTO.ValuteCursOnDate;
import ru.skillfactory.bot.tgbot.entity.ActiveChat;
import ru.skillfactory.bot.tgbot.repository.ActiveChatRepository;
import ru.skillfactory.bot.tgbot.repository.IncomeRepository;
import ru.skillfactory.bot.tgbot.repository.SpendRepository;

@Service //Данный класс является сервисом
@Slf4j //Подключаем логирование из Lombok'a
@RequiredArgsConstructor
public class BotService extends TelegramLongPollingBot {

  private static final String START = "/start";
  private static final String CURRENT_RATES = "/currentrates";
  private static final String ADD_INCOME = "/addincome";
  private static final String ADD_SPEND = "/addspend";
  private static final String CHECK_INCOME = "/checkincome";
  private static final String CHECK_SPEND = "/checkspend";

  private Map<Long, List<String>> previousCommands = new ConcurrentHashMap<>();
  private final CentralRussianBankService centralRussianBankService;
  private final SpendRepository spendRepository;
  private final IncomeRepository incomeRepository;
  private final ActiveChatRepository activeChatRepository;
  private final FinanceService financeService;

  @Value("${bot.api.key}")
  private String apiKey;

  @Value("${bot.name}")
  private String name;


  private void putPreviousCommand(Long chatId, String command) {
    if (previousCommands.get(chatId) == null) {
      List<String> commands = new ArrayList<>();
      commands.add(command);
      previousCommands.put(chatId, commands);
    } else {
      previousCommands.get(chatId).add(command);
    }
  }

  private String getPreviousCommand(Long chatId) {
    return previousCommands.get(chatId)
        .get(previousCommands.get(chatId).size() - 1);
  }


  @Override
  public void onUpdateReceived(Update update) {
    Message message = update.getMessage();
    try {
      SendMessage response = new SendMessage();
      Long chatId = message.getChatId();
      response.setChatId(String.valueOf(chatId));
      String messageText = message.getText();

      switch (messageText.toLowerCase()) {
        case START:
          response.setText("Откройте меню и выберите команду");
          break;
        case CURRENT_RATES:
          StringBuilder ratesResponse = new StringBuilder();
          for (ValuteCursOnDate valuteCursOnDate : centralRussianBankService.getCurrenciesFromCbr()) {
            ratesResponse.append(valuteCursOnDate.getName())
                .append(" - ")
                .append(valuteCursOnDate.getCourse())
                .append(" (номинал: ")
                .append(valuteCursOnDate.getNominal())
                .append(")\n");
          }
          response.setText(ratesResponse.toString());
          break;
        case ADD_INCOME:
          response.setText("Отправьте мне сумму полученного дохода");
          break;
        case ADD_SPEND:
          response.setText("Отправьте мне сумму расходов");
          break;
        case CHECK_INCOME:
          Optional<BigDecimal> totalIncome = incomeRepository.findTotalIncomeByChatId(chatId);
          response.setText(totalIncome.map(BigDecimal::toString).orElse("У вас нет доходов"));
          break;
        case CHECK_SPEND:
          Optional<BigDecimal> totalSpend = spendRepository.findTotalSpendByChatId(chatId);
          response.setText(totalSpend.map(BigDecimal::toString).orElse("У вас нет расходов"));
          break;
        default:
          response.setText(financeService.addFinanceOperation(getPreviousCommand(chatId), messageText, chatId));
          break;
      }

      putPreviousCommand(message.getChatId(), message.getText());

      if (activeChatRepository.findActiveChatByChatId(chatId).isEmpty()) {
        ActiveChat activeChat = new ActiveChat();
        activeChat.setChatId(chatId);
        activeChatRepository.save(activeChat);
      }
      execute(response);

    } catch (TelegramApiException | DatatypeConfigurationException e) {
      e.printStackTrace();
    }


  }

  public void sendNotificationToAllActiveChats(String message, Set<Long> chatIds) {
    for (Long id : chatIds) {
      SendMessage sendMessage = new SendMessage();
      sendMessage.setChatId(String.valueOf(id));
      sendMessage.setText(message);
      try {
        execute(sendMessage);
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }
  }

  @PostConstruct
  public void start() {
    log.info("username: {}, token: {}", name, apiKey);
  }

  @Override
  public String getBotUsername() {
    return name;
  }

  @Override
  public String getBotToken() {
    return apiKey;
  }
}
