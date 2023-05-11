package com.spring.springboot.social_assistant_kotlin.entity

import jakarta.persistence.*

@Entity
@Table(name = "mentees")
data class Mentee(
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

    @ManyToOne(fetch = FetchType.EAGER)
    var socialWorker: SocialWorker?
)
