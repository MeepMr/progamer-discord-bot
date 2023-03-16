import { NextFunction, Request, Response, Router } from 'express';
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

  private static sendMessage(req: Request, res: Response, next: NextFunction) {
    const channelId = req.body.channelId!;
    const message = req.body.messageText!;
    console.log('Sending Message to Channel ' + channelId);
    MessageRoutingModule.messageSender.sendMessageToChannel$(channelId, message)
      .subscribe(() => res.send(), next);
  }
}
