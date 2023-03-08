package de.justinklein.stattrackerspringbe.discordInterface.memberManagement;

import de.justinklein.stattrackerspringbe.discordInterface.memberManagement.model.DiscordMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscordMemberRepository extends JpaRepository<DiscordMember, String> {
  Optional<DiscordMember> findByDiscordIdAndGuildId(Long discordId, Long guildId);
}
