import express from 'express';

export class ExpressInitializer {
  public expressApp: express.Application;
  port: string;

  constructor(port: string) {
    this.expressApp = express();
    this.port = port;
    this.startExpressWebServer();
    this.expressApp.use(express.json());
  }

  startExpressWebServer = (): void => {
    this.expressApp.listen(this.port, () => {
      console.log(`Listening to http://localhost:${this.port}/`);
    });
  };
}
