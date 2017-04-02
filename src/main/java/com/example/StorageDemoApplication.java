package com.example;

import com.google.api.services.storage.Storage;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StorageDemoApplication {

    private Logger log = Logger.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(StorageDemoApplication.class, args);
    }

    @Bean
    CredentialManager credentialManager() {
        return new CredentialManager();
    }

    @Bean
    CommandLineRunner commandLineRunner(CredentialManager credentialManager) {
        return (args) -> {
            Storage.Buckets.Get get = credentialManager.getStorageClient().buckets()
                    .get(credentialManager.getBucketName());

            get.put("test", "test");

            log.info(get.get("test"));
        };
    }
}
