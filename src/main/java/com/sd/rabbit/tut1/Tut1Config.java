package com.sd.rabbit.tut1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sd.rabbit.util.Tut1Receiver;
import com.sd.rabbit.util.Tut1Sender;

@Profile({"tut1","hello-world"})
@Configuration
public class Tut1Config {
	@Bean
	public Queue hello() {
		return new Queue("hello");
	}

	@Profile("receiver")
	@Bean
	public Tut1Receiver receiver(){
		return new Tut1Receiver();
	}

	@Profile("sender")
	@Bean
	public Tut1Sender sender() {
		return new Tut1Sender();
	}
}
