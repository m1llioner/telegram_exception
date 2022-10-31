package nodir.bot.telegram_exception.Controller;

import lombok.RequiredArgsConstructor;
import lombok.var;
import nodir.bot.telegram_exception.Model.Message;
import nodir.bot.telegram_exception.Telegram.TelegramBot;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController {
    private final TelegramBot telegramBot;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessageToGroup(@RequestParam Map<String, String> message) {
        try {
            var messageF = Message.builder()
                    .server(message.get("server"))
                    .service(message.get("service"))
                    .request(message.get("request"))
                    .response(message.get("response"))
                    .message(message.get("message"))
                    .build();
            telegramBot.sendMessage(messageF);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

}
