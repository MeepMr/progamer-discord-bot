import { GatewayIntentBits } from 'discord.js';
import { DiscordInitializer } from './logic/init/DiscordInitializer';
import { Environment } from './model/Environment';
import { ExpressInitializer } from './logic/init/ExpressInitializer';
import { GuildFetcher } from './logic/discord-fetcher/guild-fetcher';
import { ChannelFetcher } from './logic/discord-fetcher/channel-fetcher';
import { ChannelRoutingModule } from './logic/routing/channel-routing';
import { MessageSender } from './logic/discord-interactor/message-sender';
import { MessageRoutingModule } from './logic/routing/message-routing';
import { GuildRoutingModule } from './logic/routing/guild-routing';

Environment.readEnvFile();
const discordInit = new DiscordInitializer(Environment.BOT_TOKEN);
const expressApplication = new ExpressInitializer(Environment.APPLICATION_PORT);
const guildFetcher = new GuildFetcher(discordInit.discordClient);
const channelFetcher = new ChannelFetcher();
const messageSender = new MessageSender(channelFetcher, guildFetcher);

expressApplication.expressApp.use('/channel', ChannelRoutingModule.registerRouter(channelFetcher, guildFetcher));
expressApplication.expressApp.use('/message', MessageRoutingModule.registerRouter(messageSender));
expressApplication.expressApp.use('/guild', GuildRoutingModule.registerRouter(guildFetcher, channelFetcher));
