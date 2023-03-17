package de.justinklein.progamerSpringBackend.discordInterface;

import de.justinklein.progamerSpringBackend.discordInterface.guildManagement.DiscordGuildService;
import de.justinklein.progamerSpringBackend.discordInterface.memberManagement.DiscordMemberFetcher;
import de.justinklein.progamerSpringBackend.discordInterface.memberManagement.DiscordMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscordUpdater {
  private final DiscordGuildService guildService;
  private final DiscordMemberService memberService;
  private final DiscordMemberFetcher memberFetcher;

  public void updateUserDatabase() {
    var guilds = guildService.getAllGuilds();
    log.info("Updating users for all guilds (%d)".formatted(guilds.size()));

    guilds.forEach(guild -> {
      var guildMembers = memberFetcher.getAllMembersFromGuild(guild);
      memberService.saveGuildMembersToDatabase(guildMembers, guild);
    });
  }
}
