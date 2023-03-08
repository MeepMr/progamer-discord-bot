import {GuildMember} from "discord.js";
import {MemberDto} from "../../model/dto/MemberDto";

export class MemberMapper {
    public static mapToDto = (member: GuildMember): MemberDto => {
        return new MemberDto(member.id, member.displayName);
    }
}