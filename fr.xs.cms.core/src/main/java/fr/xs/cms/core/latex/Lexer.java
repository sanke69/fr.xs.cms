package fr.xs.cms.core.latex;

import java.util.*;

class Token {
	String token;
	int type;

	Token(String s, int t) {
		token = s;
		type = t;
	}

	public boolean isCommand() {
		if(type == 1)
			return true;
		return false;
	}

	public boolean isCommand(String s) {
		if(type == 1 && token.equals(s))
			return true;
		return false;
	}

	public boolean isConstant() {
		if(type == 0)
			return true;
		return false;
	}

	public boolean isConstant(String s) {
		if(isConstant() && token.equals(s))
			return true;
		return false;
	}

	public boolean equals(Object t) {
		Token t2 = (Token) t;
		if(t2.token.equals(token) && t2.type == type) {
			return true;
		}
		return false;
	}

	public String value() {
		return token;
	}

	public String toString() {
		return token + ":" + type;
	}
}

public class Lexer {
	Stack<Character> tex;
	public Stack<Token> t;

	Lexer(Stack<Character> z) {
		tex = z;
		t = new Stack<Token>();
		lex();
	}

	public void lex() {
		while(!tex.empty()) {
			// System.out.println(tex);
			// ArrayList<Character> cs = new ArrayList<Character>();
			Character c = tex.pop();
			if(c == '\\') {
				t.push(new Token(readCommand(), 1));
			} else if(c == '{' || c == '[' || c == ']' || c == '}') {
				t.push(new Token(c.toString(), 1));
			} else if(c == '$') {
				if(tex.peek() == '$') {
					tex.pop();
					t.push(new Token("$$", 1));
				} else {
					t.push(new Token("$", 1));
				}
			} else {
				t.push(new Token(c + readConstant(), 0));
			}
		}
		Stack<Token> z = new Stack<Token>();
		for(int i = 0; i < t.size(); i++) {
			z.push(t.get(t.size() - 1 - i));
		}
		t = z;
	}

	private String readCommand() {
		if(tex.empty()) {
			return "";
		}
		ArrayList<Character> cmd = new ArrayList<Character>();
		Character c = tex.peek();
		if(c == '[' || c == ']') {
			tex.pop();
			return "$$";
		}
		while(c == '*' || (48 <= c && c <= 57) || (65 <= c && c <= 90) || (97 <= c && c <= 122)) {
			cmd.add(c);
			tex.pop();
			if(tex.empty()) {
				break;
			}
			c = tex.peek();
		}
		String s = "";
		for(int i = 0; i < cmd.size(); i++) {
			s += cmd.get(i);
		}
		return s;
	}

	private String readConstant() {
		if(tex.empty()) {
			return "";
		}
		ArrayList<Character> cmd = new ArrayList<Character>();
		Character c = tex.peek();
		while(c != '{' && c != '}' && c != '\\' && c != '[' && c != ']' && c != '$') {
			cmd.add(c);
			tex.pop();
			if(tex.empty()) {
				break;
			}
			c = tex.peek();
		}
		String s = "";
		for(int i = 0; i < cmd.size(); i++) {
			s += cmd.get(i);
		}
		return s;
	}
}