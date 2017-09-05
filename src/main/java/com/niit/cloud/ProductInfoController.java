package com.niit.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ProductInfoController {
	@Autowired
	private ProductInfoService productInfo;

	@RequestMapping(value = "/product/{id}")
	ProductInfo getProduct(@PathVariable("id") int id) {

		return productInfo.getProductInfo();

	}

}
