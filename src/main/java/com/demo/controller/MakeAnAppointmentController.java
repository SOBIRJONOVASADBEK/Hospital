package com.demo.controller;

import com.demo.domain.MakeAnAppointment;
import com.demo.response.MakeAnAppointmentResponse;
import com.demo.service.MakeAnAppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/makeAnAppointment")
public class MakeAnAppointmentController {
    private final MakeAnAppointmentService makeAnAppointmentService;

    public MakeAnAppointmentController(MakeAnAppointmentService makeAnAppointmentService) {
        this.makeAnAppointmentService = makeAnAppointmentService;
    }

    @PostMapping("/add")
    public ResponseEntity addMakeAnAppointmentService(MakeAnAppointmentResponse makeAnAppointmentResponse){
        return ResponseEntity.ok(makeAnAppointmentService.add(makeAnAppointmentResponse));
    }
    @GetMapping("/get/All")
    public ResponseEntity<List<MakeAnAppointmentResponse>> getAll(){
        return ResponseEntity.ok(makeAnAppointmentService.getAll());
    }
    @GetMapping("/get/by/{id}")
    public ResponseEntity getById(@RequestParam Long id){
        return ResponseEntity.ok(makeAnAppointmentService.getById(id));
    }
}
