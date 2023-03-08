import { Request, Response, Router } from 'express';
import { ChannelFetcher } from '../discord-fetcher/channel-fetcher';

export class MessageRoutingModule {

  private messageRouter: Router;

  constructor(channelFetcher: ChannelFetcher) {
    this.messageRouter = Router();
  }

  registerChannelRouter(): Router {
    const channelRouter = Router();

    channelRouter.get('/send/:channelId', this.sendMessage);

    return channelRouter;
  }

  async sendMessage(req: Request, res: Response) {
    const channelId = req.params.channelId;
  }
}
