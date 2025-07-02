package com.poc.webplayer.video;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.core.io.InputStreamResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class VideoService {
    @Value("${video.storage.path}")
    private String storagePath;
    private final VideoRepository repository;

    public VideoService(VideoRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Resource> streamVideo(String filename, String rangeHeader) throws IOException {
        File file = repository.getVideoFile(filename);
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        long length = file.length();
        long start = 0;
        long end = length - 1;

        if (rangeHeader != null && rangeHeader.startsWith("bytes=")) {
            String[] ranges = rangeHeader.substring(6).split("-");
            try {
                start = Long.parseLong(ranges[0]);
                if (ranges.length > 1 && !ranges[1].isEmpty()) {
                    end = Long.parseLong(ranges[1]);
                }
            } catch (NumberFormatException ignored) {}
        }

        if (start > end || start < 0 || end >= length) {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).build();
        }

        InputStream inputStream = new FileInputStream(file);
        inputStream.skip(start);
        InputStreamResource resource = new InputStreamResource(new Range(inputStream, end - start + 1));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "video/mp4");
        headers.set("Accept-Ranges", "bytes");
        headers.set("Content-Range", String.format("bytes %d-%d/%d", start, end, length));
        headers.setContentLength(end - start + 1);

        return new ResponseEntity<>(resource, headers, HttpStatus.PARTIAL_CONTENT);
    }
}
