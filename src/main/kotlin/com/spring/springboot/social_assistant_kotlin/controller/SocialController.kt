package com.spring.springboot.social_assistant_kotlin.controller

import com.spring.springboot.social_assistant_kotlin.entity.SocialWorker
import com.spring.springboot.social_assistant_kotlin.exeption.NoSuchSocialWorkerException
import com.spring.springboot.social_assistant_kotlin.exeption.SocialWorkerIncorrectData
import com.spring.springboot.social_assistant_kotlin.service.MenteeService
import com.spring.springboot.social_assistant_kotlin.service.SocialWorkerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class SocialController(val socialWorkerService: SocialWorkerService, val menteeService: MenteeService) {

  // Получение всех соц. работников
  @GetMapping("/socialWorkers")
  fun showAllSocialWorkers(): List<SocialWorker> = socialWorkerService.getAllSocialWorkers()

  // Получение одного соц. работника
  @GetMapping("/socialWorkers/{id}")
  fun getSocialWorker(@PathVariable id: Int): SocialWorker = getSocialWorkerFromOptional(id)

  @ExceptionHandler
  fun handleException(exception: NoSuchSocialWorkerException): ResponseEntity<SocialWorkerIncorrectData> {
    val data = SocialWorkerIncorrectData(exception.message)
    return ResponseEntity<SocialWorkerIncorrectData>(data, HttpStatus.NOT_FOUND)
  }

  @ExceptionHandler
  fun handleException(exception: Exception): ResponseEntity<SocialWorkerIncorrectData> {
    val data = SocialWorkerIncorrectData(exception.message)
    return ResponseEntity<SocialWorkerIncorrectData>(data, HttpStatus.BAD_REQUEST)
  }

  // Добавление соц. работника
  @PostMapping("/socialWorkers")
  fun addNewSocialWorker(@RequestBody socialWorker: SocialWorker): SocialWorker {
    socialWorkerService.saveSocialWorker(socialWorker)
    return socialWorker
  }

  // Изменение работника
  @PutMapping("/socialWorkers")
  fun updateSocialWorker(@RequestBody socialWorker: SocialWorker): SocialWorker {
    socialWorkerService.saveSocialWorker(socialWorker)
    return socialWorker
  }

  // Удаление работника
  @DeleteMapping("/socialWorkers/{id}")
  fun deleteSocialWorker(@PathVariable id: Int): String {
    val socialWorker = getSocialWorkerFromOptional(id)
    val mentees = menteeService.findAllBySocialWorker(socialWorker)

    for (mentee in mentees) {
      mentee.socialWorker = null
      menteeService.saveMentee(mentee)
    }
    socialWorkerService.deleteSocialWorker(id)
    return "SocialWorker with ID = $id was deleted"
  }

  fun getSocialWorkerFromOptional(id: Int): SocialWorker {
    val optional = socialWorkerService.getSocialWorker(id)
    if (optional.isPresent) {
      return optional.get()
    }
    throw NoSuchSocialWorkerException("There is no social worker with ID = $id.")
  }
}