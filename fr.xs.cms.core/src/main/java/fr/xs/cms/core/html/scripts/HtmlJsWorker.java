package fr.xs.cms.core.html.scripts;

public class HtmlJsWorker {

	public void workerScript() {
		String html = "";
		html += "var w;";

		html += "function startWorker() {";
		html += "    if(typeof(Worker) !== 'undefined') {";
		html += "        if(typeof(w) == 'undefined') {";
		html += "            w = new Worker('demo_workers.js');";
		html += "        }";
		html += "        w.onmessage = function(event) {";
		html += "            document.getElementById('result').innerHTML = event.data;";
		html += "        };";
		html += "    } else {";
		html += "        document.getElementById('result').innerHTML = 'Sorry! No Web Worker support.';";
		html += "    }";
		html += "}";

		html += "function stopWorker() {";
		html += "    w.terminate();";
		html += "    w = undefined;";
		html += "}";
	}
}
