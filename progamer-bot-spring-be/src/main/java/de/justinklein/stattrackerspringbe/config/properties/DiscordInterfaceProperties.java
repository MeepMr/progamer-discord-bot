package de.justinklein.stattrackerspringbe.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "stat-tracker.discord-interface")
@Data
public class DiscordInterfaceProperties {
    private String url;
}
