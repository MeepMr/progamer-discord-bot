import { Client, Guild, GuildMember } from 'discord.js';
import { concatMap, defer, filter, from, map, Observable, of, toArray } from 'rxjs';

export class GuildFetcher {

  private discordClient: Client;

  constructor(discordClient: Client) {
    this.discordClient = discordClient;
  }

  getAllGuilds$(): Observable<Guild[]> {
    return of(this.discordClient.guilds.cache)
      .pipe(
        map(guildsMap => {
          let guilds: Guild[] = [];

          guildsMap.forEach(guild => {
            guilds.push(guild);
          });

          return guilds;
        }),
      );
  };

  getMembersFromGuild$(guildId: string): Observable<GuildMember[]> {
    return defer(() => from(this.discordClient.guilds.cache.get(guildId)!.members.fetch()))
      .pipe(
        map(memberMap => {
          const guildMembers: GuildMember[] = [];

          memberMap.forEach(member => {
            guildMembers.push(member);
            console.log(member.id);
          });

          return guildMembers;
        }),
      );
  };

  getGuild$(guildId: string): Observable<Guild> {
    return this.getAllGuilds$().pipe(
      concatMap(guilds => guilds),
      filter(guild => guild.id === guildId),
      toArray(),
      map(matchingGuilds => matchingGuilds[0]),
    );
  }
}
