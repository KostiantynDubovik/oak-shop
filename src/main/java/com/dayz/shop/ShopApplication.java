package com.dayz.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ShopApplication {

	static ConfigurableApplicationContext appCtx;

	public static void main(String[] args) {
		appCtx = SpringApplication.run(ShopApplication.class, args);
	}

	static void shutdown() {
		if (appCtx.isActive()) {
			appCtx.close();
		}
	}

}

@Controller
class RouteController {
	@RequestMapping(value = {"/{path:[^.]*}", "/profile/{path:[^.]*}","/settings/{path:[^.]*}"})
	public String redirect() {
		return "forward:/";
	}
}
