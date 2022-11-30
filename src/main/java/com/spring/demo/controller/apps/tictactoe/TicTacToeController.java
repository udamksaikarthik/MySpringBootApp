package com.spring.demo.controller.apps.tictactoe;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TicTacToeController {
	
	boolean flag = true;
	String block11 = "";
	String block12 = "";
	String block13 = "";
	String block21 = "";
	String block22 = "";
	String block23 = "";
	String block31 = "";
	String block32 = "";
	String block33 = "";
	String winnerMessage = "";
	
	boolean flagX11 = false;
	boolean flagX12 = false;
	boolean flagX13 = false;
	boolean flagX21 = false;
	boolean flagX22 = false;
	boolean flagX23 = false;
	boolean flagX31 = false;
	boolean flagX32 = false;
	boolean flagX33 = false;
	
	boolean classBlock11 = false;
	boolean classBlock12 = false;
	boolean classBlock13 = false;
	boolean classBlock21 = false;
	boolean classBlock22 = false;
	boolean classBlock23 = false;
	boolean classBlock31 = false;
	boolean classBlock32 = false;
	boolean classBlock33 = false;
	boolean gameStopped = false;
	ModelAndView mv = new ModelAndView();
	
	@GetMapping("/apps/TicTacToeGame")
	public ModelAndView getTicTacToegame() {
		ModelAndView mv1 = new ModelAndView();
		mv.clear();
		mv1.setViewName("TicTacToe.html");
		return mv1;
	}
	
	@GetMapping("/apps/TicTacToeGame/{blockNumber}")
	public ModelAndView userSelectedBlock(@PathVariable("blockNumber") Integer blockNumber) {
		System.out.println("Block Number:"+blockNumber);
		String value = "X";
		if(flag) {
			value = "X";
			flag = false;
		}else {
			value = "O";
			flag = true;
		}
		if(!gameStopped) {
		
		if(blockNumber==11) {
			if(block11.isEmpty()) {
				block11 = value;
				if(value.equals("X")) {
					flagX11 = true;
				}else {
					flagX11 = false;
				}
				mv.addObject("block11", value);
				mv.addObject("flagX11", flagX11);
			}else {
				if(block11.equals("X")) {
					flagX11 = true;
					flag= false;
				}else {
					flagX11 = false;
					flag = true;
				}
				mv.addObject("block11", block11);
				mv.addObject("flagX11", flagX11);
			}
		}
		if(blockNumber==12) {
			if(block12.isEmpty()) {
				block12 = value;
				if(value.equals("X")) {
					flagX12 = true;
				}else {
					flagX12 = false;
				}
				mv.addObject("block12", value);
				mv.addObject("flagX12", flagX12);
			}else {
				if(block12.equals("X")) {
					flagX12 = true;
					flag = false;
				}else {
					flagX12 = false;
					flag = true;
				}
				mv.addObject("block12", block12);
				mv.addObject("flagX12", flagX12);
			}
		}
		if(blockNumber==13) {
			if(block13.isEmpty()) {
				block13 = value;
				if(value.equals("X")) {
					flagX13 = true;
				}else {
					flagX13 = false;
				}
				mv.addObject("block13", value);
				mv.addObject("flagX13", flagX13);
			}else {
				if(block13.equals("X")) {
					flagX13 = true;
					flag = false;
				}else {
					flagX13 = false;
					flag = true;
				}
				mv.addObject("block13", block13);
				mv.addObject("flagX13", flagX13);
			}
		}
		if(blockNumber==21) {
			if(block21.isEmpty()) {
				block21 = value;
				if(value.equals("X")) {
					flagX21 = true;
				}else {
					flagX21 = false;
				}
				mv.addObject("block21", value);
				mv.addObject("flagX21", flagX21);
			}else {
				if(block21.equals("X")) {
					flagX21 = true;
					flag = false;
				}else {
					flagX21 = false;
					flag = true;
				}
				mv.addObject("block21", block21);
				mv.addObject("flagX21", flagX21);
			}
		}
		if(blockNumber==22) {
			if(block22.isEmpty()) {
				block22 = value;
				if(value.equals("X")) {
					flagX22 = true;
				}else {
					flagX22 = false;
				}
				mv.addObject("block22", value);
				mv.addObject("flagX22", flagX22);
			}else {
				if(block22.equals("X")) {
					flagX22 = true;
					flag = false;
				}else {
					flagX22 = false;
					flag = true;
				}
				mv.addObject("block22", block22);
				mv.addObject("flagX22", flagX22);
			}
		}
		if(blockNumber==23) {
			if(block23.isEmpty()) {
				block23 = value;
				if(value.equals("X")) {
					flagX23 = true;
				}else {
					flagX23 = false;
				}
				mv.addObject("block23", value);
				mv.addObject("flagX23", flagX23);
			}else {
				if(block23.equals("X")) {
					flagX23 = true;
					flag = false;
				}else {
					flagX23 = false;
					flag = true;
				}
				mv.addObject("block23", block23);
				mv.addObject("flagX23", flagX23);
			}
		}
		if(blockNumber==31) {
			if(block31.isEmpty()) {
				block31 = value;
				if(value.equals("X")) {
					flagX31 = true;
				}else {
					flagX31 = false;
				}
				mv.addObject("block31", value);
				mv.addObject("flagX31", flagX31);
			}else {
				if(block31.equals("X")) {
					flagX31 = true;
					flag = false;
				}else {
					flagX31 = false;
					flag = true;
				}
				mv.addObject("block31", block31);
				mv.addObject("flagX31", flagX31);
			}
		}
		if(blockNumber==32) {
			if(block32.isEmpty()) {
				block32 = value;
				if(value.equals("X")) {
					flagX32 = true;
				}else {
					flagX32 = false;
				}
				mv.addObject("block32", value);
				mv.addObject("flagX32", flagX32);
			}else {
				if(block32.equals("X")) {
					flagX32 = true;
					flag = false;
				}else {
					flagX32 = false;
					flag = true;
				}
				mv.addObject("block32", block32);
				mv.addObject("flagX32", flagX32);
			}
		}
		if(blockNumber==33) {
			if(block33.isEmpty()) {
				block33 = value;
				if(value.equals("X")) {
					flagX33 = true;
				}else {
					flagX33 = false;
				}
				mv.addObject("block33", value);
				mv.addObject("flagX33", flagX33);
			}else {
				if(block33.equals("X")) {
					flagX33 = true;
					flag = false;
				}else {
					flagX33 = false;
					flag = true;
				}
				mv.addObject("block33", block33);
				mv.addObject("flagX33", flagX33);
			}
		}
		}
		else {
			mv.addObject("block11", block11);
			mv.addObject("flagX11", flagX11);
			mv.addObject("block12", block12);
			mv.addObject("flagX12", flagX12);
			mv.addObject("block13", block13);
			mv.addObject("flagX13", flagX13);
			mv.addObject("block21", block21);
			mv.addObject("flagX21", flagX21);
			mv.addObject("block22", block22);
			mv.addObject("flagX22", flagX22);
			mv.addObject("block23", block23);
			mv.addObject("flagX23", flagX23);
			mv.addObject("block31", block31);
			mv.addObject("flagX31", flagX31);
			mv.addObject("block32", block32);
			mv.addObject("flagX32", flagX32);
			mv.addObject("block33", block33);
			mv.addObject("flagX33", flagX33);
		}
		
		if(block11.equals(block12) && block12.equals(block13) && !block11.isEmpty()) {
			System.out.println(block11 + "is winner");
			winnerMessage = block11 + "-Player is the Winner!";
			gameStopped = true;
			classBlock11= true;
			classBlock12= true;
			classBlock13= true;
			mv.addObject("classBlock11", classBlock11);
			mv.addObject("classBlock12", classBlock12);
			mv.addObject("classBlock13", classBlock13);
		}
		else if(block21.equals(block22) && block21.equals(block23) && !block21.isEmpty()) {
			System.out.println(block21 + "is winner");
			winnerMessage = block21 + "-Player is the Winner!";
			gameStopped = true;
			classBlock21= true;
			classBlock22= true;
			classBlock23= true;
			mv.addObject("classBlock21", classBlock21);
			mv.addObject("classBlock22", classBlock22);
			mv.addObject("classBlock23", classBlock23);
		}
		else if(block31.equals(block32) && block32.equals(block33) && !block31.isEmpty()) {
			System.out.println(block31 + "is winner");
			winnerMessage = block31 + "-Player is the Winner!";
			gameStopped = true;
			classBlock31= true;
			classBlock32= true;
			classBlock33= true;
			mv.addObject("classBlock31", classBlock31);
			mv.addObject("classBlock32", classBlock32);
			mv.addObject("classBlock33", classBlock33);
		}
		else if(block11.equals(block21) && block21.equals(block31) && !block11.isEmpty()) {
			System.out.println(block11 + "is winner");
			winnerMessage = block11 + "-Player is the Winner!";
			gameStopped = true;
			classBlock11= true;
			classBlock21= true;
			classBlock31= true;
			mv.addObject("classBlock11", classBlock11);
			mv.addObject("classBlock21", classBlock21);
			mv.addObject("classBlock31", classBlock31);
		}
		else if(block12.equals(block22) && block12.equals(block32) && !block12.isEmpty()) {
			System.out.println(block12 + "is winner");
			winnerMessage = block12 + "-Player is the Winner!";
			gameStopped = true;
			classBlock12= true;
			classBlock22= true;
			classBlock32= true;
			mv.addObject("classBlock12", classBlock12);
			mv.addObject("classBlock22", classBlock22);
			mv.addObject("classBlock32", classBlock32);
		}
		else if(block13.equals(block23) && block13.equals(block33) && !block13.isEmpty()) {
			System.out.println(block13 + "is winner");
			winnerMessage = block13 + "-Player is the Winner!";
			gameStopped = true;
			classBlock13= true;
			classBlock23= true;
			classBlock33= true;
			mv.addObject("classBlock13", classBlock13);
			mv.addObject("classBlock23", classBlock23);
			mv.addObject("classBlock33", classBlock33);
		}
		else if(block11.equals(block22) && block11.equals(block33) && !block11.isEmpty()) {
			System.out.println(block11 + "is winner");
			winnerMessage = block11 + "-Player is the Winner!";
			gameStopped = true;
			classBlock11= true;
			classBlock22= true;
			classBlock33= true;
			mv.addObject("classBlock11", classBlock11);
			mv.addObject("classBlock22", classBlock22);
			mv.addObject("classBlock33", classBlock33);
		}
		else if(block13.equals(block22) && block13.equals(block31) && !block13.isEmpty()) {
			System.out.println(block13 + "is winner");
			winnerMessage = block13 + "-Player is the Winner!";
			gameStopped = true;
			classBlock13= true;
			classBlock22= true;
			classBlock31= true;
			mv.addObject("classBlock13", classBlock13);
			mv.addObject("classBlock22", classBlock22);
			mv.addObject("classBlock31", classBlock31);
		}
		
		if(gameStopped) {
			mv.addObject("winnerMessage", winnerMessage);
		}
		
		mv.setViewName("TicTacToe.html");
		return mv;
	}
	
	@GetMapping("/apps/TicTacToeGame/reset")
	public String Reset() {
		flag = true;
		block11 = "";
		block12 = "";
		block13 = "";
		block21 = "";
		block22 = "";
		block23 = "";
		block31 = "";
		block32 = "";
		block33 = "";
		flagX11 = false;
		flagX12 = false;
		flagX13 = false;
		flagX21 = false;
		flagX22 = false;
		flagX23 = false;
		flagX31 = false;
		flagX32 = false;
		flagX33 = false;
		gameStopped = false;
		classBlock11 = false;
		classBlock12 = false;
		classBlock13 = false;
		classBlock21 = false;
		classBlock22 = false;
		classBlock23 = false;
		classBlock31 = false;
		classBlock32 = false;
		classBlock33 = false;
		winnerMessage = "";
		return "redirect:/apps/TicTacToeGame";
	}
}
