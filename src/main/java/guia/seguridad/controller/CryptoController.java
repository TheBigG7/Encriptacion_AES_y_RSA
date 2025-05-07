package guia.seguridad.controller;

import guia.seguridad.CipherRequest;
import guia.seguridad.service.AesEncryptionService;
import guia.seguridad.service.RsaEncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // Controlador REST
@RequestMapping("/api/crypto")
public class CryptoController {

    private final AesEncryptionService aesService;
    private final RsaEncryptionService rsaService;

    @Autowired
    public CryptoController(AesEncryptionService aesService, RsaEncryptionService rsaService) {
        this.aesService = aesService;
        this.rsaService = rsaService;
    }

    @PostMapping("/aes/encrypt")
    public String encryptAes(@RequestBody CipherRequest plainText) throws Exception {
        return aesService.encrypt(plainText.getData());
    }

    @PostMapping("/aes/decrypt")
    public String decryptAes(@RequestBody CipherRequest  encryptedText) throws Exception {
        return aesService.decrypt(encryptedText.getData());
    }

    @PostMapping("/rsa/encrypt")
    public String encryptRsa(@RequestBody CipherRequest plainText) throws Exception {
        return rsaService.encrypt(plainText.getData());
    }

    @PostMapping("/rsa/decrypt")
    public String decryptRsa(@RequestBody CipherRequest encryptedText) throws Exception {
        return rsaService.decrypt(encryptedText.getData());
    }
}
