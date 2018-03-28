package com.lightbend.akka.sample;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.AbstractActor.Receive;

public class Main {
	public static void main(String[] args) {
		
	
	ActorSystem system=ActorSystem.create("testSystem");
	ActorRef first=system.actorOf(Props.create(StartStopActor1.class),"first");
	first.tell("stop", first);
}
}
class StartStopActor1 extends AbstractActor{
	 @Override
		public void preStart() throws Exception {
			System.out.println("First started");
			getContext().actorOf(Props.create(StartStopActor2.class),"second");
		}
	 @Override
		public void postStop() throws Exception {
			System.out.println("Fisrt stopped");
		}
		
		@Override
		public Receive createReceive() {
			// TODO Auto-generated method stub
			return receiveBuilder().matchEquals("stop",s->{
				getContext().stop(getSelf());
			}).build();
		}
		
	}
	class StartStopActor2 extends AbstractActor{
	@Override
	public void preStart() throws Exception {
		System.out.println("second started");
	}
	@Override
		public void postStop() throws Exception {
			System.out.println("second stopped");
		}
		@Override
		public Receive createReceive() {
			// TODO Auto-generated method stub
			return receiveBuilder().build();
		}
		
	}
