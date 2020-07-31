package com.nhn.simplewas;

import java.io.StringWriter;

public class HttpResponse {
    public StringWriter getWriter() {
        return new StringWriter();
    }
}
