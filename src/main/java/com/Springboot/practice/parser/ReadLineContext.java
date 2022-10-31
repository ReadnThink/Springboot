package com.Springboot.practice.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadLineContext<T> {
    /*
    1
    파싱중 문제가 생겨 이 라인은 넘어갑니다. 파일내용:"93012","?ǿ?","01_01
    파싱중 문제가 생겨 이 라인은 넘어갑니다. 파일내용:"93013","?ǿ?","01_01
    파싱중 문제가 생겨 이 라인은 넘어갑니다. 파일내용:"93014","?ǿ?","01_01
    파싱중 문제가 생겨 이 라인은 넘어갑니다. 파일내용:"93015","?ǿ?","01_01
    파싱중 문제가 생겨 이 라인은 넘어갑니다. 파일내용:"93016","?ǿ?","01_01
    파싱중 문제가 생겨 이 라인은 넘어갑니다. 파일내용:"93017","?ǿ?","01_01
    파싱중 문제가 생겨 이 라인은 넘어갑니다. 파일내용:"93018","?ǿ?","01_01
     */
    private Parser<T> parser;

    public ReadLineContext(Parser<T> parser) {
        this.parser = parser;
    }

    public List<T> readByLine(String filename) throws IOException {
        // 삽
        List<T> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new FileReader(filename)
        );
        String str;
        while ((str = reader.readLine()) != null) {
            try {
                result.add(parser.parse(str));
            } catch (Exception e) {
                System.out.printf("파싱중 문제가 생겨 이 라인은 넘어갑니다. 파일내용:%s\n",str.substring(0,20));
            }
        }
        reader.close();
        return result;
    }

}
