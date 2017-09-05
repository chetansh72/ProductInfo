package com.niit.cloud;

import org.springframework.web.bind.annotation.RequestMapping;

public interface ItemReviewService {
	@RequestMapping(value = "/itemreview")
	 public String getItemReview();
	
}
