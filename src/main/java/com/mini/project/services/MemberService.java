package com.mini.project.services;

import com.mini.project.entities.Author;
import com.mini.project.entities.Member;
import com.mini.project.repositories.AuthorRepository;
import com.mini.project.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //Add service
    public Long addMember(String name, String email, String phoneNumber,String membershipType ){
        Member member =  new Member();
        member.setIsActive(true);
        member.setCreatedDate(new Date());
        member.setName(name);
        member.setEmail(email);
        member.setPhoneNumber(phoneNumber);
        member.setMembershipType(membershipType);
        member = memberRepository.save(member);
        return member.getId();
    }

    //Get All members service
    public List<Member> getAllMembers() {
        return memberRepository.getAllMembers();
    }

    //Get Member By Id service
    public Member getById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent() && member.get().getIsActive()) {
            return member.get();
        }
        return new Member();
    }

    //Update service
    public Member updateMember(Long id, String updateName, String updateEmail, String updatePhoneNumber,String updateMembershipType ) throws Exception{
        Member memberToUpdate =  memberRepository.getById(id);
        if(memberToUpdate==null){
            throw new Exception("Member is not found by the id");
        }
        memberToUpdate.setUpdatedDate(new Date());
        memberToUpdate.setName(updateName);
        memberToUpdate.setName(updateName);
        memberToUpdate.setEmail(updateEmail);
        memberToUpdate.setPhoneNumber(updatePhoneNumber);
        memberToUpdate.setMembershipType(updateMembershipType);
        memberToUpdate = memberRepository.save(memberToUpdate);
        return memberToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id){
        Member deleteMember = memberRepository.getById(id);
        if(deleteMember == null){
            return false;
        }
        deleteMember.setIsActive(false);
        deleteMember.setUpdatedDate(new Date());
        memberRepository.save(deleteMember);
        return true;
    }
}
