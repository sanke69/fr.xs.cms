package fr.xs.cms.themes.base;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.objects.HtmlAnchor;
import fr.xs.cms.core.html.objects.HtmlDiv;
import fr.xs.cms.core.html.objects.HtmlInnerContent;
import fr.xs.cms.themes.base.articles.enlighted.BaseEncapsulatedArticle;
import fr.xs.jtk.helpers.MediaHelper;

public abstract class BasePageWithWidgetsForFolder extends BasePageWithWidgets {
	public abstract String getSectionName();
	public abstract String getDataPath();

	public abstract String getUrl(String _file);

	public BasePageWithWidgetsForFolder(String _file) {
		super();

		if(_file != null) {
			String file   = _file.indexOf("/") != 0 ? "/" + _file : _file;
			String folder = !new File(getDataPath() + file).isFile() ? file : file.substring(0, file.lastIndexOf("/") + 1);
	
			initializeQuickAccess(folder);
			initializeTree(file);
			initializeContentPane(file);
		} else {
			initializeQuickAccess(null);
			initializeContentPane(null);
		}
	}

	public void initializeQuickAccess(String _folder) {
		for(String d : getDirectories( getDataPath() ))
			getQuickAccessWidget().add(getUrl(d + "/"), "=>\t" + d);

		if(_folder != null) {
			for(String d : getDirectories(getDataPath() + _folder))
				getQuickAccessWidget().add(getUrl(_folder + d + "/"), "->\t" + d);
			for(String f : getFiles(getDataPath() + _folder)) {
//				getContentPane().addChild(BaseEncapsulatedArticle.fromString("DEBUG:: ", f.substring(f.lastIndexOf("."))));
				if(!f.equalsIgnoreCase("README.MD") && !f.substring(f.lastIndexOf(".")).equalsIgnoreCase(".css"))
					getQuickAccessWidget().add(getUrl(_folder + f), "-\t" + getFileLabel(f));
			}
		}
	}
	public void initializeContentPane(String _path) {
		File target = new File(_path != null ? getDataPath() + _path : getDataPath());
		if(target.isFile()) {
			String ext     = _path.substring(_path.lastIndexOf(".") + 1);
			String cssFile = _path.substring(0, _path.lastIndexOf(".")) + ".css";

//			getContentPane().addChild(BaseEncapsulatedArticle.fromString("DEBUG:: ", cssFile + "  " + new File(getDataPath() + cssFile).exists()));
//			getContentPane().addChild(BaseEncapsulatedArticle.fromString("DEBUG:: ", MediaHelper.getContentAsString(getDataPath() + cssFile)));

			boolean useCss = new File(getDataPath() + cssFile).exists(); 
			if(useCss)
				getHead().includeCss(MediaHelper.getContentAsString(getDataPath() + cssFile));

			switch(ext) {
			case "html" : getContentPane().addChild(BaseEncapsulatedArticle.fromHtml		(getDataPath() + _path, useCss ? "customize" : "content-entry", useCss ? null : "content-entry-title")); break;
			case "MD"   : getContentPane().addChild(BaseEncapsulatedArticle.fromMarkdown	(getDataPath() + _path, useCss ? "customize" : "content-entry", useCss ? null : "content-entry-title")); break;
			case "tex"  : getContentPane().addChild(BaseEncapsulatedArticle.fromLatex		(getDataPath() + _path, useCss ? "customize" : "content-entry", useCss ? null : "content-entry-title")); break;
			}
		} else {
			if(_path == null) {
				if(new File(getDataPath() + "/README.MD").exists())
					getContentPane().addChild(BaseEncapsulatedArticle.fromMarkdown(getDataPath() + "/README.MD"));
			} else {
				if(new File(getDataPath() + _path + "/README.MD").exists())
					getContentPane().addChild(BaseEncapsulatedArticle.fromMarkdown(getDataPath() + _path + "/README.MD"));
			}
		}
	}
	public void initializeTree(String _path) {
		ArrayList<String> LinkCollection = new ArrayList<String>(); 		
		StringTokenizer tokens = new StringTokenizer(_path, "/");
		while(tokens.hasMoreTokens())
			LinkCollection.add(tokens.nextToken());

		HtmlObject tree = new HtmlDiv().setClassCss("tree");
		
		String[]     token = new String[LinkCollection.size()];
		for(int i = 0; i < token.length; ++i) {
			token[i] = i != 0 ? token[i-1] + "/" + LinkCollection.get(i) : "/" + LinkCollection.get(i);
			if(new File(getDataPath() + token[i]).isDirectory()) token[i] += "/";
			
			HtmlAnchor a = new HtmlAnchor();
			a.setLabel(LinkCollection.get(i));
			a.setHRef(getUrl(token[i]));

			tree.addChild(new HtmlInnerContent("/"));
			tree.addChild(a);
		}
		getContentPane().addChild(tree);
	}

	public Collection<String> getDirectories(String _path) {
		Collection<String> directories = new ArrayList<String>();
		File[] listOfDirectories = new File(_path).listFiles();
		if(listOfDirectories != null)
			for(File d : listOfDirectories)
				if(d.isDirectory())
					directories.add(d.getName());
		return directories;
	}
	public Collection<String> getFiles(String _path) {
		Collection<String> files = new ArrayList<String>();
		File[] listOfFiles = new File(_path).listFiles();
		if(listOfFiles != null)
			for(File f : listOfFiles)
				if(f.isFile())
					files.add(f.getName());
		return files;
	}
	
	public String getFileLabel(String _file) {
		String withoutExt = _file.substring(0, _file.lastIndexOf("."));

		for(int y = 0; y < withoutExt.length(); y++) {
			if(Character.isUpperCase(withoutExt.charAt(y))) {
				withoutExt = withoutExt.substring(0, y) + " " + withoutExt.substring(y);
				y++;
			}
		}
		return withoutExt;
	}

}
