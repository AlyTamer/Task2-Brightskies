package com.aly.brightskies.task2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aly.brightskies.task2.springdata.entities.Member;
public interface MemberRepo extends JpaRepository<Member, Long> {
}
