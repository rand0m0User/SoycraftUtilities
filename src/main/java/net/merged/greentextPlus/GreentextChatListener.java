package net.merged.greentextPlus;

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

public class GreentextChatListener implements Listener {

	String PINK = "=";
	String RED = "==";
	String RED2 = "===";
	String CALM1 = "-";
	String CALM = "--";
	String CALM3 = "---";
	String COAL1 = "+";
	String COAL = "++";
	String COAL3 = "+++";
	String DATAMINING = "%%";
	String YELLOW = "||";
	String SNEED = "::";
	String CROSSOUT = "~~"; 
	String UNDERLINE = "__";
	String BOLD = "'";
	String ITALIC = "''";

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerChat(AsyncChatEvent e) {
		String msg = PlainTextComponentSerializer.plainText().serialize(e.message());
		// msg.replaceAll(":skull:", "\u2620"); //i will NOT learn regex!

		NamedTextColor ret = null;
		TextDecoration retb = null;
		String chars = "";
		switch (msg.charAt(0)) { // attempt at optomizing out if else spam by using a switch jumptable
		case '>':
			ret = NamedTextColor.GREEN;
			break;
		case '<':
			ret = NamedTextColor.GOLD; // KUUUUUUUZ ADD ChatColor.ORANGE
			break;
		case '^':
			ret = NamedTextColor.DARK_PURPLE;
			break;
		case '=':
			if (test(RED2, msg)) {// be carefull of order of operations next time retard
				ret = NamedTextColor.DARK_RED;
				chars = RED2;
			} else if (test(RED, msg)) { // fixed bug where "====" would result in an empty message
				ret = NamedTextColor.RED;
				chars = RED;
			} else if (test(PINK, msg)) {
				ret = NamedTextColor.LIGHT_PURPLE;
				chars = PINK;
			}
			break;
		case '-':
			if (test(CALM3, msg)) { // 3 '-' 's
				ret = NamedTextColor.DARK_BLUE;
				chars = CALM3;
			} else if (test(CALM, msg)) { // 2 '-' 's
				ret = NamedTextColor.BLUE;
				chars = CALM;
			} else if (test(CALM1, msg)) { // 1 '-' 's
				ret = NamedTextColor.AQUA;
				chars = CALM1;
			}
			break;
		case '+':
			if (test(COAL3, msg)) { // 3 '-' 's
				ret = NamedTextColor.BLACK;
				chars = COAL3;
			} else if (test(COAL, msg)) { // 2 '-' 's
				ret = NamedTextColor.DARK_GRAY;
				chars = COAL;
			} else if (test(COAL1, msg)) { // 1 '-' 's
				ret = NamedTextColor.GRAY;
				chars = COAL1;
			}
			break;
		case '|':
			if (test(YELLOW, msg)) {
				ret = NamedTextColor.YELLOW;
				chars = YELLOW;
			}
			break;
		case ':':
			if (test(SNEED, msg)) {
				ret = NamedTextColor.GOLD;
				chars = SNEED;
			}
			break;
		case '%':
			if (test(DATAMINING, msg)) {
				ret = NamedTextColor.DARK_GREEN;
				retb = TextDecoration.BOLD;
				chars = DATAMINING;
			}
			break;
		case '~':
			if (test(CROSSOUT, msg)) {
				retb = TextDecoration.STRIKETHROUGH;
				chars = CROSSOUT;
			}
			break;
		case '_':
			if (test(UNDERLINE, msg)) {
				retb = TextDecoration.UNDERLINED;
				chars = UNDERLINE;
			}
			break;
		case '\'':
			if (test(ITALIC, msg)) {
				retb = TextDecoration.ITALIC;
				chars = ITALIC;
			} else if (test(BOLD, msg)) {
				chars = BOLD;
				retb = TextDecoration.BOLD;
			}
			break;
		default:
		}
		Component message = e.message();
		if (ret != null) {
			if (chars.length() > 0) {
				message = TextComponent.ofChildren(replaceMagicChars(message, chars)).color(ret);
			} else {
				message = TextComponent.ofChildren(message).color(ret);
			}
		}
		if (retb != null) {
			message = TextComponent.ofChildren(replaceMagicChars(message, chars)).decorate(retb);
		}
		e.message(message);
	}

	private Component replaceMagicChars(Component in, String chars) {
		return in.replaceText(TextReplacementConfig.builder().replacement("").match(Pattern.quote(chars)).build());
	}

	private boolean test(String seq, String msg) {
		return msg.startsWith(seq) && msg.endsWith(seq) && msg.replace(seq, "").length() != 0;
	}
}
