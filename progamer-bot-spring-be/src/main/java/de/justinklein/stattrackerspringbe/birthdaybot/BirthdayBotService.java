package de.justinklein.stattrackerspringbe.birthdaybot;

import de.justinklein.stattrackerspringbe.birthdaybot.birthdayConfig.BirthdayConfigRepository;
import de.justinklein.stattrackerspringbe.discordInterface.DiscordInterfaceConnector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BirthdayBotService {
  private final DiscordInterfaceConnector discordInterfaceConnector;
  private final BirthdayConfigRepository configRepository;

  public void sendBirthdayMessages() {

  }

  public void setBirthdayChannel(Long guildId, Long channelId) {
    var config = configRepository.getReferenceById(guildId);
    config.setBirthdayChannelId(channelId);
    configRepository.save(config);

    log.info("Updating the birthday-channel for guild with id: %d".formatted(guildId));
  }

  public void toggleBirthdayBotForGuild(Long guildId) {
    var config = configRepository.getReferenceById(guildId);
    var currentState = config.isEnabled();

    config.setEnabled(!currentState);
    configRepository.save(config);

    log.info("Toggling the birthday-bot functionality for guild with id: %d".formatted(guildId));
  }
}
