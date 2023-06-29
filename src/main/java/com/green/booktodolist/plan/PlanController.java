package com.green.booktodolist.plan;


import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonObject;
import com.green.booktodolist.plan.model.PlanApiDto;
import com.green.booktodolist.plan.model.PlanBookInsDto;
import com.green.booktodolist.plan.model.PlanTodoDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.simple.parser.JSONParser;


import static java.sql.DriverManager.println;
@Slf4j
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/plan")
public class PlanController {

    private final PlanService service;
    private int tmep_cate;
    @Autowired
    public PlanController(PlanService service) {
        this.service = service;
    }

//    @Value("${isbn.API_KEY}") // api키값
//    String API_KEY;



    @GetMapping("/search")
    @Operation(summary = "검색기능-테스트용")
    public String callapihttp(@RequestParam String str) throws JSONException {

        log.info("책 검색 - api 호출 시작");

        String apiKey = "e7e239ae4128719a998e3a31ab3041b1a2cc0b014e95d5f4f2914e3187bbdc29"; //인증키
        String text = null;
        try {
            text = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }

        StringBuffer requestApi = new StringBuffer();

        try {
            String urlstr = "https://www.nl.go.kr/seoji/SearchApi.do?cert_key=" // 기본세팅 주소
            + apiKey // 인증키
            + "&result_style=json&page_no=1&page_size=30" // 페이지사이즈 30 고정
            + "&title=" + text; // 검색어

            URL url = new URL(urlstr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));

            String returnLine;
            while ((returnLine = br.readLine()) != null){
                requestApi.append(returnLine+"\n");
            }
            urlConnection.disconnect();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        log.info("책 검색 - api 호출 완료");

        log.info("파싱 시작");



        return requestApi+" ";

    }


    @PostMapping("/book")
    @Operation(summary = "책정보 입력")
    public int postBook(@RequestBody PlanBookInsDto dto){
        return service.insBook(dto);
    }

    @PostMapping("/Todolist")
    @Operation(summary = "투두리스트 작성")
    public int postTodolist(@RequestBody PlanTodoDto dto){
        return service.postTodolist(dto);
    }


}

