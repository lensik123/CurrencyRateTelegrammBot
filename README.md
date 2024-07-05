# Telegram Currency Rates Bot

Telegram bot for fetching current currency rates, adding and checking income and expenses.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Project Structure](#project-structure)

## Features

- **Current Rates**: Get the current exchange rates for various currencies.
- **Add Income**: Add income records.
- **Add Spend**: Add expense records.
- **Check Income**: Check the total income.
- **Check Spend**: Check the total expenses.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Telegram Bots API
- Lombok

## Getting Started

### Prerequisites

- Java 11 or higher
- PostgreSQL
- Telegram Bot API token

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/telegram-currency-rates-bot.git
   cd telegram-currency-rates-bot

2. Set up PostgreSQL database and update application.properties with your database credentials:
   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/yourdatabase
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update

4. Update application.properties with your Telegram Bot API token:
   ```
   bot.name=your_tg_bot_name
   bot.api.key=your_tg_bot_token

5. Build and run the application:
   ```
   ./mvnw clean install
   ./mvnw spring-boot:run

## Usage
Start a conversation with your bot on Telegram and use the following commands:

- /currentrates - Get the current exchange rates.
- /addincome - Add income record.
- /addspend - Add expense record.
- /checkincome - Check the total income.
- /checkspend - Check the total expenses.


## Project Structure
```
src
├── main
│   ├── java
│   │   └── ru
│   │       └── skillfactory
│   │           └── bot
│   │               ├── entity
│   │               │   ├── ActiveChat.java
│   │               │   ├── Income.java
│   │               │   └── Spend.java
│   │               ├── repository
│   │               │   ├── ActiveChatRepository.java
│   │               │   ├── IncomeRepository.java
│   │               │   └── SpendRepository.java
│   │               ├── service
│   │               │   ├── BotService.java
│   │               │   └── CentralRussianBankService.java
│   │               └── TgbotApplication.java
│   └── resources
│       └── application.properties
└── test
    └── java
        └── ru
            └── skillfactory
                └── bot
                    └── TgbotApplicationTests.java
