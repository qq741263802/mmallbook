package com.lc.mmallbook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lc.mmallbook.common.ServerResponse;
import com.lc.mmallbook.pojo.Product;
import com.lc.mmallbook.service.IProductService;
import com.lc.mmallbook.util.LbReptileUtil;
import com.lc.mmallbook.util.excel.ExcelUtil;
import com.lc.mmallbook.util.excel.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

//
//		String response=  LbReptileUtil.Getllcphone();
//		JSONObject jsonObject = JSONObject.parseObject(response);
//		JSONObject data = JSONObject.parseObject(jsonObject.getString("data"));
//		JSONArray spec_info = JSON.parseArray(data.getString("spec_info"));
//		JSONObject name= JSONObject.parseObject(spec_info.getString(0));
//		JSONArray list = JSON.parseArray(name.getString("list"));
//		JSONObject id= JSONObject.parseObject(list.getString(0));



	}


	@Test
	void setExcel() throws IOException {

      User user=new User();
      user.setAge(20);
      user.setName("liuxing");
      String filename= "D:/user1.xls";
      ExcelUtil.writeExcel(filename,user);




	}



}
