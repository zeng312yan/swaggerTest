package com.jt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

/**
 * swagger配置类
 * @author Jiasimin
 *
 */
@Configuration
public class SwaggerConfig extends WebMvcAutoConfigurationAdapter{

	private SpringSwaggerConfig springSwaggerConfig;

	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}

	/**
	 * 链式编程 来定制API样式 后续会加上分组信息
	 * 
	 * @return
	 */
	@Bean
	public SwaggerSpringMvcPlugin customImplementation(){
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
				.apiInfo(apiInfo())
				.includePatterns(".*?");
	}

	private ApiInfo apiInfo() {

		ApiInfo apiInfo = new ApiInfo("API接口测试平台",
				"提供后台所有Restful接口", "www.flyeast.top",
				"shexd1001@gmail.com", "β客栈", "www.flyeast.top");
		return apiInfo;
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}


}
