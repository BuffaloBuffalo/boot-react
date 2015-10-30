package react.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SimpleResource {
	private int count = 0;

	@RequestMapping("/api/simple")
	public String resource() {
		return "generated value " + count++;
	}
}