import { Request, Response, Router } from 'express';
import { ChannelFetcher } from '../discord-fetcher/channel-fetcher';
import { GuildFetcher } from '../discord-fetcher/guild-fetcher';

export class ChannelRoutingModule {

  private static channelRouter: Router;
  private static channelFetcher: ChannelFetcher;
  private static guildFetcher: GuildFetcher;

  static registerRouter(channelFetcher: ChannelFetcher, guildFetcher: GuildFetcher): Router {
    this.channelRouter = Router();
    this.channelFetcher = channelFetcher;
    this.guildFetcher = guildFetcher;
    this.channelRouter.get('/voice', this.getAllVoiceChannels);
    return this.channelRouter;
  }

  private static getAllVoiceChannels(req: Request, res: Response) {
    // let channelDtos: ChannelWithMembersDto[] = [];
    // const guilds = await this.guildFetcher.getAllGuilds();
    // const channels = await this.channelFetcher.getVoiceChannelsFromGuild(guilds[0]);
    //
    // for (const channel of channels) {
    //   const members = await this.channelFetcher.getMembersFromChannel(channel);
    //   channelDtos.push(ChannelMapper.mapChannelToDtoWithMembers(channel, members));
    // }
    //
    // res.send(channelDtos);
    console.log(ChannelRoutingModule.channelFetcher);
    res.send();
  }
}
