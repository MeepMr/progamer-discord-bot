import { ChannelFetcher } from '../discord-fetcher/channel-fetcher';
import { GuildFetcher } from '../discord-fetcher/guild-fetcher';
import { map, mergeMap, Observable, take, toArray } from 'rxjs';

export class MessageSender {

  constructor(private channelFetcher: ChannelFetcher, private guildFetcher: GuildFetcher) {
  }

  sendMessageToChannel$(channelId: string): Observable<void> {
    return this.guildFetcher.getAllGuilds$().pipe(
      mergeMap(guilds => guilds),
      mergeMap(guild => {
        return this.channelFetcher.getTextChannelById$(
          channelId,
          guild,
        );
      }),
      toArray(),
      map(channels => {
        if (channels.length === 1) {
          channels[0].send('Test');
        }
      }),
      take(1),
    );
  }
}
