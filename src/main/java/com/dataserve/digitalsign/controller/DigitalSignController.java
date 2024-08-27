package com.dataserve.digitalsign.controller;

import com.dataserve.digitalsign.exception.ApplicationException;
import com.dataserve.digitalsign.service.DigitalSignService;
import com.dataserve.digitalsign.utils.Utils;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class DigitalSignController {


    private final DigitalSignService digitalSignService;

    private static final Logger logger = LoggerFactory.getLogger(DigitalSignController.class);

    @PostMapping(path = "/applyDigitalSign")
    public ResponseEntity<?> applyDigitalSign(@RequestPart(value = "attachment", required = false) MultipartFile attachment) {
        try {

            return new ResponseEntity<>(digitalSignService.signatureService(attachment), HttpStatus.OK);

        } catch (ApplicationException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return new ResponseEntity<>(e.getStatus(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ex.printStackTrace();
            return new ResponseEntity<>(Utils.internalServerError(ex.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
