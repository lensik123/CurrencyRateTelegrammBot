package ru.skillfactory.bot.tgbot.repository;


import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skillfactory.bot.tgbot.entity.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
  @Query("SELECT SUM(i.income) FROM Income i WHERE i.chatId = :chatId")
  Optional<BigDecimal> findTotalIncomeByChatId(@Param("chatId") Long chatId);
}
