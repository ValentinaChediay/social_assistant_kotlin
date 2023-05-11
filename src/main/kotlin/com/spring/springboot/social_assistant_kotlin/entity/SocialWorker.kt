package com.spring.springboot.social_assistant_kotlin.entity

import jakarta.persistence.*

@Entity
@Table(name = "social_workers")
data class SocialWorker(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Int,

    @Column(name = "surname")
    val surname: String,

    @Column(name = "name")
    val name: String,

    @Column(name = "patronymic")
    val patronymic: String,

    @Column(name = "login")
    val login: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "code")
    val code: String
)
