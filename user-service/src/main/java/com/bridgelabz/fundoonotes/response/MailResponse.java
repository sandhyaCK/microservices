package com.bridgelabz.fundoonotes.response;

/*
 *  author : Sandhya
 */

import org.springframework.stereotype.Component;

@Component
public class MailResponse {
public String fromMessage(String url,String token) {
	return  url +"/" +token;
}

}
