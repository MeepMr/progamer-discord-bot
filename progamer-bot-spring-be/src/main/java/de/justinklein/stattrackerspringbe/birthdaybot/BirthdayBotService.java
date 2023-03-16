package de.justinklein.stattrackerspringbe.birthdaybot;

import de.justinklein.stattrackerspringbe.birthdaybot.birthdayConfig.BirthdayConfigRepository;
import de.justinklein.stattrackerspringbe.discordInterface.guildManagement.DiscordGuildService;
import de.justinklein.stattrackerspringbe.discordInterface.guildManagement.discordGuild.DiscordGuild;
import de.justinklein.stattrackerspringbe.discordInterface.memberManagement.DiscordMemberService;
import de.justinklein.stattrackerspringbe.discordInterface.memberManagement.discordMember.DiscordMember;
import de.justinklein.stattrackerspringbe.discordInterface.messageSending.DiscordMessageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class BirthdayBotService {
  private final DiscordGuildService guildService;
  private final DiscordMemberService memberService;
  private final BirthdayConfigRepository configRepository;
  private final DiscordMessageSender messageSender;

  public void sendBirthdayMessages() {
    guildService.getAllGuilds().forEach(guild -> {
      getMembersWhereBirthdayIsTodayFromGuild(guild).forEach(member -> {
        sendBirthdayMessageToMember(member, guild);
      });
    });
  }

  private void sendBirthdayMessageToMember(DiscordMember member, DiscordGuild guild) {
    log.info("Sending birthday-Messages to member %d from guild %d".formatted(
      member.getDiscordId(),
      guild.getGuildId()
    ));

    messageSender.sendMessage(
      guild.getGuildId(),
      guild.getBirthdayConfig().getBirthdayChannelId(),
      "Test-Birthday-Message"
    );
  }

  public void setBirthdayChannel(Long guildId, Long channelId) {
    var config = configRepository.getReferenceById(guildId);
    config.setBirthdayChannelId(channelId);
    configRepository.save(config);

    log.info("Updating the birthday-channel for guild with id: %d".formatted(guildId));
  }

  public void toggleBirthdayBotForGuild(Long guildId) {
    var config = configRepository.getReferenceById(guildId);
    var currentState = config.isEnabled();

    config.setEnabled(!currentState);
    configRepository.save(config);

    log.info("Toggling the birthday-bot functionality for guild with id: %d".formatted(guildId));
  }

  private Collection<DiscordMember> getMembersWhereBirthdayIsTodayFromGuild(DiscordGuild guild) {
    var today = Calendar.getInstance();
    today.setTime(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));

    var members = memberService.getAllMembersWithBirthDayFromGuild(guild.getGuildId()).stream()
      .filter(member -> {
        var birthDate = Calendar.getInstance();
        birthDate.setTime(member.getBirthDate());

        return today.get(Calendar.MONTH) == birthDate.get(Calendar.MONTH) &&
          today.get(Calendar.DAY_OF_MONTH) == birthDate.get(Calendar.DAY_OF_MONTH);
      })
      .toList();

    members.forEach(System.out::println);
    return members;
  }
}
