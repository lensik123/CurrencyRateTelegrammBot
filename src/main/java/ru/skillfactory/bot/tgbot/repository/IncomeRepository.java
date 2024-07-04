package ru.skillfactory.bot.tgbot.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillfactory.bot.tgbot.entity.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
  Optional<Income> findIncomeByChatId(Long ChatId);
}
