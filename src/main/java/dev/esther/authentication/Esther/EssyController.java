package dev.esther.authentication.Esther;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/essyyyy")
public class EssyController {
    @GetMapping
    public ResponseEntity<String> hi(){
       return ResponseEntity.ok("hey there");
    }

}
