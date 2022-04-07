package com.example.demo.controller;

import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.github.javafaker.Faker;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public String index(@ModelAttribute User user, Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("users", userRepository.findAll());
		return "user";
	}
	
	@GetMapping("/{id}")
	public String get(@PathVariable("id") Long id, Model model) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("user", userRepository.getById(id));
		model.addAttribute("users", userRepository.findAll());
		return "user";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		userRepository.deleteById(id);
		return "redirect:../";
	}
	
	@PostMapping("/")
	public String add(@Valid @ModelAttribute User user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "POST");
			model.addAttribute("users", userRepository.findAll());
			return "user";
		}
		userRepository.save(user);
		return "redirect:./";
	}
	
	@PutMapping("/")
	public String update(@Valid @ModelAttribute User user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "PUT");
			model.addAttribute("users", userRepository.findAll());
			return "user";
		}
		// 根據 JPA 規範
		// 若有 user.id 則進行數據更新
		userRepository.save(user);
		return "redirect:./";
	}
	
	
	//------------------------------------------------------------------------------------
	
	@GetMapping("/add/sample/data")
	@ResponseBody
	public String addSampleData() {
		Faker faker = new Faker();
		Random random = new Random();
		for(int i=0;i<150;i++) {
			User user = new User();
			user.setName(faker.name().firstName());
			user.setPassword(String.format("%04d", random.nextInt(10000))); // 0000~9999
			user.setBirth(faker.date().birthday());
			user.setHeight(random.nextInt(51) + 150);
			user.setWeight(random.nextInt(20) + (int)((user.getHeight()-80) * 0.7));
			userRepository.save(user);
		}
		return "Add Sample Data OK";
	}
	
	@GetMapping("/list/sample/data")
	@ResponseBody
	public List<User> listSampleData() {
		return userRepository.findAll();
	}
	
}
