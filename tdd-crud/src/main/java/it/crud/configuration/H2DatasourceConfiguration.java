package it.crud.configuration;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@Profile("h2") 
public class H2DatasourceConfiguration {
	
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseaServer() throws SQLException {
		
		log.debug("Creazione h2 server ...");
		return Server.createTcpServer(
	      "-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}

}
