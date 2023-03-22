package de.justinklein.progamerSpringBackend.discordInterface.guildManagement;

import de.justinklein.progamerSpringBackend.discordInterface.DiscordInterfaceConnector;
import de.justinklein.progamerSpringBackend.discordInterface.guildManagement.discordGuild.DiscordGuild;
import de.justinklein.progamerSpringBackend.discordInterface.guildManagement.discordGuild.DiscordGuildMapper;
import de.justinklein.progamerSpringBackend.discordInterface.guildManagement.discordGuild.DiscordGuildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class DiscordGuildService {
  private final DiscordGuildRepository guildRepository;
  private final DiscordInterfaceConnector interfaceConnector;
  private final DiscordGuildMapper guildMapper;

  public DiscordGuild getGuildById(Long guildId) {
    return guildRepository.getReferenceById(guildId);
  }

  public void saveGuild(DiscordGuild guild) {
    guildRepository.save(guild);
  }

  public Collection<DiscordGuild> getAllGuildsFromDatabase() {
    return guildRepository.findAll();
  }

  public Collection<DiscordGuild> getAllGuildsFromInterfaceConnector() {
    return interfaceConnector.getDiscordGuilds().stream().map(guildMapper::mapDtoToEntity).toList();
  }

  public boolean isGuildPresentInDatabase(Long guildId) {
    return guildRepository.existsById(guildId);
  }
}
