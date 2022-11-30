package com.spring.demo.controller.apps.rpsgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.demo.service.rpsgame.RpsGameServiceImpl;

@Controller
public class RPSGameController {
	
	List<String> computerChoices = Arrays.asList("rock","paper","scissor");
	int userScore = 0;
	int computerScore = 0;
	
	@Autowired
	private RpsGameServiceImpl rpsGameServiceImpl;
	
	@GetMapping("/apps/RPSGame")
	public ModelAndView getApps() {
		ModelAndView mv = new ModelAndView();
		HashMap<String, String> winnerMessage = new HashMap<>();
		winnerMessage.put("userMessage","");
		winnerMessage.put("computerMessage","");
		mv.addObject("userScore", userScore);
		mv.addObject("computerScore", computerScore);
		mv.addObject("winnerMessage", winnerMessage);
		mv.addObject("clicked", false);
		mv.setViewName("RPSGame.html");
		return mv;
	}
	
	@GetMapping("/apps/RPSGame/{choice}")
	public ModelAndView whoWon(@PathVariable("choice") String userChoice) {
		System.out.println("userChoice: "+userChoice);
		boolean clicked = false;
		ModelAndView mv = new ModelAndView();
		Random random = new Random();
		int randomNumber = random.nextInt(3);
		System.out.println("randomNumber: "+randomNumber);
		String computerChoice = computerChoices.get(randomNumber);
		System.out.println("computerChoice: "+computerChoice);
		HashMap<String, String> winnerMessage= rpsGameServiceImpl.whoWon(userChoice, computerChoice);
		System.out.println("winnerMessage: "+winnerMessage);
		if(winnerMessage!=null) {
			clicked = true;
			if(winnerMessage.get("userMessage").equals("You Won!")) {
				userScore++;
			}else {
				computerScore++;
			}
		}
		mv.addObject("clicked", clicked);
		mv.addObject("winnerMessage", winnerMessage);
		mv.addObject("userScore", userScore);
		mv.addObject("computerScore", computerScore);
		mv.addObject("userChoice", userChoice);
		mv.addObject("computerChoice", computerChoice);
		mv.setViewName("RPSGame.html");
		return mv;
	}
	
	@GetMapping("/app/RPSGame/playAgain")
	public String playAgain() {
		return "redirect:/apps/RPSGame";
	}
	
	@GetMapping("/app/RPSGame/startOver")
	public String startOver() {
		userScore = 0;
		computerScore = 0;
		return "redirect:/apps/RPSGame";
	}
}
