package com.poc.webplayer.video;

public class VideoDTO {
    private final String filename;
    private final long size;

    public VideoDTO(String filename, long size) {
        this.filename = filename;
        this.size = size;
    }

    public String getFilename() {
        return filename;
    }

    public long getSize() {
        return size;
    }
}
