package de.justinklein.stattrackerspringbe.birthdaybot.birthdayConfig;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthdayConfigRepository extends JpaRepository<BirthdayConfig, Long> {
}
