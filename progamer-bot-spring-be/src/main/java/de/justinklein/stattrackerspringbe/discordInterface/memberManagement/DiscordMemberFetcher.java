package de.justinklein.stattrackerspringbe.discordInterface.memberManagement;

import de.justinklein.stattrackerspringbe.discordInterface.DiscordInterfaceConnector;
import de.justinklein.stattrackerspringbe.discordInterface.guildManagement.discordGuild.DiscordGuild;
import de.justinklein.stattrackerspringbe.discordInterface.memberManagement.discordMember.DiscordMember;
import de.justinklein.stattrackerspringbe.discordInterface.memberManagement.discordMember.DiscordMemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class DiscordMemberFetcher {
  private final DiscordInterfaceConnector discordInterface;
  private final DiscordMemberMapper memberMapper;

  public Collection<DiscordMember> getAllMembersFromGuild(DiscordGuild guild) {
    var memberDtosOptional = discordInterface.getDiscordMembersFromGuild(guild.getGuildId());

    return memberDtosOptional
      .map(discordGuildMembersDto -> discordGuildMembersDto.getGuildMembers()
        .stream()
        .map(memberMapper::mapDtoToEntity)
        .toList()).orElseGet(List::of);
  }

}
