package com.spring.springboot.social_assistant_kotlin.service

import com.spring.springboot.social_assistant_kotlin.dao.MenteeRepository
import com.spring.springboot.social_assistant_kotlin.entity.Mentee
import com.spring.springboot.social_assistant_kotlin.entity.SocialWorker
import org.springframework.stereotype.Service
import java.util.*

@Service
class MenteeServiceImpl(val menteeRepository: MenteeRepository) : MenteeService {
    override fun getAllMentees(): List<Mentee> = menteeRepository.findAll()

    override fun saveMentee(mentee: Mentee) {
        menteeRepository.save(mentee)
    }

    override fun getMentee(id: Int): Optional<Mentee> = menteeRepository.findById(id)

    override fun deleteMentee(id: Int) {
        menteeRepository.deleteById(id)
    }

    override fun findAllBySocialWorker(socialWorker: SocialWorker): List<Mentee> =
        menteeRepository.findAllBySocialWorker(socialWorker)
}