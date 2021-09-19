package com.jojo5716.budapp.restapi.user.controllers

import com.jojo5716.budapp.restapi.controllers.BasicController
import com.jojo5716.budapp.restapi.user.entities.User
import com.jojo5716.budapp.restapi.user.services.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
class UserController(userService: UserService) : BasicController<User, Int>(userService)