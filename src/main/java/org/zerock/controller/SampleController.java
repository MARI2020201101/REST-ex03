package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	@GetMapping(value = "/getText", produces = "text/plain;charset=utf-8")
	public String getText() {
		log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		return "안녕하세요";
	}

	@GetMapping(value = "getSample", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
												MediaType.APPLICATION_XML_VALUE})
	public SampleVO getSample() {
		return new SampleVO(100, "마리","쿠가");
	}
	@GetMapping("getSample2")
	public SampleVO getSample2() {
		return new SampleVO(111,"미나","로");
	}
	
	@GetMapping("getList")
	public List<SampleVO> getList(){
		return IntStream.range(1,10).mapToObj(i-> new SampleVO(i, i +"Firstname", i+"LastName")).collect(Collectors.toList());
	}
	
	@GetMapping("getMap")
	public Map<String, SampleVO> getMap(){
		Map<String, SampleVO> map = new HashMap();
		map.put("First", new SampleVO(101,"모리","모리나가"));
		return map;
	}
	@GetMapping(value="check", params = {"height", "weight"}) 
	public ResponseEntity<SampleVO> check(int height , int weight){
		SampleVO vo = new SampleVO(1, height + "", weight + "");
		if(height < 180) {
			return ResponseEntity.status(HttpStatus.OK).body(vo);
		}else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vo);
	}
}
