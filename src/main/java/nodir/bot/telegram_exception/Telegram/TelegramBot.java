package nodir.bot.telegram_exception.Telegram;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nodir.bot.telegram_exception.Config.TelegramConfig;
import nodir.bot.telegram_exception.Model.Message;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final TelegramConfig telegramConfig;

    public TelegramBot(TelegramConfig telegramConfig) {
        this.telegramConfig = telegramConfig;
    }


    @Override
    public String getBotUsername() {
        return telegramConfig.getName();
    }

    @Override
    public String getBotToken() {
        return telegramConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        String userRequest = "\nname: " + update.getMessage().getFrom().getFirstName() + "\n"
                + "surname: " + update.getMessage().getFrom().getLastName() + "\n"
                + "userLink: @" + update.getMessage().getFrom().getUserName() + "\n"
                + "userId: " + update.getMessage().getFrom().getId() + "\n"
                + "message: " + update.getMessage().getText();
        log.info("Requested user: {}", userRequest);
    }

    @SneakyThrows
    public void sendMessage(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(telegramConfig.getGroupId());
        String server = "\n\uD83D\uDCBD Server: " + message.getServer();
        String service = "\n\uD83D\uDDA5 Service: " + message.getService();
        String date = "\n\uD83D\uDCC5 Date: " + LocalDate.now();
        String time = "\n⏳ Time: " + LocalTime.now();
        String request = "\n▶️ Request: " + message.getRequest();
        String response = "\n⏺ Response: " + message.getResponse();
        String text = "\n✉️ Message: " + message.getMessage();

        sendMessage.setText(server + service + date + time + request + response + text);
        execute(sendMessage);
    }
}
