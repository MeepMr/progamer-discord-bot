package de.justinklein.progamerSpringBackend.discordInterface.guildManagement.discordGuild;

import de.justinklein.progamerSpringBackend.util.DataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DiscordGuildMapper implements DataMapper<DiscordGuild, DiscordGuildDto> {
  @Override
  public DiscordGuildDto mapEntityToDto(DiscordGuild entity) {
    return DiscordGuildDto.builder()
      .id(entity.getGuildId())
      .name(entity.getGuildName())
      .build();
  }

  @Override
  public DiscordGuild mapDtoToEntity(DiscordGuildDto dto) {
    return DiscordGuild.builder()
      .guildId(dto.getId())
      .guildName(dto.getName())
      .build();
  }
}
