package com.demo.service;

import com.demo.domain.MakeAnAppointment;
import com.demo.repository.MakeAnAppointmentRepository;
import com.demo.response.MakeAnAppointmentResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.demo.response.MakeAnAppointmentResponse.mapToMakeAnAppointment;

@Service
public class MakeAnAppointmentService {
    private final MakeAnAppointmentRepository makeAnAppointmentRepository;

    public MakeAnAppointmentService(MakeAnAppointmentRepository makeAnAppointmentRepository) {
        this.makeAnAppointmentRepository = makeAnAppointmentRepository;
    }

    public MakeAnAppointment add(MakeAnAppointmentResponse makeAnAppointmentResponse){
        MakeAnAppointment makeAnAppointment=mapToMakeAnAppointment(makeAnAppointmentResponse);
        return makeAnAppointmentRepository.save(makeAnAppointment);
    }
    public List<MakeAnAppointmentResponse> getAll(){
        List<MakeAnAppointment> makeAnAppointments=makeAnAppointmentRepository.findAll();
        List<MakeAnAppointmentResponse> makeAnAppointmentResponses=new ArrayList<>();
        for (MakeAnAppointment makeAnAppointment:makeAnAppointments){
            MakeAnAppointmentResponse makeAnAppointmentResponse=mapToMakeAnAppointment(makeAnAppointment);
            makeAnAppointmentResponses.add(makeAnAppointmentResponse);
        }
        return makeAnAppointmentResponses;
    }

    public MakeAnAppointmentResponse getById(Long id){
        MakeAnAppointment makeAnAppointment=makeAnAppointmentRepository.findById(id).get();
        return mapToMakeAnAppointment(makeAnAppointment);
    }
}
