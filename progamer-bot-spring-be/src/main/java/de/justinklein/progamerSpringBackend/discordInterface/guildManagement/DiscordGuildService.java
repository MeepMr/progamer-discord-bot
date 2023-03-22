package de.justinklein.progamerSpringBackend.discordInterface.guildManagement;

import de.justinklein.progamerSpringBackend.discordInterface.guildManagement.discordGuild.DiscordGuild;
import de.justinklein.progamerSpringBackend.discordInterface.guildManagement.discordGuild.DiscordGuildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class DiscordGuildService {
  private final DiscordGuildRepository guildRepository;

  public DiscordGuild getGuildById(Long guildId) {
    return guildRepository.getReferenceById(guildId);
  }

  public void updateGuild(DiscordGuild guild) {
    guildRepository.save(guild);
  }

  public Collection<DiscordGuild> getAllGuildsFromDatabase() {
    return guildRepository.findAll();
  }
}
