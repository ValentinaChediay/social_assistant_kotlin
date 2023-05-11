package com.spring.springboot.social_assistant_kotlin.service

import com.spring.springboot.social_assistant_kotlin.entity.SocialWorker
import java.util.Optional

interface SocialWorkerService {
    fun getAllSocialWorkers(): List<SocialWorker>
    fun saveSocialWorker(socialWorker: SocialWorker)
    fun getSocialWorker(id: Int): Optional<SocialWorker>
    fun deleteSocialWorker(id: Int)
}