package de.justinklein.stattrackerspringbe.discordInterface.memberManagement;

import de.justinklein.stattrackerspringbe.discordInterface.guildManagement.discordGuild.DiscordGuild;
import de.justinklein.stattrackerspringbe.discordInterface.memberManagement.discordMember.DiscordMember;
import de.justinklein.stattrackerspringbe.discordInterface.memberManagement.discordMember.DiscordMemberDto;
import de.justinklein.stattrackerspringbe.discordInterface.memberManagement.discordMember.DiscordMemberMapper;
import de.justinklein.stattrackerspringbe.discordInterface.memberManagement.discordMember.DiscordMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscordMemberService {
  private final DiscordMemberRepository memberRepository;
  private final DiscordMemberMapper memberMapper;

  public Collection<DiscordMember> getAllMembersFromGuild(DiscordGuild guild) {
    return getAllMembersFromGuild(guild.getGuildId());
  }

  public Collection<DiscordMember> getAllMembersFromGuild(Long guildId) {
    return memberRepository.findByGuildId(guildId);
  }

  public Collection<DiscordMember> getAllMembersWithBirthDayFromGuild(Long guildId) {
    return getAllMembersFromGuild(guildId).stream().filter(member -> member.getBirthDate() != null).toList();
  }

  public void saveGuildMembersToDatabase(Collection<DiscordMember> members, DiscordGuild guild) {
    members.forEach(member -> {
      var memberOptional = memberRepository.findByDiscordIdAndGuildId(member.getDiscordId(), guild.getGuildId());
      if (memberOptional.isPresent()) {
        if (discordMemberHasChanged(memberOptional.get(), member)) {
          patchGuildMember(memberOptional.get(), member);
        }
      } else {
        createGuildMember(member, guild.getGuildId());
      }
    });
  }

  private void patchGuildMember(DiscordMember oldMember, DiscordMember newMember) {
    oldMember.setNickname(newMember.getNickname());

    memberRepository.save(oldMember);
    log.info("Updated DiscordMember with id %d from guild with id %d"
      .formatted(oldMember.getDiscordId(), oldMember.getGuildId()));
  }

  private void createGuildMember(DiscordMemberDto memberDto, Long guildId) {
    var member = memberMapper.mapDtoToEntity(memberDto);
    member.setGuildId(guildId);

    memberRepository.save(member);
    log.info("Created new DiscordMember with id %d in guild with id %d"
      .formatted(member.getDiscordId(), member.getGuildId()));
  }

  private void createGuildMember(DiscordMember member, Long guildId) {
    member.setGuildId(guildId);

    memberRepository.save(member);
    log.info("Created new DiscordMember with id %d in guild with id %d"
      .formatted(member.getDiscordId(), member.getGuildId()));
  }

  private boolean discordMemberHasChanged(DiscordMember oldMember, DiscordMember newMember) {
    return !(
      oldMember.getNickname().equals(newMember.getNickname())
    );
  }
}
