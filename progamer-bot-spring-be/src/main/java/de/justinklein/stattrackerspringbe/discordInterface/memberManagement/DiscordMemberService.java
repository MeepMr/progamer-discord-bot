package de.justinklein.stattrackerspringbe.discordInterface.memberManagement;

import de.justinklein.stattrackerspringbe.discordInterface.guildManagement.discordGuild.DiscordGuild;
import de.justinklein.stattrackerspringbe.discordInterface.memberManagement.discordMember.DiscordMember;
import de.justinklein.stattrackerspringbe.discordInterface.memberManagement.discordMember.DiscordMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class DiscordMemberService {
  private final DiscordMemberRepository memberRepository;

  public Collection<DiscordMember> getAllMembersFromGuild(DiscordGuild guild) {
    return getAllMembersFromGuild(guild.getGuildId());
  }

  public Collection<DiscordMember> getAllMembersFromGuild(Long guildId) {
    return memberRepository.findByGuildId(guildId);
  }

  public Collection<DiscordMember> getAllMembersWithBirthDayFromGuild(Long guildId) {
    return getAllMembersFromGuild(guildId).stream().filter(member -> member.getBirthDate() != null).toList();
  }
}
