package de.justinklein.stattrackerspringbe.discordInterface.guildManagement.discordGuild;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscordGuildRepository extends JpaRepository<DiscordGuild, Long> {
}
