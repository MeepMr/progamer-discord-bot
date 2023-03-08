package de.justinklein.stattrackerspringbe.discordInterface.sendMessage;

import de.justinklein.stattrackerspringbe.discordInterface.DiscordInterfaceConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscordMessageSender {
  private final DiscordInterfaceConnector discordInterface;

  public void sendTestMessage() {
    var message = SendMessageDto.builder()
      .channelId("1047947055580057650")
      .messageText("Nice text-Message")
      .build();
    discordInterface.sendMessage(message);
  }
}
