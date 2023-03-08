import {ChannelType} from "../ChannelType";
import {MemberDto} from "./MemberDto";

export class ChannelWithMembersDto {
    public id: string;
    public descriptor: string;
    public type: ChannelType
    public members: MemberDto[]

    constructor(id: string, descriptor: string, type: ChannelType, members: MemberDto[]) {
        this.id = id;
        this.descriptor = descriptor;
        this.type = type;
        this.members = members;
    }
}