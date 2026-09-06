package com.mini.project.controllers;

import com.mini.project.dto.MemberDTO;
import com.mini.project.entities.Member;
import com.mini.project.services.MemberService;
import jakarta.validation.Valid;
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
    public Long addMember(@Valid @RequestBody MemberDTO dto) {
        return memberService.addMember(
                dto.getMemberName(),
                dto.getMemberEmail(),
                dto.getMemberPhoneNumber(),
                dto.getMembershipType());
    }

    @GetMapping("getAll")
    public List<MemberDTO> getAllMembers() {
        List<MemberDTO> members = MemberDTO.convertToDTO(memberService.getAllMembers());
        return members;
    }

    @GetMapping("getById")
    public MemberDTO getById(@RequestParam Long id) {
        return MemberDTO.convertToDTO(memberService.getById(id));
    }

    @PutMapping("update")
    public MemberDTO updateMember(@Valid @RequestBody MemberDTO dto) throws Exception {
        return MemberDTO.convertToDTO(memberService.updateMember(
                dto.getMemberId(),
                dto.getMemberName(),
                dto.getMemberEmail(),
                dto.getMemberPhoneNumber(),
                dto.getMembershipType()));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteMember(@RequestParam Long id) {
        return memberService.deleteById(id);
    }
}
