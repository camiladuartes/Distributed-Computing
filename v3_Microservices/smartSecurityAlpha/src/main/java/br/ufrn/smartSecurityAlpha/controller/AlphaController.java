package br.ufrn.smartSecurityAlpha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.smartSecurityAlpha.service.AlphaService;

@RestController
@RequestMapping("/alpha")
public class AlphaController {
	
	private final AlphaService alphaService;
	
	@Autowired
	public AlphaController(AlphaService alphaService) {
		// Use dependency injection, and avoid: this.alphaService = new AlphaService();
		this.alphaService = alphaService;
	}
	
	@GetMapping
	@RequestMapping("/data")
	public ResponseEntity getData() {
		return alphaService.getData();
	}
	
	@GetMapping
	@RequestMapping("/data2")
	public ResponseEntity getData2() {
		return alphaService.getData2();
	}
}
