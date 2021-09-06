package com.jojo5716.budapp.restapi.controller

import com.jojo5716.budapp.restapi.domain.User
import com.jojo5716.budapp.restapi.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
class UserController(userService: UserService) : BasicController<User, Int>(userService)