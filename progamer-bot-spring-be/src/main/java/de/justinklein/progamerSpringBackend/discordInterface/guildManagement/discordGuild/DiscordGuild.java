package de.justinklein.progamerSpringBackend.discordInterface.guildManagement.discordGuild;

import de.justinklein.progamerSpringBackend.birthdaybot.birthdayConfig.BirthdayConfig;
import de.justinklein.progamerSpringBackend.discordInterface.memberManagement.discordMember.DiscordMember;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscordGuild {
  @Id
  private Long guildId;
  private String guildName;
  @OneToOne(cascade = CascadeType.ALL)
  private BirthdayConfig birthdayConfig;
  @OneToMany(mappedBy = "guildId")
  private Collection<DiscordMember> guildMembers;
}
