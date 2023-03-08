import {ChannelType} from "../ChannelType";

export class ChannelDto {
    public id: string;
    public descriptor: string;
    public type: ChannelType

    constructor(id: string, descriptor: string, type: ChannelType) {
        this.id = id;
        this.descriptor = descriptor;
        this.type = type;
    }
}