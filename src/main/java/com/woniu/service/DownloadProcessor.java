package com.woniu.service;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class DownloadProcessor {
    private final HttpServletResponse response;

    public DownloadProcessor(HttpServletResponse response) {
        this.response = response;
        String fileName = LocalDate.now() + ".csv";
        this.response.addHeader("Content-Type", "application/csv");
        this.response.addHeader("Content-Disposition", "attachment; filename="+fileName);
        this.response.setCharacterEncoding("UTF-8");
    }

    public <E> void processData(E record) {
        try {
            response.getWriter().write(record.toString()); //如果是要写入csv,需要重写toString,属性通过","分割
            response.getWriter().write("\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
