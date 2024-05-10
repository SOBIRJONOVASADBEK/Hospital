package com.demo.service;

import com.demo.domain.Doktor;
import com.demo.exception.InternalServerException;
import com.demo.exception.PhotoRetrievalExсeption;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.DoktorRepository;
import com.demo.response.DoktorResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoktorService {
    private final DoktorRepository doktorRepository;

    public DoktorService(DoktorRepository doktorRepository) {
        this.doktorRepository = doktorRepository;
    }
    public Doktor addNewDoktor(String FIO, MultipartFile file, String bio) throws IOException, SQLException {
        Doktor doktor=new Doktor();
        doktor.setFIO(FIO);
        doktor.setBio(bio);
        if (!file.isEmpty()){
            byte[] photoBytes=file.getBytes();
            Blob photoBlob=new SerialBlob(photoBytes);
            doktor.setPhoto(photoBlob);
        }
        return doktorRepository.save(doktor);
    }

   public List<Doktor> findAll(){
        return doktorRepository.findAll();
   }
    public byte[] getDoktorPhotoByDoktorId(Long id) throws SQLException, ResourceNotFoundException {
        Optional<Doktor> theDoktor=doktorRepository.findById(id);
        if (theDoktor.isEmpty()){
            throw new ResourceNotFoundException("Sorry,Not found Doktor");
        }
        Blob photoBlob=theDoktor.get().getPhoto();
        if (photoBlob !=null){
            return photoBlob.getBytes(1,(int)photoBlob.length());
        }
        return null;
    }

    public DoktorResponse getDoktorResponse(Doktor doktor) {
        List<Doktor> doktors=doktorRepository.findAll();
        List<DoktorResponse> doktorInfo=doktors.stream().map(
                doktor1 -> new DoktorResponse(doktor1.getId(),
                                        doktor1.getFIO(),
                                        doktor1.getBio(),
                                        doktor1.getContacts(),
                        doktor1.getProfessions(),
                        doktor1.getAddress(),
                        doktor1.getPrice())).collect(Collectors.toList());
        byte[] photoByte=null;
        Blob photoBlob=doktor.getPhoto();
        if (photoBlob !=null){
            try {
                photoByte=photoBlob.getBytes(1,(int) photoBlob.length());
            }catch ( SQLException e){
                throw new PhotoRetrievalExсeption("Error retrieving photo");
            }
        }
        return new DoktorResponse(doktor.getId(),doktor.getFIO(), doktor.getProfessions(), doktor.getBio(), photoByte,doktor.getAddress(),doktor.getContacts(),doktor.getPrice());
    }
    public Doktor getById(Long id){
        return doktorRepository.findById(id).get();
    }
    public void delete(Long doktorId) {
        Optional<Doktor> theDoktor=doktorRepository.findById(doktorId);
        if (theDoktor.isEmpty()){
            doktorRepository.deleteById(doktorId);
        }
    }

    public Doktor updateDoktor(Long doktorId, String FIO, String bio, byte[] photoBytes) {
        Doktor doktor=doktorRepository.findById(doktorId).orElseThrow(()->new ResourceNotFoundException("Doktor not found"));
        if (FIO!=null) doktor.setFIO(FIO);
        if (bio!=null) doktor.setBio(bio);
        if (photoBytes!=null && photoBytes.length>0){
            try {
                doktor.setPhoto(new SerialBlob(photoBytes));
            }catch (SQLException e){
                throw new InternalServerException("Error update doktor!");
            }
        }


        return doktorRepository.save(doktor);
    }




}
