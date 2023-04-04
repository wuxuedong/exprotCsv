package com.woniu.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AuthorsService {
    private final SqlSessionTemplate sqlSessionTemplate;
    private final AuthorsMapper authorsMapper;

    public AuthorsService(SqlSessionTemplate sqlSessionTemplate, AuthorsMapper authorsMapper) {
        this.sqlSessionTemplate = sqlSessionTemplate;
        this.authorsMapper = authorsMapper;
    }

    /**
     * stream读数据写文件方式
     *
     * @param httpServletResponse
     * @throws IOException
     */
    public void streamDownload(HttpServletResponse httpServletResponse)
            throws IOException {
        DownloadProcessor downloadProcessor = new DownloadProcessor(httpServletResponse);
        authorsMapper.getAll(new ResultHandler<Authors>() {
            @Override
            public void handleResult(ResultContext<? extends Authors> resultContext) {
                Authors authors = resultContext.getResultObject();
                downloadProcessor.processData(authors);
            }
        });
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
    }

    /**
     * 传统下载方式
     *
     * @param httpServletResponse
     * @throws IOException
     */
    public void traditionDownload(HttpServletResponse httpServletResponse)
            throws IOException {
        List<Authors> authors = authorsMapper.selectByExample();
        DownloadProcessor downloadProcessor = new DownloadProcessor(httpServletResponse);
        authors.forEach(downloadProcessor::processData);
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
    }
}
