package ru.sharovse.telegrambot;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.DefaultBotOptions.ProxyType;
import org.telegram.telegrambots.meta.ApiContext;

@Configuration
public class Config {
	@Value("${bot.url}") 
	String url;

	@Value("${bot.proxy.host}") 
	String proxyHost;

	@Value("${bot.proxy.port}") 
	Integer proxyPort;
	
	@Value("${bot.proxy}") 
	boolean proxy;
	
	@Bean
	DefaultBotOptions createBotOptions() {
		ApiContextInitializer.init();
		DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
		if(proxy) {
			botOptions.setProxyHost(proxyHost);
			botOptions.setProxyPort(proxyPort);
			
			HttpHost httpHost = new HttpHost(proxyHost, proxyPort);
			RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).setAuthenticationEnabled(false).build();
			botOptions.setRequestConfig(requestConfig);
			botOptions.setProxyType(ProxyType.HTTP);
		}
		return botOptions;
	}

}
