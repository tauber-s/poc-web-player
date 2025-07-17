package com.poc.webplayer.video;

import java.io.IOException;
import java.io.InputStream;

public class Range extends InputStream {
    private final InputStream in;
    private long remaining;

    public Range(InputStream in, long length) {
        this.in = in;
        this.remaining = length;
    }

    @Override
    public int read() throws IOException {
        if (remaining <= 0) return -1;
        int b = in.read();
        if (b != -1) remaining--;
        return b;
    }

    @Override
    public int read(byte [] b, int off, int len) throws IOException {
        if (remaining <= 0) return -1;
        int readLen = (int) Math.min(len, remaining);
        int result = in.read(b, off, readLen);
        if (result != -1) remaining -= result;
        return result;
    }
}
