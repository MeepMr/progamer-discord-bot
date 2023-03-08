import { GatewayIntentBits } from 'discord.js';
import { DiscordInitializer } from './logic/init/DiscordInitializer';
import { Environment } from './model/Environment';
import { ExpressInitializer } from './logic/init/ExpressInitializer';
import { GuildMapper } from './logic/mapper/GuildMapper';
import { GuildFetcher } from './logic/discord-fetcher/guild-fetcher';
import { ChannelFetcher } from './logic/discord-fetcher/channel-fetcher';
import { ChannelRoutingModule } from './logic/routing/channel-routing';
import { map, mergeMap, take, toArray } from 'rxjs';
import { ChannelMapper } from './logic/mapper/ChannelMapper';
import { MessageSender } from './logic/discord-interactor/message-sender';

Environment.readEnvFile();
const discordInit = new DiscordInitializer(Environment.BOT_TOKEN);
const guildFetcher = new GuildFetcher(discordInit.discordClient);
const channelFetcher = new ChannelFetcher();
const channelRouter = new ChannelRoutingModule(channelFetcher, guildFetcher);
const expressApplication = new ExpressInitializer(Environment.APPLICATION_PORT);
const messageSender = new MessageSender(channelFetcher, guildFetcher);

expressApplication.expressApp.get('/guild', (req, res) => {
  guildFetcher.getAllGuilds$()
    .pipe(
      mergeMap(guilds => guilds),
      mergeMap(guild => {
        return channelFetcher.getVoiceChannelsFromGuild$(guild)
          .pipe(
            map(channels => {
              const t = GuildMapper.mapToDto(guild, ChannelMapper.mapChannelsToDto(channels));
              console.log(t);
              return t;
            }),
          );
      }),
      toArray(),
      take(1),
    )
    .subscribe(dtos => {
      console.log(dtos);
      res.send(dtos);
    });
});

expressApplication.expressApp.get('/message', (req, res) => {
  messageSender.sendMessageToChannel$('1047947055580057650').subscribe(() => res.send());
});

expressApplication.expressApp.use('/channel', channelRouter.registerRouter());
