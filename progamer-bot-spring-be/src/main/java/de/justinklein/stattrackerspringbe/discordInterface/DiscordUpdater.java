package de.justinklein.stattrackerspringbe.discordInterface;

import de.justinklein.stattrackerspringbe.discordInterface.guildManagement.DiscordGuildService;
import de.justinklein.stattrackerspringbe.discordInterface.memberManagement.DiscordMemberFetcher;
import de.justinklein.stattrackerspringbe.discordInterface.memberManagement.DiscordMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscordUpdater {
  private final DiscordGuildService guildService;
  private final DiscordMemberService memberService;
  private final DiscordMemberFetcher memberFetcher;

  public void updateUserDatabase() {
    guildService.getAllGuilds().forEach(guild -> {
      var guildMembers = memberFetcher.getAllMembersFromGuild(guild);
      memberService.saveGuildMembersToDatabase(guildMembers, guild);
    });
  }
}
