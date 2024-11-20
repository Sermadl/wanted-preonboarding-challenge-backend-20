package org.wantedmarket.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wantedmarket.member.domain.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String id);
}
