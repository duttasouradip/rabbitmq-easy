package com.sd.rabbit.tut4;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sd.rabbit.util.Tut4Receiver;
import com.sd.rabbit.util.Tut4Sender;


@Configuration
@Profile({"tut4","routing"})
public class Tut4Config {
	
	@Bean
	public DirectExchange direct(){
		return new DirectExchange("tut.direct");
	}
	
	@Profile("receiver")
	private static class ReceiverConfig{
		
		@Bean
		public Queue autoDeleteQueue1(){
			return new Queue("sd1");
		}
		
		
		@Bean
		public Queue autoDeleteQueue2(){
			return new Queue("sd2");
		}
		
		@Bean
		public Binding binding1a(DirectExchange direct, Queue autoDeleteQueue1){
			return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("orange");
		}
		
		@Bean
		public Binding binding1b(DirectExchange direct, Queue autoDeleteQueue1){
			return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("black");
		}
		
		@Bean
		public Binding binding2a(DirectExchange direct, Queue autoDeleteQueue2){
			return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("green");
		}
		
		@Bean
		public Binding binding2b(DirectExchange direct, Queue autoDeleteQueue2){
			return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("black");
		}
		
		@Bean
		public Tut4Receiver receiver(){
			return new Tut4Receiver(); 
		}
		
		
	}
	
	@Profile("sender")
	@Bean
	public Tut4Sender sender(){
		return new Tut4Sender(); 
	}
	
	
}
