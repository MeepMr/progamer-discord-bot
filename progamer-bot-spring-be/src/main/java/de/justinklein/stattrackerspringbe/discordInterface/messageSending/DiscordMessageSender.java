package de.justinklein.stattrackerspringbe.discordInterface.messageSending;

import de.justinklein.stattrackerspringbe.discordInterface.DiscordInterfaceConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscordMessageSender {
  private final DiscordInterfaceConnector interfaceConnector;

  public void sendMessage(Long guildId, Long channelId, String messageText) {
    var message = SendMessageDto.builder()
      .channelId(channelId.toString())
      .messageText(messageText)
      .build();

    this.interfaceConnector.sendMessage(guildId, message);
  }
}
