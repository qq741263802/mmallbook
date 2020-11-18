package com.lc.mmallbook;

import com.lc.mmallbook.common.ServerResponse;
import com.lc.mmallbook.pojo.Product;
import com.lc.mmallbook.service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
 class MmallbookApplicationTests {

	@Autowired
	private IProductService iProductService;

	@Test
	void contextLoads() {
		for(int i=0;i<1;i++)
		{
			BigDecimal loanAmount = new BigDecimal("15000.48");
			double price=22;
			Product product=new Product();
			product.setCategoryId(100002);
			product.setName("活动商品");
			product.setSubtitle("活动商品副标题");
			product.setSubImages(UUID.randomUUID().toString());
			product.setPrice(loanAmount);
			product.setStock(22);
			ServerResponse ss= iProductService.saveOrUpdateProduct(product);
			System.out.println(ss.getData());

		}

	}

	public static void main(String[] args) throws IOException {

     //   String	productName=com.lc.mmallbook.util.LbReptileUtil.rsaEncrypt();

//
//		String	productName = new StringBuffer().append("%").append("123").append("%").toString();
//		System.out.println(productName);




	}

}
