package de.justinklein.stattrackerspringbe.discordInterface.memberManagement.discordMember;

import de.justinklein.stattrackerspringbe.util.DataMapper;
import org.springframework.stereotype.Service;

@Service
public class DiscordMemberMapper implements DataMapper<DiscordMember, DiscordMemberDto> {

  @Override
  public DiscordMemberDto mapEntityToDto(DiscordMember entity) {
    return DiscordMemberDto.builder()
      .id(entity.getDiscordId())
      .name(entity.getNickname())
      .build();
  }

  @Override
  public DiscordMember mapDtoToEntity(DiscordMemberDto dto) {
    return DiscordMember.builder()
      .discordId(dto.getId())
      .nickname(dto.getName())
      .build();
  }
}
