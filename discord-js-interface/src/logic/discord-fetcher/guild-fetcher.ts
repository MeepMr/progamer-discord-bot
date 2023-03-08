import { Client, Guild } from 'discord.js';
import { concatMap, defer, from, Observable } from 'rxjs';

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
}
