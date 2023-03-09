package de.justinklein.stattrackerspringbe.config;

import de.justinklein.stattrackerspringbe.birthdaybot.BirthdayBotService;
import de.justinklein.stattrackerspringbe.discordInterface.DiscordInterfaceConnector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
@Configuration
@EnableScheduling
@Slf4j
public class SchedulingConfig {

  private final BirthdayBotService birthdayBotService;
  private final DiscordInterfaceConnector discordInterfaceConnector;

  // Runs every day at 00:00 (using server-time)
  @Scheduled(fixedDelay = 10000)
  public void sendBirthdayMessages() {
    log.info("Running daily schedule to send all birthday-messages");
    birthdayBotService.sendBirthdayMessages();
  }

  public void updateUserDatabase() {
    log.info("Updating users for all guilds (%d)");

  }
}
