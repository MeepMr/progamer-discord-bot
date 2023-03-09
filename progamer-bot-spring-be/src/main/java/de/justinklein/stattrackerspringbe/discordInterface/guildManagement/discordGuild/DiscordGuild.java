package de.justinklein.stattrackerspringbe.discordInterface.guildManagement.discordGuild;

import de.justinklein.stattrackerspringbe.birthdaybot.birthdayConfig.BirthdayConfig;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class DiscordGuild {
  @Id
  private Long guildId;
  private String guildName;
  @OneToOne
  private BirthdayConfig birthdayConfig;
}
