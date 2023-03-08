// import { ChannelFetcher } from '../discord-fetcher/channel-fetcher';
// import { GuildFetcher } from '../discord-fetcher/guild-fetcher';
// import { TextChannel } from 'discord.js';
// import { map, Observable } from 'rxjs';
//
// export class MessageSender {
//
//   constructor(private channelFetcher: ChannelFetcher, private guildFetcher: GuildFetcher) {
//   }
//
//   sendMessageToChannel$(channelId: string): Observable<void> {
//     return this.guildFetcher.getAllGuilds$().pipe(
//       map(guilds => {
//         const matchingChannels: TextChannel[] = [];
//         return guilds.forEach(guild => matchingChannels.push(this.channelFetcher.getTextChannelById$(
//           channelId,
//           guild,
//         )));
//       }),
//     );
//   }
// }
