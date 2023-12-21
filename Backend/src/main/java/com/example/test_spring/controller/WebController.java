package com.example.test_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//    import ru.cloud.storage.backendjavacloudstorage.dto.request.UserRequest;
//    import ru.cloud.storage.backendjavacloudstorage.dto.response.UserResponse;
//    import ru.cloud.storage.backendjavacloudstorage.model.User;
//    import ru.cloud.storage.backendjavacloudstorage.service.UserService;


//@RestController
@Controller
public class WebController {

//    @GetMapping("/hello_world")
//    public ResponseEntity<String> helloWorld()   {return new ResponseEntity<>("Hello, World", HttpStatus.OK); }


    // localhost:8080
    @GetMapping("/")
    public String index()   {return "index"; }

    // после входа в систему
//    @GetMapping("/chat")
//    public String chatView() {
//        return "chat";
//    }

}



