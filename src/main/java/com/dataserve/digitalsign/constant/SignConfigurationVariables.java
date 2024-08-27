package com.dataserve.digitalsign.constant;

import com.dataserve.digitalsign.dto.SignResponse;
import com.stcs.HashMechanism.HashResponse;
import com.stcs.HashMechanism.HashingMechanism;
import com.stcs.HashMechanism.MutualAuthentication;
import com.stcs.HashMechanism.containers.Signature;

import java.io.File;

public class SignConfigurationVariables {
    //~~~~~~~~~~~~~~~~~~~~~ Initializing Variables ~~~~~~~~~~~~~~~~~~~~~~~
    public static byte[] originalBytes = null;
    public static HashResponse signatureHash = null;
    public static HashingMechanism hashEx = null;
    public static HashResponse hashResp = null;
    public static Signature sig = null;
    //User information
    public static String firstName = "Abdelrhman";
    public static String lastName = "Salah";

    public static String reason = "reason_abdelrhman";
    public static String location = "Egypt_abdelrhman";
    public static String contact = "ropel81929@polatrix.com";
    //Profile information
    public static String apiUrl = "https://stage-authservice.sirar.com.sa/api/sayen/v1/sign/hashSigning";
    public static String cid = "88f7c761-6599-475d-9198-0bd1ec12bf5e";
    public static String privateKeyPassword = "Test@321";
    public static String email = "ropel81929@polatrix.com";

    //Signature information
    public static String signatureName = "Boda";
    //coordinate of the signature rectangle
    public static float lower_left_x = 120;
    public static float lower_left_y = 320;
    public static float upper_right_x = 529;
    public static float upper_right_y = 463;
    //     Input
    public static File imageFile = new File("D://Logo.png");
    //Output
    public static SignResponse signResponse = null;
    public static String outputFile = "signedOutput.pdf";
    public static String outputFileLTV = "D://signedOutputWithLTV.pdf";
}
