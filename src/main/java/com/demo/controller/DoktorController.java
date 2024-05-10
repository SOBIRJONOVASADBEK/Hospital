package com.demo.controller;

import com.demo.domain.Doktor;
import com.demo.exception.ResourceNotFoundException;
import com.demo.response.DoktorResponse;
import com.demo.service.DoktorService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doktor")
public class DoktorController {
    private final DoktorService doktorService;

    public DoktorController(DoktorService doktorService) {
        this.doktorService = doktorService;
    }

    @PostMapping("/add/new-doktor")
    public ResponseEntity<DoktorResponse> addNewDoktor(
            @RequestParam("FIO") String fio,
            @RequestParam("photo")MultipartFile photo,
            @RequestParam("bio") String bio,
            @RequestParam("address") String address,
            @RequestParam("price") Double price
            ) throws SQLException, IOException {
        Doktor doktor=doktorService.addNewDoktor(fio,photo,bio);
        DoktorResponse doktorResponse=new DoktorResponse(doktor.getId(),doktor.getFIO(),doktor.getBio(), doktor.getContacts(), doktor.getProfessions(),doktor.getAddress(),doktor.getPrice());        return ResponseEntity.ok(doktorResponse);
    }

    @GetMapping("/get/All/doktor")
    public ResponseEntity<List<DoktorResponse>> getAllRooms() throws SQLException, ResourceNotFoundException {
        List<Doktor> doktors=doktorService.findAll();
        List<DoktorResponse> doktorResponses=new ArrayList<>();
        for (Doktor doktor:doktors){
            byte[] photoBytes=doktorService.getDoktorPhotoByDoktorId(doktor.getId());
            if (photoBytes != null && photoBytes.length>0){
                String base64Photo= Base64.encodeBase64String(photoBytes);
                DoktorResponse doktorResponse=doktorService.getDoktorResponse(doktor);
                doktorResponse.setPhoto(base64Photo);
                doktorResponses.add(doktorResponse);
            }
        }
        return ResponseEntity.ok(doktorResponses);
    }

    @GetMapping("/get/By/{id}")
    public ResponseEntity getById(@RequestParam Long id){
        Doktor theDoktor=doktorService.getById(id);
        return ResponseEntity.ok(Optional.of(doktorService.getDoktorResponse(theDoktor)));
    }

    @DeleteMapping("/deleteBy/{id}")
    public ResponseEntity deleteById(@RequestParam Long id){
        doktorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/update/{doktorId}")
    public ResponseEntity<DoktorResponse> updateRoom(@PathVariable Long doktorId,
                                                   @RequestParam(required = false) String FIO,
                                                   @RequestParam(required = false) MultipartFile photo,
                                                     @RequestParam(required = false) String bio) throws IOException, SQLException {
        byte[] photoBytes=photo!=null&&!photo.isEmpty()?
                photo.getBytes():doktorService.getDoktorPhotoByDoktorId(doktorId);
        Blob photoBlob=photoBytes !=null  && photoBytes.length>0?
                new SerialBlob(photoBytes):null;
        Doktor theDoktor=doktorService.updateDoktor(doktorId,FIO,bio,photoBytes);
        theDoktor.setPhoto(photoBlob);
        DoktorResponse doktorResponse=doktorService.getDoktorResponse(theDoktor);
        return ResponseEntity.ok(doktorResponse);
    }







}
