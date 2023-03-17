package de.justinklein.progamerSpringBackend.birthdaybot.birthdayConfig;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
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
  @NotNull
  private String birthdayMessage;
}
