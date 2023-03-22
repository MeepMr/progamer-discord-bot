package de.justinklein.progamerSpringBackend.discordInterface.guildManagement.discordGuild;

import lombok.Data;

import java.util.Collection;

@Data
public class DiscordGuildCollection {
  private Collection<DiscordGuildDto> guilds;
}
