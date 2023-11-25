package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
    @RestController
    public class ApiController {
        private final List<String> messages = new ArrayList<>();
        @GetMapping("messages")
        public ResponseEntity<List<String>> getMessages() {
            return ResponseEntity.ok(messages);
        }  //curl -X GET localhost:8080/messages -H "Content-Type: text/plain"
        @PostMapping("messages")
        public ResponseEntity<Void> addMessage(@RequestBody String text) {
            messages.add(text);
            return ResponseEntity.accepted().build();
        } // curl -X POST localhost:8080/messages -H "Content-Type: text/plain" -d "text"
        @GetMapping("messages/{index}")
        public ResponseEntity<String> getMessage(@PathVariable("index") Integer index) {
            return ResponseEntity.ok(messages.get(index));
        } // curl -X GET localhost:8080/messages/0 -H "Content-Type: text/plain"
        @DeleteMapping("messages/{index}")
        public ResponseEntity<Void> deleteText(@PathVariable("index") Integer index) {
            messages.remove((int) index);
            return ResponseEntity.noContent().build();
        } // curl -X DELETE localhost:8080/messages/0 -H "Content-Type: text/plain"
        @PutMapping("messages/{index}")
        public ResponseEntity<Void> updateMessage(@PathVariable("index") Integer i, @RequestBody String message) {
            messages.remove((int) i);
            messages.add(i, message);
            return ResponseEntity.accepted().build();
        } // curl -X PUT localhost:8080/messages/0 -H "Content-Type: text/plain"
    }

