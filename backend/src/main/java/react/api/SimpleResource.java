package react.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SimpleResource {

	@RequestMapping("/api/simple")
	public String resource() {
		List<Integer> numbers = new ArrayList<>();
		int randomNum = new Random().nextInt(10) + 2;
		for (int i = 1; i < randomNum; i++) {
			numbers.add(i);
		}
		return StringUtils.collectionToCommaDelimitedString(numbers);
	}
}