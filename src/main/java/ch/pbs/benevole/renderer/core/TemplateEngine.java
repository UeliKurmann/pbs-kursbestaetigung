package ch.pbs.benevole.renderer.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

import jersey.repackaged.com.google.common.collect.Maps;

public class TemplateEngine {

	private Map<String, Supplier<String>> context = Maps.newHashMap();

	public TemplateEngine() {
		context.put("today", () -> {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			return sdf.format(new Date());
		});
	}

	public void add(String key, Supplier<String> value) {
		context.put(key, value);
	}

	public void add(String key, String value) {
		context.put(key, () -> value);
	}

	public String process(String s) {
		String output = s;
		for (Entry<String, Supplier<String>> entry : context.entrySet()) {
			output = output.replace(generateKey(entry.getKey()), entry.getValue().get());
		}
		return output;
	}

	public PdfText process(PdfText p) {
		PdfText output = p;
		while (output != null) {
			output.setValue(process(output.getValue()));
			output = output.getNext();
		}
		return p;
	}

	private String generateKey(String s) {
		return "${" + s + "}";
	}

}
