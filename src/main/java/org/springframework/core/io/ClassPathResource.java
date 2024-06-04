package org.springframework.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * classpath下的资源
 *
 * @author derekyi
 * @date 2020/11/25
 */
public class ClassPathResource implements Resource {

	private final String path;

	public ClassPathResource(String path) {
		this.path = path;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		//this.getClass().getClassLoader():classLoader是一个类装载器,正常用于读取硬盘中.class文件的字节码.存入内存让其成为对象
		//在这里通过getResourceAsStream 获得一个InputStream流,通过path读取对应classpath下的文件
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(this.path);
		if (is == null) {
			throw new FileNotFoundException(this.path + " cannot be opened because it does not exist");
		}
		return is;
	}
}
