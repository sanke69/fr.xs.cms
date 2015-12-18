package fr.xs.cms.core.latex;

import java.util.*;

public class Parser {
	public static Stack<Token> read;
	public static Stack<Token> tokens;
	public static final int CMD = 1;
	public static final int CONST = 0;
	public static final Token ENVEND = new Token("end", 1);
	public static final Token CMDEND = new Token("}", 1);
	public static Counter counters;

	public static void main(String[] args) {
		counters = new Counter();
		// the loop that parses everything
		read = new Stack<Token>();
		Stack<Character> tex = new Stack<Character>();
		Scanner in = new Scanner(System.in);
		StringBuffer s = new StringBuffer();
		while(in.hasNext()) {
			s.append(in.nextLine()).append("\n");
		}
		for(int i = s.length() - 1; i > -1; i--) {
			tex.push(s.charAt(i));
		}
		Lexer l = new Lexer(tex);
		tokens = l.t;
		// System.out.println(tokens);
		Tree parsed = parse(new Token("", 4));
		System.out.println(parsed);
		in.close();
	}

	public static Tree parse() {
		return parse(CMDEND);
	}

	public static Tree parse(ArrayList<Token> end) {
		Tree tree = new Tree("");
		while(true) {
			if(tokens.empty()) {
				return tree;
			}
			Token t = tokens.pop();
			boolean eqend = false;
			for(int i = 0; i < end.size(); i++) {
				if(t.equals(end.get(i))) {
					eqend = true;
					break;
				}
			}
			if(eqend) {
				tokens.push(t);
				return tree;
			} else if(t.isCommand()) {
				tree.addChild(command(t.token));
			} else {
				tree.addChild(new Tree(t.token));
			}
		}
	}

	public static Tree parse(Token end) {
		ArrayList<Token> ends = new ArrayList<Token>();
		ends.add(ENVEND);
		// ends.add(CMDEND);
		ends.add(end);
		return parse(ends);
	}

	public static Tree command(String cmd) {
		// if the command has 1 optional, 2 optional
		Tree t = new Tree("\\" + cmd);
		if(cmd.equals("textbf")) {
			t = cmd_textFORMAT("strong");
		} else if(cmd.equals("texttt")) {
			t = cmd_textFORMAT("tt");
		} else if(cmd.equals("emph")) {
			t = cmd_textFORMAT("em");
		} else if(cmd.equals("textsuperscript")) {
			t = cmd_textFORMAT("sup");
		} else if(cmd.equals("$")) {
			t = cmd_inlinemath();
		} else if(cmd.equals("$$")) {
			t = cmd_displaymath();
		} else if(cmd.equals("begin")) {
			t = cmd_begin();
		} else if(cmd.equals("[") || cmd.equals("]")) {
			t = new Tree(cmd);
		} else if(cmd.equals("}")) {
			t = new Tree("");
		} else if(cmd.equals("end")) {
			t = cmd_end();
		} else if(cmd.equals("section")) {
			t = cmd_section();
		} else if(cmd.equals("subsection")) {
			t = cmd_subsection();
		} else if(cmd.equals("subsubsection")) {
			t = cmd_subsubsection();
		} else if(cmd.equals("section*")) {
			t = cmd_textFORMAT("h3");
		} else if(cmd.equals("subsection*")) {
			t = cmd_textFORMAT("h4");
		} else if(cmd.equals("subsubsection*")) {
			t = cmd_textFORMAT("h5");
		}
		return t;
	}

	public static Tree cmd_section() {
		Tree t = new Tree();
		counters.addToCounter("section", 1);
		counters.setCounter("subsection", 0);
		counters.setCounter("subsubsection", 0);
		t.addChild("<h3>" + counters.value("section") + ".");
		t.addAllChild(parseParameter());
		t.addChild("</h3>");
		return t;
	}

	public static Tree cmd_subsection() {
		Tree t = new Tree();
		counters.addToCounter("subsection", 1);
		counters.setCounter("subsubsection", 0);
		t.addChild("<h4>" + counters.value("section") + "." + counters.value("subsection") + ".");
		t.addAllChild(parseParameter());
		t.addChild("</h4>");
		return t;
	}

	public static Tree cmd_subsubsection() {
		Tree t = new Tree();
		counters.addToCounter("subsubsection", 1);
		t.addChild("<h5>" + counters.value("section") + "." + counters.value("subsection") + "." + counters.value("subsubsection") + ".");
		t.addAllChild(parseParameter());
		t.addChild("</h5>");
		return t;
	}

	public static Tree cmd_begin() {
		Tree t = new Tree();
		Tree param = parseParameter();

		t.addChild("\\begin{");
		t.addAllChild(param);
		t.addChild("}");
		String env = param.toString();
		tokens.pop();
		if(env.equals("theorem")) {
			t = env_theorem("<blockquote class='theorem'>", "</blockquote>");
		} else if(env.equals("definition")) {
			t = env_theorem("<blockquote class='definition'>", "</blockquote>");
		} else if(env.equals("lemma")) {
			t = env_theorem("<blockquote class='lemma'>", "</blockquote>");
		} else if(env.equals("remark")) {
			t = env_theorem("<blockquote class='remark'>", "</blockquote>");
		} else if(env.equals("quotation")) {
			t = env_theorem("<blockquote class='quotation'>", "</blockquote>");
		} else if(env.equals("problem")) {
			t = env_theorem("<blockquote class='problem'>", "</blockquote>");
		} else if(env.equals("proof")) {
			t = env_theorem("<p><em>Proof .</em>", " Q.E.D.</p>");
		} else if(env.equals("enumerate")) {
			t = env_enumerate();
		} else if(env.equals("itemize")) {
			t = env_itemize();
		} else if(env.equals("document")) {
			t = env_document();
		} else if(isMathEnv(env)) {
			t = env_MATHIGNORE(env);
		} else {
			t.addAllChild(parse());
			t.addChild("\\end{");
			t.addAllChild(param);
			t.addChild("}");
		}
		// remove the \end part
		tokens.pop();
		cmd_end();
		return t;
	}

	public static Tree cmd_end() {
		parseParameter();
		return new Tree("");
	}

	public static Tree cmd_inlinemath() {
		Tree t = new Tree("");
		t.addChild("$");
		// search for the next $
		while(!tokens.peek().isCommand("$")) {
			t.addChild(plain(tokens.pop()));
		}
		tokens.pop();
		t.addChild("$");
		return t;
	}

	public static Tree cmd_displaymath() {
		Tree t = new Tree("");
		t.addChild("\\[");
		// search for the next $$
		while(!tokens.peek().isCommand("$$")) {
			t.addChild(plain(tokens.pop()));
		}
		tokens.pop();
		t.addChild("\\]");
		return t;
	}

	public static String plain(Token t) {
		if(t.isCommand() && !t.token.equals("{") && !t.token.equals("}") && !t.token.equals("$") && !t.token.equals("$$") && !t.token.equals("[") && !t.token.equals("]")) {
			return "\\" + t.token;
		}
		return t.token;
	}

	public static Tree cmd_textFORMAT(String s) {
		return cmd_textFORMAT("<" + s + ">", "</" + s + ">");
	}

	public static Tree cmd_textFORMAT(String pre, String suf) {
		Tree t = new Tree("");
		t.addChild(new Tree(pre));
		t.addChild(parseParameter());
		t.addChild(new Tree(suf));
		return t;
	}

	public static Tree parseOptionalParameter(Tree def) {
		if(tokens.peek().isConstant()) {
			Token to = tokens.pop();
			String s = ltrim(to.token);
			if(s.length() != 0) {
				tokens.push(to);
				return def;
			}
		}
		if(tokens.peek().isCommand("[")) {
			tokens.pop();
			Tree t = new Tree("");
			// search for the next RIGHTSQUAREBRACKET
			while(!tokens.peek().isCommand("]")) {
				if(tokens.peek().isCommand("{")) {
					tokens.pop();
					t.addAllChild(parse(CMDEND));
				} else {
					t.addChild(plain(tokens.pop()));
				}
			}
			tokens.pop();
			return t;
		}
		return def;
	}

	public static Tree parseParameter() {
		if(tokens.peek().isConstant()) {
			String s = ltrim(tokens.pop().token);
			if(s.length() != 0) {
				Token to = new Token(s.substring(1), CONST);
				tokens.push(to);
				return new Tree(String.valueOf(s.charAt(0)));
			}
		}
		// this means we have a white space
		tokens.pop();
		return parse(CMDEND);
	}

	public static Tree env_theorem(String pre, String suf) {
		Tree t = new Tree("");
		// read optional parameter
		Tree def = new Tree("");
		t.addChild(pre);
		Tree optional = parseOptionalParameter(def);
		if(!optional.isLeaf()) {
			t.addChild("(");
			t.addAllChild(optional);
			t.addChild(")");
		}
		t.addAllChild(parse(ENVEND));
		t.addChild(suf);
		return t;
	}

	public static Tree env_document() {
		return cmd_textFORMAT("<article>", "</article>");
	}

	public static Tree env_enumerate() {
		Tree t = new Tree();
		t.addChild(new Tree("<ol>\n"));
		t.addAllChild(item_helper());
		t.addChild(new Tree("</ol>\n"));
		return t;
	}

	public static Tree env_itemize() {
		Tree t = new Tree();
		t.addChild(new Tree("<ul>\n"));
		t.addAllChild(item_helper());
		t.addChild(new Tree("</ul>\n"));
		return t;
	}

	public static Tree env_MATHIGNORE(String env) {
		Tree t = new Tree();
		t.addChild("\\begin{" + env + "}");
		while(true) {
			while(!tokens.peek().equals(ENVEND)) {
				t.addChild(plain(tokens.pop()));
			}
			Token to = tokens.pop();
			Token to2 = tokens.pop();
			if(tokens.empty() || tokens.peek().isConstant(env)) {
				tokens.push(to2);
				tokens.push(to);
				break;
			} else {
				t.addChild(plain(to));
				t.addChild(plain(to2));
			}
		}
		t.addChild("\\end{" + env + "}");
		return t;
	}

	public static Tree item_helper() {
		Tree t = new Tree("");
		while(tokens.peek().isConstant()) {
			tokens.pop();
		}
		while(tokens.peek().isCommand("item")) {
			tokens.pop();
			t.addChild("<li>\n");
			t.addChild((parse(new Token("item", 1))).toString().trim());
			t.addChild("\n</li>\n");
		}
		return t;
	}

	public static String ltrim(String s) {
		int i = 0;
		while(i < s.length() && Character.isWhitespace(s.charAt(i))) {
			i++;
		}
		return s.substring(i);
	}

	public static boolean isMathEnv(String s) {
		String[] mathenv = { "align", "align*", "alignat", "alignat*", "aligned", "alignedat", "array", "Bmatrix", "bmatrix", "cases", "eqnarray", "eqnarray*", "equation", "equation*", "gather", "gather*", "gathered", "matrix", "multline", "multline*",
				"pmatrix", "smallmatrix", "split", "subarray", "Vmatrix", "vmatrix" };
		HashSet<String> h = new HashSet<String>();
		for(int i = 0; i < mathenv.length; i++) {
			h.add(mathenv[i]);
		}
		if(h.contains(s)) {
			return true;
		}
		return false;
	}
}