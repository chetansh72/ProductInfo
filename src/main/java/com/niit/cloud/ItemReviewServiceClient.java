package com.niit.cloud;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="http://ITEMREVIEW")
public interface ItemReviewServiceClient extends ItemReviewService {

}
