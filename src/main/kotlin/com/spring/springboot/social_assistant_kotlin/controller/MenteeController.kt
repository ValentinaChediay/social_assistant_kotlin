package com.spring.springboot.social_assistant_kotlin.controller

import com.spring.springboot.social_assistant_kotlin.entity.Mentee
import com.spring.springboot.social_assistant_kotlin.entity.SocialWorker
import com.spring.springboot.social_assistant_kotlin.exeption.MenteeIncorrectData
import com.spring.springboot.social_assistant_kotlin.exeption.NoSuchMenteeException
import com.spring.springboot.social_assistant_kotlin.exeption.NoSuchSocialWorkerException
import com.spring.springboot.social_assistant_kotlin.service.MenteeService
import com.spring.springboot.social_assistant_kotlin.service.SocialWorkerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
class MenteeController(val menteeService: MenteeService, val socialWorkerService: SocialWorkerService) {

    // получение всех подопечных
    @GetMapping("/mentees")
    fun showAllMentees(): List<Mentee> = menteeService.getAllMentees()

    // получение одного подопечного
    @GetMapping("/mentees/{id}")
    fun getMentee(@PathVariable id: Int): Mentee {
        val optional = menteeService.getMentee(id)
        if (optional.isPresent) {
            return optional.get()
        }
        throw NoSuchMenteeException("There is no mentee with ID = $id.")
    }

    @ExceptionHandler
    fun handleException(exception: NoSuchMenteeException): ResponseEntity<MenteeIncorrectData> {
        val data = MenteeIncorrectData(exception.message)
        return ResponseEntity<MenteeIncorrectData>(data, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleException(exception: Exception): ResponseEntity<MenteeIncorrectData> {
        val data = MenteeIncorrectData(exception.message)
        return ResponseEntity<MenteeIncorrectData>(data, HttpStatus.BAD_REQUEST)
    }

    // добавление подопечного
    @PostMapping("/mentees")
    fun addNewMentee(@RequestBody mentee: Mentee): Mentee {
        menteeService.saveMentee(mentee)
        return mentee
    }

    // доп метод: добавление подопечного и проставление связи с соц. работником, айди которого передается в адресе
    @PostMapping("/mentees/{socialWorkerId}")
    fun addNewMenteeAndLinkHisToSocialWorker(@RequestBody mentee: Mentee, @PathVariable socialWorkerId: Int): Mentee {
        val socialWorker = getSocialWorkerFromOptional(socialWorkerId)
        mentee.socialWorker = socialWorker
        menteeService.saveMentee(mentee)
        return mentee
    }

    // изменение подопечного
    @PutMapping("/mentees")
    fun updateMentee(@RequestBody mentee: Mentee): Mentee {
        menteeService.saveMentee(mentee)
        return mentee
    }

    // доп метод: изменение подопечного и проставление связи с соц. работником, айди которого передается в адресе
    @PutMapping("/mentees/{socialWorkerId}")
    fun updateMenteeAndLinkHisToSocialWorker(@RequestBody mentee: Mentee, @PathVariable socialWorkerId: Int): Mentee {
        val socialWorker = getSocialWorkerFromOptional(socialWorkerId)
        mentee.socialWorker = socialWorker
        menteeService.saveMentee(mentee)
        return mentee
    }

    // удаление подопечного
    @DeleteMapping("/mentees/{id}")
    fun deleteMentee(@PathVariable id: Int): String {
        menteeService.deleteMentee(id)
        return "Mentee with ID = $id was deleted"
    }

    fun getSocialWorkerFromOptional(id: Int): SocialWorker {
        val optional = socialWorkerService.getSocialWorker(id)
        if (optional.isPresent) {
            return optional.get()
        }
        throw NoSuchSocialWorkerException("There is no social worker with ID = $id.")
    }

}