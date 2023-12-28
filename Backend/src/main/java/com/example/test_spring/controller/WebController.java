package com.example.test_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//    import ru.cloud.storage.backendjavacloudstorage.dto.request.UserRequest;
//    import ru.cloud.storage.backendjavacloudstorage.dto.response.UserResponse;
//    import ru.cloud.storage.backendjavacloudstorage.model.User;
//    import ru.cloud.storage.backendjavacloudstorage.service.UserService;


//@RestController
@Controller
public class WebController {
//    private SpringStandardDialect dialect = SpringStandardDialect("SpringStandard", "th", 1000);

//    @GetMapping("/hello_world")
//    public ResponseEntity<String> helloWorld()   {return new ResponseEntity<>("Hello, World", HttpStatus.OK); }


    // localhost:8080
    @GetMapping("/")
    public String index()   {
        return "index1";
    }

}



