package com.demo.response;

import com.demo.domain.MakeAnAppointment;
import lombok.Data;

@Data
public class MakeAnAppointmentResponse {
    private Long id;
    private String name;
    private String phoneNumber;
    private String complaint;

    public static MakeAnAppointment mapToMakeAnAppointment(MakeAnAppointmentResponse makeAnAppointmentResponse){
        MakeAnAppointment makeAnAppointment=new MakeAnAppointment();
        makeAnAppointment.setName(makeAnAppointmentResponse.getName());
        makeAnAppointment.setPhoneNumber(makeAnAppointmentResponse.getPhoneNumber());
        makeAnAppointment.setComplaint(makeAnAppointmentResponse.getComplaint());
        return makeAnAppointment;
    }
    public static MakeAnAppointmentResponse mapToMakeAnAppointment(MakeAnAppointment makeAnAppointmentResponse){
        MakeAnAppointmentResponse makeAnAppointment=new MakeAnAppointmentResponse();
        makeAnAppointment.setName(makeAnAppointmentResponse.getName());
        makeAnAppointment.setPhoneNumber(makeAnAppointmentResponse.getPhoneNumber());
        makeAnAppointment.setComplaint(makeAnAppointmentResponse.getComplaint());
        return makeAnAppointment;
    }
}
