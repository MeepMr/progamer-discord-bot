package de.justinklein.progamerSpringBackend.discordInterface.memberManagement.discordMember;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscordMember {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID Id;
  @Nonnull
  private Long discordId;
  @Nonnull
  private Long guildId;
  @Nonnull
  private String nickname;
  @Nullable
  private Date birthDate;
}
