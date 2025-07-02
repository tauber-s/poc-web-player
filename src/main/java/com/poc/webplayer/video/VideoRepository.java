package com.poc.webplayer.video;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class VideoRepository {
    @Value("${video.storage.path}")
    private String storagePath;

    public File getVideoFile(String filename) {
        return new File(storagePath + File.separator + filename);
    }

    public List<File> listAllVideos() {
        File dir = new File(storagePath);
        if (!dir.exists()) return List.of();
        return Arrays.asList(Objects.requireNonNull(dir.listFiles((file) -> file.getName().endsWith(".mp4"))));
    }
}
