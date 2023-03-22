package de.justinklein.progamerSpringBackend.discordInterface;

import de.justinklein.progamerSpringBackend.birthdaybot.birthdayConfig.BirthdayConfig;
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

  public void updateGuildInformation() {

  }

  public void updateUserDatabase() {
    var guilds = guildService.getAllGuildsFromDatabase();
    log.info("Updating users for all guilds (%d)".formatted(guilds.size()));

    guilds.forEach(guild -> {
      var guildMembers = memberFetcher.getAllMembersFromGuild(guild);
      memberService.saveGuildMembersToDatabase(guildMembers, guild);
    });
  }

  public void fetchNewGuilds() {
    var interfaceGuilds = guildService.getAllGuildsFromInterfaceConnector();

    var newGuilds = interfaceGuilds.stream()
      .filter(guild -> !guildService.isGuildPresentInDatabase(guild.getGuildId()))
      .peek(guild -> {
        var birthdayConfig = new BirthdayConfig();
        guild.setBirthdayConfig(birthdayConfig);
      })
      .toList();

    newGuilds.forEach(guildService::saveGuild);
  }
}
