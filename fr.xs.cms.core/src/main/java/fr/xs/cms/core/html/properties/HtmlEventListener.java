package fr.xs.cms.core.html.properties;

import java.util.HashMap;
import java.util.Map;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.scripts.HtmlCallBack;

public class HtmlEventListener {

	public enum Type {
		Click		( 3, "onClick", 	"click"),		// Se produit lorsque l'utilisateur clique sur l'élément associé à l'événement.
		Load		(11, "onLoad", 		"load"),		// Se produit lorsque le navigateur de l'utilisateur charge la page en cours
		Unload		(18, "onUnload", 	"unload"),		// Se produit lorsque le navigateur de l'utilisateur quitte la page en cours
		MouseOver	(12, "onMouseOver", "mouseover"),	// Se produit lorsque l'utilisateur positionne le curseur de la souris au-dessus d'un élément
		MouseOut	(13, "onMouseOut", 	"mouseout"),	// Se produit lorsque le curseur de la souris quitte un élément.
		Focus		( 7, "onFocus", 	"focus"),		// Se produit lorsque l'utilisateur donne le focus à un élément, c'est-à-dire que cet élément est sélectionné comme étant l'élément actif
		Blur		( 1, "onBlur", 		"blur"),		// Se produit lorsque l'élément perd le focus, c'est-à-dire que l'utilisateur clique hors de cet élément, celui-ci n'est alors plus sélectionné comme étant l'élément actif.
		Change		( 2, "onChange", 	"change"),		// Se produit lorsque l'utilisateur modifie le contenu d'un champ de données.
		Select		(16, "onSelect", 	"select"),		// Se produit lorsque l'utilisateur sélectionne un texte (ou une partie d'un texte) dans un champ de type "text" ou "textarea"
		Submit		(17, "onSubmit", 	"submit"),		// Se produit lorsque l'utilisateur clique sur le bouton de soumission d'un formulaire (le bouton qui permet d'envoyer le formulaire)

		Abort		( 0, "onAbort", 	"abort"),		// Cet événement a lieu lorsque l'utilisateur interrompt le chargement de l'image
		dblclick	( 4, "onDblclick", 	"dblclick"),	// Se produit lorsque l'utilisateur double-clique sur l'élément associé à l'événement (un lien hypertexte ou un élément de formulaire).
		dragdrop	( 5, "onDragdrop", 	"dragdrop"),	// Se produit lorsque l'utilisateur effectue un glisser-déposer sur la fenêtre du navigateur.
		error		( 6, "onError", 	"error"),		// Se déclenche lorsqu'une erreur apparaît durant le chargement de la page.
		keydown		( 8, "onKeydown", 	"keydown"),		// Se produit lorsque l'utilisateur appuie sur une touche de son clavier.
		keypress	( 9, "onKeypress", 	"keypress"),	// Se produit lorsque l'utilisateur maintient une touche de son clavier enfoncée.
		keyup		(10, "onKeyup", 	"keyup"),		// Se produit lorsque l'utilisateur relâche une touche de son clavier préalablement enfoncée.
		Reset		(14, "onReset", 	"reset"),		// Se produit lorsque l'utilisateur efface les données d'un formulaire à l'aide du bouton Reset.
		Resize		(15, "onResize", 	"resize");		// Se produit lorsque l'utilisateur redimensionne la fenêtre du navigateur

		int id;
		String value, value_alt;

		Type(int _id, String _value, String _value_alt) { id = _id; value = _value; }

		public String toString() {
			return value;
		}
		public String toStringAlt() {
			return value_alt;		
		}
	}
	/*
	Evénements		Objets concernés
	abort			Image
	blur			Button, Checkbox, FileUpload, Layer, Password, Radio, Reset, Select, Submit, Text, TextArea, window
	change			FileUpload, Select, Submit, Text, TextArea
	click			Button, document, Checkbox, Link, Radio, Reset, Select, Submit
	dblclick		document, Link
	dragdrop		window
	error			Image, window
	focus			Button, Checkbox, FileUpload, Layer, Password, Radio, Reset, Select, Submit, Text, TextArea, window
	keydown			document, Image, Link, TextArea
	keypress		document, Image, Link, TextArea
	keyup			document, Image, Link, TextArea
	load			Image, Layer, window
	mousedown		Button, document, Link
	mousemove		Aucun spécifiquement
	mouseout		Layer, Link
	mouseover		Area, Layer, Link
	mouseup			Button, document, Link
	move			window
	reset			form
	resize			window
	select			text, Textarea
	submit			Form
	unload			window
	*/

	Map<Type, HtmlCallBack> callbacks;

	public HtmlEventListener() {
	}

	public HtmlEventListener setCallBackMap(Map<Type, HtmlCallBack> _callbacks) {
		callbacks = _callbacks;
		return this;
	}
	public HtmlEventListener addOnClickCallBack(HtmlCallBack _callback) {
		if(callbacks == null)
			callbacks = new HashMap<Type, HtmlCallBack>();
		callbacks.put(Type.Click, _callback);
		return this;
	}

	public HtmlEventListener addACallBack(Type _type, HtmlCallBack _callback) {
		if(callbacks == null)
			callbacks = new HashMap<Type, HtmlCallBack>();
		callbacks.put(_type, _callback);
		return this;
	}

	public String toHtml() {
		String html = "";

		if(callbacks != null)
			if(callbacks.size() != 0)
				for(Map.Entry<Type, HtmlCallBack> cb : callbacks.entrySet())
					html += cb.getKey().toString() + "='" + cb.getValue().toHtml() + ";' ";

		return html;
	}

	public String toHtmlExcept(Type... _except) {
		String html = "";

		if(callbacks != null)
			if(callbacks.size() != 0)
				for(Map.Entry<Type, HtmlCallBack> cb : callbacks.entrySet()) {
					boolean add = true;
					for(Type t : _except)
						for(Type u : Type.values())
							if(t.equals(u))
								add = false;
					if(add)
						html += cb.getKey().toString() + "='" + cb.getValue().toHtml() + ";' ";
				}

		return html;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public class Script {

		String addEventListener(HtmlObject _obj, Type _event, HtmlCallBack _callback) {
			return "document.getElementById('" + _obj.getId() + "')." + _event.value + "='" + _callback.toHtml() + "';";
		}

		String addEventListener_v2(HtmlObject _obj, Type _event, HtmlCallBack _callback) {
			return "document.getElementById('" + _obj.getId() + "').addEventListener('click', " + _callback.toHtml() + ", false);";
		}
		String rmvEventListener_v2(HtmlObject _obj, Type _event, HtmlCallBack _callback) {
			return "document.getElementById('" + _obj.getId() + "').removeEventListener('click', " + _callback.toHtml() + ", false);";
		}

		String addEventListener_v3(HtmlObject _obj, Type _event, HtmlCallBack _callback) {
			//_callback.setArguments("toto", "tata");
			return "document.getElementById('" + _obj.getId() + "').setAttribute('onclick', '" + _callback.toHtml() + "');";
		}
		String rmvEventListener_v3(HtmlObject _obj, Type _event, HtmlCallBack _callback) {
			return "document.getElementById('" + _obj.getId() + "').removeAttribute('onclick');";
		}

	}

}
