package ch.pbs.benevole.renderer.core;

import com.google.common.collect.Maps;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

public class TemplateEngine {

	private final Map<String, Supplier<String>> context = Maps.newHashMap();

	public TemplateEngine() {
		context.put("today", () -> {
			final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			return sdf.format(new Date());
		});

		context.put("todayFrench", () -> {
			final SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMM yyyy", Locale.FRENCH);
			return sdf.format(new Date());
		});
	}

	public void add(final String key, final Supplier<String> value) {
		context.put(key, value);
	}

	public void add(final String key, final String value) {
		context.put(key, () -> value);
	}

	public String process(final String s) {
		String output = s;
		for (final Entry<String, Supplier<String>> entry : context.entrySet()) {
			output = output.replace(generateKey(entry.getKey()), entry.getValue().get());
		}
		return output;
	}

	public PdfText process(final PdfText p) {
		p.setValue(process(p.getValue()));
		return p;
	}

	private String generateKey(final String s) {
		return "${" + s + "}";
	}

}
