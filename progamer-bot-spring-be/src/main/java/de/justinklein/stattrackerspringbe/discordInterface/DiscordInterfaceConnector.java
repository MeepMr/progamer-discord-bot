package de.justinklein.stattrackerspringbe.discordInterface;

import de.justinklein.stattrackerspringbe.config.properties.DiscordInterfaceProperties;
import de.justinklein.stattrackerspringbe.discordInterface.sendMessage.SendMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscordInterfaceConnector {

  private final DiscordInterfaceProperties interfaceProperties;

  public void sendMessage(SendMessageDto messageDto) {
    var client = WebClient.create(interfaceProperties.getUrl());

    log.info("Sending message to channel with id %s".formatted(messageDto.getChannelId()));

    client.post()
      .uri("/message/send")
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .body(Mono.just(messageDto), SendMessageDto.class)
      .retrieve()
      .bodyToMono(String.class)
      .block();

  }
}
