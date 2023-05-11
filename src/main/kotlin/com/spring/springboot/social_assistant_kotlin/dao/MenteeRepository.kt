package com.spring.springboot.social_assistant_kotlin.dao

import com.spring.springboot.social_assistant_kotlin.entity.Mentee
import com.spring.springboot.social_assistant_kotlin.entity.SocialWorker
import org.springframework.data.jpa.repository.JpaRepository

interface MenteeRepository : JpaRepository<Mentee, Int> {
    fun findAllBySocialWorker(socialWorker: SocialWorker) : List<Mentee>
}