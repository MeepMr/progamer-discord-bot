import { Request, Response, Router } from 'express';
import { ChannelFetcher } from '../discord-fetcher/channel-fetcher';
import { GuildFetcher } from '../discord-fetcher/guild-fetcher';

export class ChannelRoutingModule {

  private channelRouter: Router;

  constructor(private channelFetcher: ChannelFetcher, private guildFetcher: GuildFetcher) {
    this.channelRouter = Router();
  }

  registerChannelRouter(): Router {
    const channelRouter = Router();

    channelRouter.get('/channels/voice', this.getAllVoiceChannels);

    return channelRouter;
  }

  async getAllVoiceChannels(req: Request, res: Response) {
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
    res.send();
  }
}
