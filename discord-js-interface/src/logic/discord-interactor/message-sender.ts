import { ChannelFetcher } from '../discord-fetcher/channel-fetcher';
import { GuildFetcher } from '../discord-fetcher/guild-fetcher';
import { filter, map, mergeMap, Observable, take, toArray } from 'rxjs';

export class MessageSender {

  constructor(private channelFetcher: ChannelFetcher, private guildFetcher: GuildFetcher) {
  }

  sendMessageToChannel$(channelId: string, guildId: string, message: string): Observable<void> {
    return this.guildFetcher.getAllGuilds$().pipe(
      mergeMap(guilds => guilds),
      filter(guild => guild.id === guildId),
      mergeMap(guild => {
        return this.channelFetcher.getTextChannelById$(
          channelId,
          guild,
        );
      }),
      toArray(),
      map(channels => {
        if (channels.length === 1) {
          console.log('Sending Message to Channel ' + channelId);
          channels[0].send(message);
        } else {
          console.log('Error sending Message to Channel ' + channelId);
        }
      }),
      take(1),
    );
  }
}
