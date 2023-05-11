package com.spring.springboot.social_assistant_kotlin.service

import com.spring.springboot.social_assistant_kotlin.entity.Mentee
import com.spring.springboot.social_assistant_kotlin.entity.SocialWorker
import java.util.Optional

interface MenteeService {
    fun getAllMentees(): List<Mentee>
    fun saveMentee(mentee: Mentee)
    fun getMentee(id: Int): Optional<Mentee>
    fun deleteMentee(id: Int)
    fun findAllBySocialWorker(socialWorker: SocialWorker): List<Mentee>
}