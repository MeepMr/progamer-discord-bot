import { Request, Response, Router } from 'express';
import { MessageSender } from '../discord-interactor/message-sender';

export class MessageRoutingModule {
  private static messageRouter: Router;
  private static messageSender: MessageSender;

  static registerRouter(messageSender: MessageSender): Router {
    this.messageRouter = Router();
    this.messageSender = messageSender;
    this.messageRouter.post('/send/', this.sendMessage);
    return this.messageRouter;
  }

  private static sendMessage(req: Request, res: Response) {
    const channelId = req.body.channelId!;
    const message = req.body.messageText!;
    MessageRoutingModule.messageSender.sendMessageToChannel$(channelId, message)
      .subscribe(() => res.send());
  }
}
