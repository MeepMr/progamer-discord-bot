package de.justinklein.stattrackerspringbe.discordInterface.memberManagement;

import de.justinklein.stattrackerspringbe.discordInterface.DiscordInterfaceConnector;
import de.justinklein.stattrackerspringbe.discordInterface.memberManagement.discordmember.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class DiscordMemberFetcher {
  private final DiscordInterfaceConnector discordInterface;
  private final DiscordMemberMapper memberMapper;
  private final DiscordMemberRepository memberRepository;

  public void updateAllMembersFromGuild() {
    var memberDtosOptional = discordInterface.getDiscordMembersFromGuild("1033155520758042675");
    memberDtosOptional.ifPresent(this::saveGuildMembersToDatabase);
  }

  public void saveGuildMembersToDatabase(DiscordGuildMembersDto membersDto) {
    membersDto.getGuildMembers().forEach(memberDto -> {
      var memberOptional = memberRepository.findByDiscordIdAndGuildId(memberDto.getId(), membersDto.getGuildId());
      if (memberOptional.isPresent()) {
        if (discordMemberHasChanged(memberOptional.get(), memberDto)) {
          patchGuildMember(memberOptional.get(), memberDto);
        }
      } else {
        createGuildMember(memberDto, membersDto.getGuildId());
      }
    });
  }

  private void patchGuildMember(DiscordMember member, DiscordMemberDto memberDto) {
    member.setNickname(memberDto.getName());

    memberRepository.save(member);
    log.info("Updated DiscordMember with id %d from guild with id %d"
      .formatted(member.getDiscordId(), member.getGuildId()));
  }

  private void createGuildMember(DiscordMemberDto memberDto, Long guildId) {
    var member = memberMapper.mapDtoToEntity(memberDto);
    member.setGuildId(guildId);

    memberRepository.save(member);
    log.info("Created new DiscordMember with id %d in guild with id %d"
      .formatted(member.getDiscordId(), member.getGuildId()));
  }

  private boolean discordMemberHasChanged(DiscordMember discordMember, DiscordMemberDto memberDto) {
    return !(
      discordMember.getNickname().equals(memberDto.getName())
    );
  }
}
