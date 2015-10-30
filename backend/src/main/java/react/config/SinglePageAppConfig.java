package react.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import groovy.transform.builder.Builder;

/**
 * Redirects every page to index.html Used to handle the router
 */
@Configuration
@Profile("!webpack")
public class SinglePageAppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/").resourceChain(false)
				.addResolver(new PushStateResourceResolver());
	}

	@Builder
	public static class PushStateResourceResolver implements ResourceResolver {
		private Resource index = new ClassPathResource("/static/index.html");
		private List<String> handledExtensions = Arrays.asList("html", "js", "json", "csv", "css", "png", "svg", "eot",
				"ttf", "woff", "appcache", "jpg", "jpeg", "gif", "ico");
		private List<String> ignoredPaths = Arrays.asList("api");

		@Override
		public Resource resolveResource(HttpServletRequest request, String requestPath,
				List<? extends Resource> locations, ResourceResolverChain chain) {
			return resolve(requestPath, locations);
		}

		@Override
		public String resolveUrlPath(String resourcePath, List<? extends Resource> locations,
				ResourceResolverChain chain) {
			Resource r = resolve(resourcePath, locations);
			try {
				return r != null ? r.getURL().toString() : null;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private Resource resolve(String requestPath, List<? extends Resource> locations) {
			if (isIgnored(requestPath)) {
				return null;
			}
			if (isHandled(requestPath)) {
				// TODO figure out how to use convert this groovy to stream API
				//// return locations.stream().collect {
				// it.createRelative(requestPath) }.find { it.exists() };
				for (Resource r : locations) {
					try {
						if (r.createRelative(requestPath).exists()) {
							return r;
						}
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}

			}
			return index;
		}

		public boolean isIgnored(String path) {
			return ignoredPaths.contains(path);
		}

		public boolean isHandled(String path) {
			String extension = StringUtils.getFilenameExtension(path);
			return handledExtensions.contains(extension);
		}
	}
}
