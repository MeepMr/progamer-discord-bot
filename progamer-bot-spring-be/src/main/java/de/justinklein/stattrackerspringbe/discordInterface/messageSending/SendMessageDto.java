package de.justinklein.stattrackerspringbe.discordInterface.messageSending;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendMessageDto {
  private Long channelId;
  private String messageText;
}
