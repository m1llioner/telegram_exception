package nodir.bot.telegram_exception.Telegram;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import nodir.bot.telegram_exception.Config.TelegramConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@RequiredArgsConstructor
public class TelegramStart {

    private final TelegramConfig telegramConfig;

//    @SneakyThrows
//    @Bean
//    public TelegramBotsApi startTelegramBot() {
//        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//        botsApi.registerBot(new TelegramBot(telegramConfig));
//        return botsApi;
//    }

    @Bean
    public TelegramBotsApi startTelegramBot() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TelegramBot(telegramConfig));
        return telegramBotsApi;
    }
}
