import {Guild} from "discord.js";
import {GuildDto} from "../../model/dto/GuildDto";
import {ChannelDto} from "../../model/dto/ChannelDto";

export class GuildMapper {
    public static mapToDto = (guild: Guild, channels: Iterable<ChannelDto>): GuildDto => {
        return new GuildDto(guild.id, guild.name, channels);
    }
}