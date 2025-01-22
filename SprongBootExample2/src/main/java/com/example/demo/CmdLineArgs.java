package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CmdLineArgs implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("==== Cmd Line Arguments starts ===");
		System.out.println(args[0]);
		System.out.println(args[1]);
		System.out.println(Arrays.asList(args));
		System.out.println("==== Cmd Line Arguments Ends ===");
	}
}
