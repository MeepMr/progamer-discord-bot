package de.justinklein.progamerSpringBackend.discordInterface.memberManagement.discordMember;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscordMemberDto {
  @Nonnull
  private Long id;
  @Nonnull
  private String name;
}
