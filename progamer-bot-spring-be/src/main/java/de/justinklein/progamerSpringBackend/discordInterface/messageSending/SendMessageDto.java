package de.justinklein.progamerSpringBackend.discordInterface.messageSending;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendMessageDto {
  private String channelId;
  private String messageText;
  private String guildId;
}
