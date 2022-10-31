package nodir.bot.telegram_exception.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    private String service;
    private String message;
    private String server;
    private String request;
    private String response;

}
