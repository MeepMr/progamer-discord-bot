import {
  BaseGuildTextChannel,
  BaseGuildVoiceChannel,
  Guild,
  GuildBasedChannel,
  GuildChannel,
  TextChannel,
  VoiceChannel,
} from 'discord.js';
import { MemberDto } from '../../model/dto/MemberDto';
import { MemberMapper } from '../mapper/MemberMapper';
import { defer, from, map, Observable } from 'rxjs';

export class ChannelFetcher {

  public getTextChannelsFromGuild$(guild: Guild): Observable<TextChannel[]> {
    return this.getGuildChannelsFromGuild$(guild).pipe(
      map(channels => channels
        .filter(channel => channel.isTextBased())
        .map(channel => channel as TextChannel)),
    );
  };

  public getVoiceChannelsFromGuild$(guild: Guild): Observable<VoiceChannel[]> {
    return this.getGuildChannelsFromGuild$(guild).pipe(
      map(channels => channels.filter(channel => channel.isVoiceBased()).map(channel => channel as VoiceChannel)),
    );
  };

  public getMembersFromChannel(channel: GuildBasedChannel): MemberDto[] {
    if (channel.isVoiceBased()) {
      return this.getMembersFromVoiceChannel(channel as BaseGuildVoiceChannel);
    } else if (channel.isTextBased()) {
      return this.getMembersFromTextChannel(channel as BaseGuildTextChannel);
    }
    return [];
  };

  public getTextChannelById$(channelId: string, guild: Guild): Observable<TextChannel> {
    return this.getGuildChannelsFromGuild$(guild).pipe(
      map(channels => {
        channels
          .filter(channel => channel.isTextBased())
          .filter(channel => channel.id === channelId);
        return channels.pop() as TextChannel;
      }),
    );
  }

  private getGuildChannelsFromGuild$(guild: Guild): Observable<GuildChannel[]> {
    return defer(() => from(guild.channels.fetch())
      .pipe(
        map(channels => {
          let guildChannels: GuildChannel[] = [];
          for (let channel of channels.values()) {
            if (channel !== null) guildChannels.push(channel);
          }
          return guildChannels;
        }),
      ),
    );
  }

  private getMembersFromVoiceChannel(voiceChannel: BaseGuildVoiceChannel): MemberDto[] {
    let members: MemberDto[] = [];

    if (voiceChannel.members.size > 0) {
      for (const voiceChannelMember of voiceChannel.members.values()) {
        members.push(MemberMapper.mapToDto(voiceChannelMember));
      }
    }

    return members;
  };

  private getMembersFromTextChannel(textChannel: BaseGuildTextChannel): MemberDto[] {
    let members: MemberDto[] = [];

    if (textChannel.members.size > 0) {
      for (const voiceChannelMember of textChannel.members.values()) {
        members.push(MemberMapper.mapToDto(voiceChannelMember));
      }
    }

    return members;
  };
}
