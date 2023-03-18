import { NextFunction, Request, Response, Router } from 'express';
import { GuildFetcher } from '../discord-fetcher/guild-fetcher';
import { concatMap, map, mergeMap, take, toArray } from 'rxjs';
import { ChannelMapper } from '../mapper/ChannelMapper';
import { GuildMapper } from '../mapper/GuildMapper';
import { ChannelFetcher } from '../discord-fetcher/channel-fetcher';
import { MemberMapper } from '../mapper/MemberMapper';

export class GuildRoutingModule {
  private static guildRouter: Router;
  private static guildFetcher: GuildFetcher;
  private static channelFetcher: ChannelFetcher;

  static registerRouter(guildFetcher: GuildFetcher, channelFetcher: ChannelFetcher): Router {
    this.guildRouter = Router();
    this.guildFetcher = guildFetcher;
    this.channelFetcher = channelFetcher;
    this.guildRouter.get('/', this.getAllGuilds);
    this.guildRouter.get('/:guildId/member', this.getMembersFromGuild);
    return this.guildRouter;
  }

  static getAllGuilds(req: Request, res: Response, next: NextFunction): void {
    GuildRoutingModule.guildFetcher.getAllGuilds$()
      .pipe(
        mergeMap(guilds => guilds),
        mergeMap(guild => {
          return GuildRoutingModule.channelFetcher.getVoiceChannelsFromGuild$(guild)
            .pipe(
              map(channels => GuildMapper.mapToDto(guild, ChannelMapper.mapChannelsToDto(channels))),
            );
        }),
        toArray(),
        take(1),
      )
      .subscribe(dtos => {
        res.send(dtos);
      }, next);
  };

  static getMembersFromGuild(req: Request, res: Response, next: NextFunction): void {
    const guildId = req.params.guildId;

    GuildRoutingModule.guildFetcher.getGuild$(guildId).pipe(
      concatMap(guild => {
        return GuildRoutingModule.guildFetcher.getMembersFromGuild$(guild.id);
      }),
      concatMap(members => members),
      map(member => MemberMapper.mapToDto(member)),
      toArray(),
    ).subscribe(members => res.send({
      guildId: guildId,
      guildMembers: members,
    }), next);
  }
}
