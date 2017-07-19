package com.sd.rabbit.tut2;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sd.rabbit.util.Tut2Receiver;
import com.sd.rabbit.util.Tut2Sender;


@Profile({"tut2","work-queues"})
@Configuration
public class Tut2Config {


	@Bean
	public Queue queue(){
		return new Queue("hello");
	}

	@Profile("receiver")
    private static class ReceiverConfig {

        @Bean
        public Tut2Receiver receiver1() {
            return new Tut2Receiver(1);
        }

        @Bean
        public Tut2Receiver receiver2() {
            return new Tut2Receiver(2);
        }
    }

	@Profile("sender")
	@Bean
	public Tut2Sender sender() {
		return new Tut2Sender();
	}

}
