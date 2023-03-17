package de.justinklein.progamerSpringBackend.discordInterface.memberManagement.discordMember;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiscordMemberRepository extends JpaRepository<DiscordMember, Long> {
  List<DiscordMember> findByGuildId(Long guildId);
  Optional<DiscordMember> findByDiscordIdAndGuildId(Long discordId, Long guildId);
}
