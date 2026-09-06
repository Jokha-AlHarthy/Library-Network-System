package com.mini.project.controllers;

import com.mini.project.entities.Member;
import com.mini.project.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("member")
public class MemberController {
    MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("add")
    public Long addMember(@RequestParam String name,
                          @RequestParam String email,
                          @RequestParam String phoneNumber,
                          @RequestParam String membershipType) {
        return memberService.addMember(name, email, phoneNumber, membershipType);
    }

    @GetMapping("getAll")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("getById")
    public Member getById(@RequestParam Long id) {
        return memberService.getById(id);
    }

    @PutMapping("update")
    public Member updateMember(@RequestParam Long id,
                               @RequestParam String updateName,
                               @RequestParam String updateEmail,
                               @RequestParam String updatePhoneNumber,
                               @RequestParam String updateMembershipType) throws Exception {
        return memberService.updateMember(id, updateName, updateEmail,
                updatePhoneNumber, updateMembershipType);
    }

    @DeleteMapping("deleteById")
    public Boolean deleteMember(@RequestParam Long id) {
        return memberService.deleteById(id);
    }
}
