import { NextFunction, Request, Response, Router } from 'express';
import { ChannelFetcher } from '../discord-fetcher/channel-fetcher';
import { GuildFetcher } from '../discord-fetcher/guild-fetcher';
import { ChannelWithMembersDto } from '../../model/dto/ChannelWithMembersDto';
import { ChannelMapper } from '../mapper/ChannelMapper';
import { concatMap, map } from 'rxjs';

export class ChannelRoutingModule {

  private static channelRouter: Router;
  private static channelFetcher: ChannelFetcher;
  private static guildFetcher: GuildFetcher;

  static registerRouter(channelFetcher: ChannelFetcher, guildFetcher: GuildFetcher): Router {
    this.channelRouter = Router();
    this.channelFetcher = channelFetcher;
    this.guildFetcher = guildFetcher;
    this.channelRouter.get('/voice', this.getAllVoiceChannels);
    this.channelRouter.get('/text', this.getAllTextChannels);
    return this.channelRouter;
  }

  private static getAllTextChannels(req: Request, res: Response, next: NextFunction) {
    ChannelRoutingModule.guildFetcher.getAllGuilds$().pipe(
      concatMap(guilds => {
        return ChannelRoutingModule.channelFetcher.getTextChannelsFromGuild$(guilds[0]);
      }),
      map(channels => {
        let channelDtos: ChannelWithMembersDto[] = [];
        for (const channel of channels) {
          const members = ChannelRoutingModule.channelFetcher.getMembersFromTextChannel(channel);
          channelDtos.push(ChannelMapper.mapChannelToDtoWithMembers(channel, members));
        }
        return channelDtos;
      }),
    ).subscribe(channelDtos => res.send(channelDtos), next);
  }

  private static getAllVoiceChannels(req: Request, res: Response, next: NextFunction) {
    ChannelRoutingModule.guildFetcher.getAllGuilds$().pipe(
      concatMap(guilds => {
        return ChannelRoutingModule.channelFetcher.getVoiceChannelsFromGuild$(guilds[0]);
      }),
      map(channels => {
        let channelDtos: ChannelWithMembersDto[] = [];
        for (const channel of channels) {
          const members = ChannelRoutingModule.channelFetcher.getMembersFromVoiceChannel(channel);
          channelDtos.push(ChannelMapper.mapChannelToDtoWithMembers(channel, members));
        }
        return channelDtos;
      }),
    ).subscribe(channelDtos => res.send(channelDtos), next);
  }
}
