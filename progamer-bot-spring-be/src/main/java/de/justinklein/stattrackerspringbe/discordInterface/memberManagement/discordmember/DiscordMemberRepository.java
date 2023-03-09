package de.justinklein.stattrackerspringbe.discordInterface.memberManagement.discordmember;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscordMemberRepository extends JpaRepository<DiscordMember, Long> {
  Optional<DiscordMember> findByDiscordIdAndGuildId(Long discordId, Long guildId);
}
