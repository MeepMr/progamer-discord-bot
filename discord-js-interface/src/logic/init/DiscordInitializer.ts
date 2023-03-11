import Discord, { Client, Events, GatewayIntentBits } from 'discord.js';

export class DiscordInitializer {

  public discordClient: Client;

  constructor(loginToken: string) {
    this.discordClient = new Discord.Client({
      intents: [
        GatewayIntentBits.Guilds,
        GatewayIntentBits.GuildVoiceStates,
        GatewayIntentBits.GuildMembers,
      ],
    });
    this.discordClient.on(Events.ClientReady, () => {
      console.log('Discord bot ready');
    });
    this.login(loginToken);
  }

  private login = (loginToken: string): void => {
    this.discordClient.login(loginToken).then(() => {
      console.log('Client successfully logged in!');
    });
  };
}
