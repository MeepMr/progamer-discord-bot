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

Environment.readEnvFile();
const discordInit = new DiscordInitializer(Environment.BOT_TOKEN);
const guildFetcher = new GuildFetcher(discordInit.discordClient);
const channelFetcher = new ChannelFetcher();
const channelRouter = new ChannelRoutingModule(channelFetcher, guildFetcher);
const expressApplication = new ExpressInitializer(Environment.APPLICATION_PORT);

expressApplication.expressApp.get('/guild', (req, res) => {
  guildFetcher.getAllGuilds$()
    .pipe(
      mergeMap(users => users),
      map(guild => {
        return channelFetcher.getVoiceChannelsFromGuild$(guild)
          .pipe(
            map(channels => GuildMapper.mapToDto(guild, ChannelMapper.mapChannelsToDto(channels))),
          );
      }),
      toArray(),
      take(1),
    )
    .subscribe(dtos => res.send(dtos));
});

expressApplication.expressApp.use('/channel', channelRouter.registerChannelRouter());
