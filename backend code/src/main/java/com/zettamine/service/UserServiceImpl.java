package com.zettamine.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Service;

import com.zettamine.Repository.UserRepo;
import com.zettamine.dto.LoginDto;
import com.zettamine.dto.UserDto;
import com.zettamine.email.EmailService;
import com.zettamine.entity.User;
import com.zettamine.exception.ResourceAlreadyExistsException;
import com.zettamine.mapper.UserMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
	
	
	private UserRepo userRepo;
	private IRoleService roleService;
	private EmailService emailService; 
	
	
	public void save(UserDto userDto) {
		Optional<User> optUser = userRepo.findByEmail(userDto.getEmail());
		System.err.println(optUser.isPresent());
		if(optUser.isPresent()) {
			
			throw new ResourceAlreadyExistsException("User is already exists with this email: "+userDto.getEmail());	
		}
		
		User user = UserMapper.dtoToEntity(new User(), userDto);
		user.setTempPwd(generatePassayPassword());
		user.setAccountLock(false);
		user.setLoginStatus(0);
		user.setAttempts(0);
		user.setRole(roleService.getByRoleId(userDto.getRoleId()));
		
		
		
		try {
            if (!emailService.sendEmail(user)) {
            	throw new RuntimeException("Failed to send email or save user try");
			}
            userRepo.save(user);
        } catch (Exception e) {
            log.error("Failed to send email or save user: {}", e.getMessage());
            throw new RuntimeException("Failed to send email or save user", e);
        }
		
			
	}
	
	
	private static String generatePassayPassword() {

		PasswordGenerator gen = new PasswordGenerator();
		CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
		CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
		lowerCaseRule.setNumberOfCharacters(2);

		CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
		CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
		upperCaseRule.setNumberOfCharacters(2);

		CharacterData digitChars = EnglishCharacterData.Digit;
		CharacterRule digitRule = new CharacterRule(digitChars);
		digitRule.setNumberOfCharacters(2);

		CharacterData specialChars = new CharacterData() {
			public String getErrorCode() {
				return "cannot generate special characters";
			}

			public String getCharacters() {
				return "!@#$%^&*()_+";
			}
		};
		CharacterRule splCharRule = new CharacterRule(specialChars);
		splCharRule.setNumberOfCharacters(2);

		String password = gen.generatePassword(10, splCharRule, lowerCaseRule, upperCaseRule, digitRule);

		return password;
	}


	@Override
	public Boolean checkValidUser(String email) {
		
		Optional<User> optUser = userRepo.findByEmail(email);
		
		if(optUser.isPresent()) {
			return true;
		}
		
		return false;		
	}

	
	@Override
	public Map<String, String> checkValidPassword(LoginDto dto) {
		
		Map<String, String> logInStatus = new HashMap();
		User user = userRepo.findByEmail(dto.getEmail()).get();
		if (user.isAccountLock() || user.getAttempts()>=3) {
			return null;
		}
		Integer status = user.getLoginStatus();
		if (status==0) {
			if (!dto.getPassword().equals(user.getTempPwd())) {
				user.setAttempts(user.getAttempts()+1);
				if (user.getAttempts()>=3) {
					user.setAccountLock(true);
				}
				
				userRepo.save(user);
				logInStatus.put("attempts",user.getAttempts().toString());
				
				return logInStatus;
			}
		}else if(status==1) {
			
			if(dto.getPassword().equals(user.getTempPwd())){
				return null;
			}
			
			
			if (!dto.getPassword().equals(user.getPassword())) {
				user.setAttempts(user.getAttempts()+1);
				if (user.getAttempts()>=3) {
					user.setAccountLock(true);
				}
				
				userRepo.save(user);
				logInStatus.put("attempts",user.getAttempts().toString());
				
				return logInStatus;
			}
		}
		logInStatus.put("firstName", user.getFirstName());
		logInStatus.put("lastName", user.getLastName());
		logInStatus.put("role", user.getRole().getRoleName());
		logInStatus.put("logInStatus", user.getLoginStatus().toString());
		logInStatus.put("email", user.getEmail());
		
		user.setAttempts(0);
		userRepo.save(user);
		return logInStatus;
	}
	
	@Override
	public Boolean updatePassword(LoginDto dto) {
		
		User user = userRepo.findByEmail(dto.getEmail()).get();
		
		user.setPassword(dto.getPassword());
		user.setLoginStatus(1);
		try {
			userRepo.save(user);
			return true;
		}
		catch(Exception ex){
			
			return false;
		}
			
	}
	
	@Override
	public List<UserDto> userDetails() {
		
		List<UserDto> userDtoList = new ArrayList<>();
		List<User> userList = userRepo.findAll();
		
		for(User userInList:userList) {
		      UserDto dto = UserMapper.entityToDto(userInList, new UserDto());
		      userDtoList.add(dto);		      
		}		
		return userDtoList;	
	}
	
	
	
	
	

}
