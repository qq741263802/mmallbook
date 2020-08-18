package com.lc.mmallbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lc.mmallbook.dao")
public class MmallbookApplication {


	public static void main(String[] args) {
		SpringApplication.run(MmallbookApplication.class, args);
	}

}
