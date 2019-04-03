package com.example.demo.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/helloworld")
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;
	
	@GetMapping()
	public String helloWorld() {
		return "Hello, World!";
	}
	
	@GetMapping("/i18n")
	public String helloWorldi18n(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}
	
	@GetMapping("/i18n/v2")
	public String helloWorldi18nV2() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
	
	
	@GetMapping("/")
	public HelloWorld helloWorldBean() {
		return new HelloWorld("Default");
	}
	
	@GetMapping("/{name}")
	public HelloWorld helloWorldPathVariable(@PathVariable(name = "name") String name) {
		return new HelloWorld(String.format("This is from the path variable %s", name));
	}
	
}
