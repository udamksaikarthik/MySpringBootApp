package com.spring.demo.service.rpsgame;

import java.util.HashMap;

public interface RpsGameServiceImpl {

	HashMap<String, String> whoWon(String userChoice, String computerChoice);

}
