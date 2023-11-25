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
    private final List<User> users = new ArrayList<>();
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
        } // curl -X PUT localhost:8080/messages/0 -H "Content-Type: text/plain" -d "ABOBA"
        @GetMapping("messages/search/{text}")
        public ResponseEntity<Integer> getMessage(@PathVariable("text") String text) {
            for (int i=0; i<messages.size(); i++){
                if(messages.get(i).contains(text)){
                    return ResponseEntity.ok(i);
                }
            }
            return null;
        } //curl -X GET localhost:8080/messages/search/ABOBA -H "Content-Type: text/plain"
    @GetMapping("messages/search/count")
    public ResponseEntity<Integer> countMessages(){
                return ResponseEntity.ok(messages.size());
    } //curl -X GET localhost:8080/messages/search/count -H "Content-Type: text/plain"
    @PostMapping("messages/{index}/create")
    public ResponseEntity<Void> addMessage(@RequestBody String text, @PathVariable("index") Integer index) {
        messages.add(index, "NEW MESSAGE");
        return ResponseEntity.accepted().build();
    } // curl -X POST localhost:8080/messages/0/create -H "Content-Type: text/plain" -d "text"
    @DeleteMapping("messages/search/{text}")
    public ResponseEntity<Void> deleteText(@PathVariable("text") String text) {
        int i=0;
        while (i<messages.size()) {
            if (messages.get(i).contains(text)) {
                messages.remove(i);
            }
            else{
                i++;
            }
        }
        return ResponseEntity.noContent().build();
    } //curl -X DELETE localhost:8080/messages/search/ABOBA -H "Content-Type: text/plain"


    @PostMapping("users")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        users.add(user);
        return ResponseEntity.accepted().build();
    }// curl -X POST localhost:8080/users -H "Content-Type: application/json" -d "{\"name\":\"Bob\",\"age\":\"25\"}"

    @DeleteMapping("users/{index}")
    public ResponseEntity<Void> deleteUser(@PathVariable("index") Integer index) {
        users.remove((int) index);
        return ResponseEntity.noContent().build();
    }// curl -X DELETE localhost:8080/users/0 -H "Content-Type: application/json"
    @GetMapping("users/{index}")
    public ResponseEntity<User> getUser(@PathVariable("index") Integer index) {
        return ResponseEntity.ok(users.get(index));
    }// curl -X GET localhost:8080/users/0 -H "Content-Type: application/json"
    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers(){
            return ResponseEntity.ok(users);
    }// curl -X GET localhost:8080/users -H "Content-Type: application/json"
    @PutMapping("users/{index}")
    public ResponseEntity<Void> updateUserAge(@PathVariable("index") Integer i, @RequestBody Integer age) {
        User user = users.get(i);
        user.setAge(age);
        users.remove((int) i);
        users.add(i, user);
        return ResponseEntity.accepted().build();
    }// curl -X PUT localhost:8080/users/0 -H "Content-Type: application/json" -d "26"
}

