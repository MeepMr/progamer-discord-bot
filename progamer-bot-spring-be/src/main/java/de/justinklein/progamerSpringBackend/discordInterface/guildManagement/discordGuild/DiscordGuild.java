package de.justinklein.progamerSpringBackend.discordInterface.guildManagement.discordGuild;

import de.justinklein.progamerSpringBackend.birthdaybot.birthdayConfig.BirthdayConfig;
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
