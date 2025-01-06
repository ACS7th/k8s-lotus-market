package com.acs7th.lotus_market.service.gcp;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.WriteChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.util.UUID;

@Service
public class StorageService {

    @Value("${gcp.bucket.name:k8s-kimjc}")
    private String bucketName;

    public String uploadToCloudStorage(MultipartFile file) throws IOException {
        String imageUrl = null;

        if (file != null && !file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            String uniqueFilename = UUID.randomUUID() + "_" + originalFilename;

            Storage storage = StorageOptions.getDefaultInstance().getService();

            BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, uniqueFilename).build();

            try (WriteChannel writer = storage.writer(blobInfo);
                    var inputStream = file.getInputStream();
                    OutputStream outputStream = Channels.newOutputStream(writer)) {
                inputStream.transferTo(outputStream);
            }

            imageUrl = String.format("https://storage.googleapis.com/%s/%s", bucketName, uniqueFilename);
        }

        return imageUrl;
    }
}
