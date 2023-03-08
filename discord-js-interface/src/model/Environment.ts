import dotenv from 'dotenv';

export class Environment {
  public static BOT_TOKEN: string;
  public static APPLICATION_PORT: string;

  public static readEnvFile = (): void => {
    dotenv.config({ path: './../stat-tracker-docker/.env' });
    Environment.BOT_TOKEN = process.env.DISCORD_INTERFACE_BOT_TOKEN!;
    Environment.APPLICATION_PORT = process.env.DISCORD_INTERFACE_PORT!;
  };
}
