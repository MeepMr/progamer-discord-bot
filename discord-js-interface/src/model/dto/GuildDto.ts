import {ChannelDto} from "./ChannelDto";

export class GuildDto {
    public id: string;
    public name: string;
    public channels: Iterable<ChannelDto>;

    constructor(id: string, name: string, channels: Iterable<ChannelDto>) {
        this.id = id;
        this.name = name;
        this.channels = channels;
    }
}