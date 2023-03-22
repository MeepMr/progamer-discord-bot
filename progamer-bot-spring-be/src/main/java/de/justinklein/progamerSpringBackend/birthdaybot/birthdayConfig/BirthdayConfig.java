package de.justinklein.progamerSpringBackend.birthdaybot.birthdayConfig;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BirthdayConfig {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long birthdayChannelId;
  @Column(columnDefinition = "boolean default false", nullable = false)
  private boolean enabled;
  @NotBlank
  @Column(columnDefinition = "varchar(255) default 'Happy Birthday, ${USER_NAME}'", nullable = false)
  private String birthdayMessage = "Happy Birthday, ${USER_NAME}";
}
