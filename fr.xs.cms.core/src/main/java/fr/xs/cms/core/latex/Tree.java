package fr.xs.cms.core.latex;

import java.util.*;

public class Tree {
	private List<Tree> Children = new ArrayList<Tree>();
	private String data;

	public Tree(String data) {
		this.data = data;
	}

	public Tree() {
		this.data = "";
	}

	public Tree getChild(int i) {
		return Children.get(i);
	}

	public void setChild(int i, Tree t) {
		Children.set(i, t);
	}

	public boolean isLeaf() {
		if(Children.size() == 0) {
			return true;
		}
		return false;
	}

	public void addChild(Tree t) {
		Children.add(t);
	}

	public void addChild(String s) {
		Children.add(new Tree(s));
	}

	public List<Tree> getChildren(Tree t) {
		return Children;
	}

	public void addAllChild(Tree t) {
		List<Tree> oc = t.getChildren(t);
		for(int i = 0; i < oc.size(); i++) {
			Children.add(oc.get(i));
		}
	}

	public String toString() {
		if(isLeaf()) {
			return data;
		}
		StringBuffer s = new StringBuffer();
		for(int i = 0; i < Children.size(); i++) {
			s.append(Children.get(i).toString());
		}
		return s.toString();
	}
}