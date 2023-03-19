package de.justinklein.progamerSpringBackend.birthdaybot.birthdayConfig;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BirthdayConfig {
  @Id
  private Long id;
  private Long birthdayChannelId;
  private boolean enabled;
  @NotBlank
  @Column(columnDefinition = "varchar(255) default 'Happy Birthday, ${USER_NAME}'", nullable = false)
  private String birthdayMessage;
}
