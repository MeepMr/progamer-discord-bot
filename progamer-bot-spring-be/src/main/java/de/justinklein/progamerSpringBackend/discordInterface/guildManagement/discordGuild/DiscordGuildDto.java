package de.justinklein.progamerSpringBackend.discordInterface.guildManagement.discordGuild;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link DiscordGuild} entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscordGuildDto {
  private Long id;
  private String name;
}
