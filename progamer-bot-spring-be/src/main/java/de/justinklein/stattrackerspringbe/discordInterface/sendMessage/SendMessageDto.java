package de.justinklein.stattrackerspringbe.discordInterface.sendMessage;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendMessageDto {
  private String channelId;
  private String messageText;
}
