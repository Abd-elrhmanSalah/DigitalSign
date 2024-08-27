package com.dataserve.digitalsign.service;


import com.dataserve.digitalsign.dto.RequestToSign;
import com.dataserve.digitalsign.dto.SignResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.stcs.HashMechanism.HashResponse;
import com.stcs.HashMechanism.MutualAuthentication;
import com.stcs.HashMechanism.containers.AdssConfig;
import com.stcs.HashMechanism.containers.AgentConfig;
import com.stcs.HashMechanism.containers.Signature;
import com.stcs.HashMechanism.containers.SignatureAppearance;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.stcs.HashMechanism.HashingMechanism;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


import static com.dataserve.digitalsign.constant.SignConfigurationVariables.*;

@Service
@AllArgsConstructor
public class DigitalSignService {


    private RestTemplate restTemplate;

    private ObjectMapper objectMapper;

    private SignResponse callApiService(RequestToSign request) throws IOException {

        HttpEntity<Object> requestBody = new HttpEntity<>(request);

        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                requestBody,
                String.class
        );

        signResponse = objectMapper.readValue(response.getBody(), new TypeReference<>() {
        });

        return signResponse;
    }

    public String signatureService(MultipartFile attachment) {
        System.out.println("Build HashRequest...Start");
        try {

            originalBytes = attachment.getBytes();
            hashEx = new HashingMechanism();

            /** This part is responsible for signature
             1- logo and data with the direction of content .
             2- position of the signature on the document
             */

            Image backImage = Image.getInstance(imageFile.toURI().toURL());

            Rectangle rectangle = new Rectangle(lower_left_x, lower_left_y,
                    upper_right_x, upper_right_y);
            SignatureAppearance appearance = new SignatureAppearance(true, rectangle
                    , 1, convertImageToByteArray(backImage));
            sig = new Signature(signatureName, false, appearance);
            sig.setRTL(true);
            sig.setContact(contact);
            sig.setDateVisible(false);
            sig.setLocation(location);
//            sig.setReason(reason);
            sig.setSignedBy(firstName, lastName);

            signatureHash = hashEx.AddEmptySigAndCalculateHash(originalBytes, sig,
                    80000, true);

            /** This part is responsible for
             prepare the request with the configuration that will be sent
             */

            RequestToSign request = new RequestToSign();
            String requestID = "26594878-1001-0000-0000-" + System.currentTimeMillis();

            request.setRid(requestID);
            request.setCid(cid);
            request.setEmail(email);
            request.setPrivateKeyPassword(privateKeyPassword);
            request.setFileBytes(signatureHash.getEncodedHash());

            /** This part is responsible for
             calling the function that will call the api service of SAYAN
             */
            callApiService(request);

        } catch (IOException ex) {
            System.err.println("Hashing pdfFile...Failed: " + ex.getMessage());
        } catch (BadElementException e) {
            throw new RuntimeException(e);
        }

        try {
            File signedFile = new File(outputFile);

            hashResp = hashEx.EmbedSignedHash(signatureHash.getOriginalDocBytes(),
                    signResponse.getSignedFileBytes().getBytes(), sig);

            try (FileOutputStream streamSigned = new FileOutputStream(signedFile)) {
                streamSigned.write(hashResp.getSignedBytes());
            }

            return "done";
        } catch (IOException ex) {
            System.err.println("Process Response...Failed: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    private byte[] convertImageToByteArray(Image image) {
        byte[] imageBytes = null;
        try (InputStream inputStream = image.getUrl().openStream();
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            imageBytes = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageBytes;
    }
}
