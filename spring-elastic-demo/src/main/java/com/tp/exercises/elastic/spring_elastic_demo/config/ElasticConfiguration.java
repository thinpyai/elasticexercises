package com.tp.exercises.elastic.spring_elastic_demo.config;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.tp.exercises.elastic.spring_elastic_demo.repository")
public class ElasticConfiguration {

	@Value("${elasticsearch.host}")
	private String EsHost;

	@Value("${elasticsearch.port}")
	private int EsPort;

	@Value("${elasticsearch.clustername}")
	private String EsClusterName;

	@Bean
	ElasticsearchOperations elasticsearchOperations() {
		File tempFile = null;
		try {
			tempFile = File.createTempFile("temp-elastic", Long.toString(System.nanoTime()));
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		}

		Settings.Builder elasticsearchSettings = Settings.builder().put("http.enabled", "true")
				.put("index.number_of_shards", "1").put("path.data", new File(tempFile, "data").getAbsolutePath())
				.put("path.logs", new File(tempFile, "data").getAbsolutePath())
				.put("path.work", new File(tempFile, "data").getAbsolutePath()).put("path.home", tempFile)
				.put("cluster.name", EsClusterName);

		TransportClient client = null;
		try {
			client = new PreBuiltTransportClient(Settings.EMPTY)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsHost), EsPort));

			client.settings().builder().put(elasticsearchSettings);
			return new ElasticsearchTemplate(client);
		} catch (UnknownHostException unknownHost) {
			unknownHost.printStackTrace();
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

}
