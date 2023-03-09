package de.justinklein.stattrackerspringbe.birthdaybot.birthdayConfig;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BirthdayConfig {
  @Id
  private Long id;
  private Long birthdayChannelId;
  private boolean enabled;
}
