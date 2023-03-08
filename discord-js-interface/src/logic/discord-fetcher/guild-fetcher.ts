import { Client, Guild, GuildMember } from 'discord.js';
import { concatMap, defer, filter, from, map, Observable, toArray } from 'rxjs';

export class GuildFetcher {

  private discordClient: Client;

  constructor(discordClient: Client) {
    this.discordClient = discordClient;
  }

  getAllGuilds$(): Observable<Guild[]> {
    return defer(() => from(this.discordClient.guilds.fetch()))
      .pipe(
        concatMap(async oAuth2Guilds => {
          let guilds: Guild[] = [];

          for (const oAuth2Guild of oAuth2Guilds.values()) {
            guilds.push(await oAuth2Guild.fetch());
          }

          return guilds;
        }),
      );
  };

  getMembersFromGuild$(guild: Guild): Observable<GuildMember[]> {
    return defer(() => from(guild.members.list()))
      .pipe(
        map(memberMap => memberMap.values()),
        map(members => {
          const guildMembers: GuildMember[] = [];

          for (let member of members) {
            guildMembers.push(member);
          }

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
