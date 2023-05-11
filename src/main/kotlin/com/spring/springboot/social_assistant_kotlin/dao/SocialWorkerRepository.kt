package com.spring.springboot.social_assistant_kotlin.dao

import com.spring.springboot.social_assistant_kotlin.entity.SocialWorker
import org.springframework.data.jpa.repository.JpaRepository

interface SocialWorkerRepository : JpaRepository<SocialWorker, Int>