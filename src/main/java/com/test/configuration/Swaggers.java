package com.test.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swaggers 配置
 */
@Configuration
@EnableSwagger2
public class Swaggers {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.enableUrlTemplating(true)
				.select()
				// 扫描所有有注解的api，用这种方式更灵活
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.paths(PathSelectors.any())
				.build();
	}

	/**
	 * 首页描述
	 * @return
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Spring Boot中使用Swagger2构建RESTful APIs")
				.description("更多Spring Boot相关文章请百度：http://www.baidu.com")
				.termsOfServiceUrl("http://www.baidu.com/")
				.build();
	}

	/**
	 * 重写BASE URL
	 */
	class BasePathAwareRelativePathProvider extends AbstractPathProvider {
		private String basePath;

		public BasePathAwareRelativePathProvider(String basePath) {
			this.basePath = basePath;
		}

		@Override
		protected String applicationPath() {
			return basePath;
		}

		@Override
		protected String getDocumentationPath() {
			return "/";
		}

		@Override
		public String getOperationPath(String operationPath) {
			UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/");
			return Paths.removeAdjacentForwardSlashes(
					uriComponentsBuilder.path("").build().toString());
		}
	}
}
