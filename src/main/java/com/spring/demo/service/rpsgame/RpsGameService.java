package com.spring.demo.service.rpsgame;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class RpsGameService implements RpsGameServiceImpl{

	@Override
	public HashMap<String, String> whoWon(String userChoice, String computerChoice) {
		HashMap<String, String> winnerMessage = new HashMap<>();
		String userMessage = "";
		String computerMessage = "";
		if(userChoice.equals("rock") && computerChoice.equals("scissor")) {
			userMessage = "You Won!";
			computerMessage = "Computer Lost!";
		}
		else if(userChoice.equals("paper") && computerChoice.equals("rock")) {
			userMessage = "You Won!";
			computerMessage = "Computer Lost!";
		}
		else if(userChoice.equals("scissor") && computerChoice.equals("paper")) {
			userMessage = "You Won!";
			computerMessage = "Computer Lost!";
		}
		else if(userChoice.equals(computerChoice)) {
			userMessage = "You Tied!";
			computerMessage = "Computer Tied!";
		}
		else {
			userMessage = "You Lost!";
			computerMessage = "Computer Won!";
		}
		winnerMessage.put("userMessage", userMessage);
		winnerMessage.put("computerMessage", computerMessage);
		System.out.println("winnerMessage: "+winnerMessage);
		return winnerMessage;
	}

}
