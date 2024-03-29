package de.justinklein.progamerSpringBackend.discordInterface.memberManagement.discordMember;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscordGuildMembersDto {
  private Long guildId;
  private LinkedList<DiscordMemberDto> guildMembers;
}
