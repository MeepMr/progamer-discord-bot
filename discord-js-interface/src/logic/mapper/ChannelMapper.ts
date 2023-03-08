import Discord, {GuildBasedChannel} from "discord.js";
import {ChannelDto} from "../../model/dto/ChannelDto";
import {ChannelType} from "../../model/ChannelType";
import {ChannelWithMembersDto} from "../../model/dto/ChannelWithMembersDto";
import {MemberDto} from "../../model/dto/MemberDto";

export class ChannelMapper {
    public static mapChannelToDto = (channel: GuildBasedChannel): ChannelDto => {
        return new ChannelDto(channel.id, channel.name, ChannelMapper.parseChannelType(channel.type));
    }

    public static mapChannelsToDto = (channels: Iterable<GuildBasedChannel>): Iterable<ChannelDto> => {
        let channelDtos = [];
        for (const channel of channels) {
            channelDtos.push(ChannelMapper.mapChannelToDto(channel));
        }
        return channelDtos;
    };

    public static mapChannelToDtoWithMembers = (channel: GuildBasedChannel, members: MemberDto[]): ChannelWithMembersDto => {
        return new ChannelWithMembersDto(channel.id, channel.name, ChannelMapper.parseChannelType(channel.type), members);
    };

    private static parseChannelType = (discordChannelType: Discord.ChannelType): ChannelType => {
        switch (discordChannelType) {
            case Discord.ChannelType.GuildVoice:
                return ChannelType.Voice;
            case Discord.ChannelType.GuildText:
                return ChannelType.Text;
            default:
                return ChannelType.Undefined;
        }
    }
}