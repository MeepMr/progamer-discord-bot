package de.justinklein.stattrackerspringbe.config;

import de.justinklein.stattrackerspringbe.discordInterface.sendMessage.DiscordMessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
@Configuration
@EnableScheduling
public class SchedulingConfig {

  private final DiscordMessageSender messageSender;
    @Scheduled(fixedDelay = 10000)
    public void fetchCurrentChannelActivity() {
      messageSender.sendTestMessage();
    }
}
