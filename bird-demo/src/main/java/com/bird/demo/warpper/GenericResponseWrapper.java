package com.bird.demo.warpper;

import com.bird.demo.app.filter.FilterServletOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

/**
 * @author master
 */
public class GenericResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private int contentLength;
    private String contentType;

    public GenericResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public byte[] getData() {
        return this.output.toByteArray();
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return new FilterServletOutputStream(this.output);
    }

    @Override
    public PrintWriter getWriter() {
        return new PrintWriter(this.getOutputStream(), true);
    }

    @Override
    public void setContentLength(int length) {
        this.contentLength = length;
        super.setContentLength(length);
    }

    public int getContentLength() {
        return this.contentLength;
    }

    @Override
    public void setContentType(String type) {
        this.contentType = type;
        super.setContentType(type);
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }
}
