package com.niit.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductInfoServiceImpl implements ProductInfoService{
	
	@Autowired
	ItemReviewServiceClient itemReviewClient;
	
	  public ProductInfo getProductInfo(){

		  String itemReviewStr = itemReviewClient.getItemReview();
		  ProductInfo p = new ProductInfo();
		  ItemInfo itemInfo = new ItemInfo();
		  itemInfo.setDesc("Item Info..");
		  ItemReview itemReview = new ItemReview();
		  itemReview.setDesc(itemReviewStr);
		  p.setItemInfo(itemInfo);
		  p.setItemReview(itemReview);
		  return p;
	  }

}
