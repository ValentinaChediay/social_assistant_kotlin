package com.spring.springboot.social_assistant_kotlin.service

import com.spring.springboot.social_assistant_kotlin.dao.SocialWorkerRepository
import com.spring.springboot.social_assistant_kotlin.entity.SocialWorker
import org.springframework.stereotype.Service
import java.util.*

@Service
class SocialWorkerServiceImpl(val socialWorkerRepository: SocialWorkerRepository) : SocialWorkerService {
    override fun getAllSocialWorkers(): List<SocialWorker> = socialWorkerRepository.findAll()

    override fun saveSocialWorker(socialWorker: SocialWorker) {
        socialWorkerRepository.save(socialWorker)
    }

    override fun getSocialWorker(id: Int): Optional<SocialWorker> = socialWorkerRepository.findById(id)

    override fun deleteSocialWorker(id: Int) {
        socialWorkerRepository.deleteById(id)
    }
}