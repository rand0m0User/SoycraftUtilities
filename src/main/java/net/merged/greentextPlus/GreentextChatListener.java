package net.merged.greentextPlus;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.kyori.adventure.text.format.TextColor;

public class GreentextChatListener implements Listener {
	// the list of all text effects, use it as a cheatsheet, request more to be
	// added on /craft/
	private final List<Attribute> formattingAttribute = new ArrayList<>(Arrays.asList(
			new Attribute(false, ">", NamedTextColor.GREEN, null), 
			new Attribute(false, "<", NamedTextColor.GOLD, null),
			new Attribute(false, "^", NamedTextColor.DARK_PURPLE, null),
			new Attribute(true, "====", NamedTextColor.DARK_RED, TextDecoration.BOLD), //red "glow" text
			new Attribute(true, "===", NamedTextColor.DARK_RED, null),
			new Attribute(true, "==", NamedTextColor.RED, null),
			new Attribute(true, "=", NamedTextColor.LIGHT_PURPLE, null), // doll text
			new Attribute(true, "----", NamedTextColor.DARK_BLUE, TextDecoration.BOLD), //blue "glow" text
			new Attribute(true, "---", NamedTextColor.DARK_BLUE, null),
			new Attribute(true, "--", NamedTextColor.BLUE, null), 
			new Attribute(true, "-", NamedTextColor.AQUA, null),
			new Attribute(true, "+++", NamedTextColor.BLACK, null),
			new Attribute(true, "++", NamedTextColor.DARK_GRAY, null), // soot text
			new Attribute(true, "+", NamedTextColor.GRAY, null), 
			new Attribute(true, "||", NamedTextColor.YELLOW, null),
			new Attribute(true, "::", NamedTextColor.GOLD, TextDecoration.BOLD),
			new Attribute(true, "%%", NamedTextColor.DARK_GREEN, TextDecoration.BOLD),
			new Attribute(true, "~~", -1, TextDecoration.STRIKETHROUGH),
			new Attribute(true, "__", -1, TextDecoration.UNDERLINED),
			new Attribute(true, "'''", -1, TextDecoration.BOLD), 
			new Attribute(true, "''", -1, TextDecoration.ITALIC),
			new Attribute(true, "~-~", -1, null), // color text
			new Attribute(true, "&&", 9127187, null) // caca text
	));

	private static final String[][] wordFilters = new String[][] {
			// FORMAT: new String[] { "new word", "/regex that matches for old word/" },
			new String[] { "\u2620", Pattern.quote(":skull:") },
			new String[] { "shitskin", "[aA\u0430\u0440][rR][yYiI\u0443][aA\u0430\u0440][nN]" },
			new String[] { "chemical castration drugs", "[hH]\s*[rR]\s*[tT]|estrogen" },
			new String[] { "dnwo", "[bB8\u0441\u0412][nN][wW][oO0]" },
			// new String[] { "big indoraptor cock",
			// "[bB8\u0441\u0412iI]\s*[Ii1l]\s*[cC\u0421\u0441pP]" },
			new String[] { "big baryonyx cock", "[bB8\u0441\u0412]\s*[bB8\u0441\u0412]\s*[cC\u0421\u0441pP]" },
			new String[] { "big baryonyx cock",
					"[bB8\u0441\u0412][eEiI]{1,}\s*[bB8\u0441\u0412iI][eEiI]{1,}\s*[cCsS][eEiI]{1,}" },
			new String[] { "tiny mamlel pecker", "[bB8\u0441\u0412]\s*[wW]\s*[cC\u0421\u0441pP]" },
			new String[] { "mamlel",
					"[cC\u0421\u0441][Rr][aA\u0430\u0440][Cc\u0421\u0441Kk]{1,}[Ee\u0435][RrAa\u0430\u0440]" },
			new String[] { "rexxed",
					"[bB8\u0441\u0412][LlIi][aA\u0430\u0440][Cc\u0421\u0441Kk]{1,}[Cc\u0421\u0441Kk]{1,}[Ee][Dd]" },
			new String[] { "clorox challenge",
					"[bB8\u0441\u0412][Ll][Ee][aA\u0430\u0440][Cc\u0421\u0441Kk]{1,}[Hh]" } };

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerChat(AsyncChatEvent e) {
		// setup
		Component message = doAprilFools(e.message());

		// apply word filters
		message = filter(message, wordFilters);

		String msg = PlainTextComponentSerializer.plainText().serialize(message);
		Attribute selected = null;

		// the actual meat and potatoes of the entire class
		for (Attribute a : formattingAttribute) {
			if ((a.wrap && test(a.chars, msg)) || (!a.wrap && msg.startsWith(a.chars))) {
				selected = a;
				break;
			}
		}

		// if no text attribute applies, don't do anything else
		if (selected == null) {
			e.message(message);
			return;
		}

		String chars = selected.chars;
		int col = selected.color;

		// apply color (if present)
		if (selected.wrap) {
			message = TextComponent.ofChildren(replaceMagicChars(message, chars)).color(TextColor.color(col));
		} else {
			message = TextComponent.ofChildren(message).color(TextColor.color(col));
		}
		// apply deco (if present)
		if (selected.deco != null) {
			message = TextComponent.ofChildren(replaceMagicChars(message, chars)).decorate(selected.deco);
		}

		// do rainbow text
		if ("~-~".equals(selected.chars)) {
			TextComponent.Builder builder = Component.text();
			// replace chars
			message = TextComponent.ofChildren(replaceMagicChars(message, selected.chars));

			// get the string
			String tmp = PlainTextComponentSerializer.plainText().serialize(message);

			// C O L O R!
			for (int j = 0; j < tmp.length(); j++) {
				char c = tmp.charAt(j);
				float hue = (0.8f - ((float) j / (float) tmp.length()) * 0.8f) % 1.0f;
				TextColor color = TextColor.color(Color.HSBtoRGB(hue, 1.0f, 1.0f));
				builder.append(Component.text(String.valueOf(c), color));
			}

			message = builder.build();
		}

		e.message(message);
	}
	
	// code relating to Attribute selection logic

	private Component replaceMagicChars(Component in, String chars) {
		return in.replaceText(TextReplacementConfig.builder().replacement("").match(Pattern.quote(chars)).build());
	}

	private boolean test(String seq, String msg) {
		return msg.startsWith(seq) && msg.endsWith(seq) && msg.replace(seq, "").length() != 0;
	}

	// code relating to word filters

	private static boolean isAprilFools;
	static {
		LocalDate currentDate = LocalDate.now();
		isAprilFools = (currentDate.getMonthValue() == 4 && currentDate.getDayOfMonth() == 1);
		// isAprilFools = true; //debug
	}

	private Component doAprilFools(Component message) {
		if (isAprilFools) {
			String[][] fourChanWordfilters = new String[][] { new String[] { "based", "soy(?!\s|$)" },
					new String[] { "BASED", "SOY(?!\\s|$)" }, new String[] { "onions ", "soy\s*" },
					new String[] { "ONIONS ", "SOY\\s*" } };
			message = filter(message, fourChanWordfilters);
		}
		return message;
	}

	private Component filter(Component message, String[][] filters) {
		for (String[] filter : filters) {
			message = message.replaceText(TextReplacementConfig.builder().replacement(filter[0]).match(filter[1]).build());
		}
		return message;
	}

	// wrap: Whether or not this attribute needs to be ===surrounded=== or >alone
	// chars: the magic chars that need to exist for the color/style to get applied
	private static class Attribute {
		public boolean wrap = false;
		public String chars = "";
		public int color = 0;
		public TextDecoration deco = null;

		Attribute(boolean wrap, String chars, NamedTextColor color, TextDecoration deco) {
			this.wrap = wrap;
			this.chars = chars;
			this.color = color.value();
			this.deco = deco;
		}

		// second constructor to allow arbitrary color codes
		Attribute(boolean wrap, String chars, int color, TextDecoration deco) {
			this.wrap = wrap;
			this.chars = chars;
			this.color = color;
			this.deco = deco;
		}
	}

}
