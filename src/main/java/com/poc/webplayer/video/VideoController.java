package com.poc.webplayer.video;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/videos")
public class VideoController {
    private final VideoService service;

    public VideoController(VideoService service) {
        this.service = service;
    }

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> stream(@PathVariable String filename, @RequestHeader(value = "Range", required = false) String rangeHeader) {
        try {
            return service.streamVideo(filename, rangeHeader);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
