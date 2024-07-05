package ru.skillfactory.bot.tgbot.repository;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skillfactory.bot.tgbot.entity.Spend;

@Repository
public interface SpendRepository extends JpaRepository<Spend, Long> {
  @Query("SELECT sum (s.spend) from Spend s where s.chatId = :chatId")
  Optional<BigDecimal> findTotalSpendByChatId(@Param("chatId") Long ChatId);
}
